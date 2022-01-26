<template>
  <div class="app-container">
    <el-col :span="24">
      <el-form ref="postForm" :model="postForm" label-position="left" label-width="150px">
        <el-card>
          <el-form-item label="题目类型 " prop="type">
            <el-select v-model="postForm.type" class="filter-item" @change="handleTypeChange">
              <el-option
                v-for="item in quType"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="难度等级 " prop="level">
            <el-select v-model="postForm.level" class="filter-item">
              <el-option
                v-for="item in levels"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="所属章节 " prop="chapterId">
            <el-select v-model="postForm.chapterId" class="filter-item">
              <el-option
                v-for="item in chapters"
                :key="item.id"
                :label="item.title"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="试题内容" prop="content">
<!--            <el-input v-model="postForm.content" type="textarea"/>-->
            <timymce-editor ref="content"></timymce-editor>
          </el-form-item>

          <el-form-item label="整题解析" prop="analysis">
<!--            <el-input v-model="postForm.analysis" type="textarea" :precision="1" :max="999999"/>-->
            <timymce-editor ref="analysis"></timymce-editor>
          </el-form-item>
        </el-card>

        <div v-if="postForm.type!=='3'" class="filter-container" style="margin-top: 25px">
          <el-button class="filter-item" type="primary" icon="el-icon-plus" size="small" plain @click="handleAdd">
            添加
          </el-button>
          <el-table
            :data="postForm.answerList"
            :border="true"
            style="width: 100%;"
          >
            <el-table-column label="是否答案" width="120" align="center">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.isRight">答案</el-checkbox>
              </template>
            </el-table-column>
            <el-table-column label="选项内容">
              <template slot-scope="scope">
                <el-input v-model="scope.row.content" type="textarea"/>
              </template>
            </el-table-column>

            <el-table-column label="答案解析">
              <template slot-scope="scope">
                <el-input v-model="scope.row.analysis" type="textarea"/>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              align="center"
              width="100px"
            >
              <template slot-scope="scope">
                <el-button type="danger" icon="el-icon-delete" circle @click="removeItem(scope.$index)"/>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div style="margin-top: 20px">
          <el-button type="primary" @click="submitForm">保存</el-button>
          <el-button type="info" @click="onCancel">返回</el-button>
        </div>
      </el-form>
    </el-col>
  </div>
</template>

<script>
import {getTask} from "@/api/vm/task";
import {addAnswer, getAnswer, updateAnswer} from "@/api/vm/answer"
import timymceEditor from "@/components/TimymceEditor";

export default {
  name: "TestQuestionForm",
  components:{timymceEditor},
  data() {
    return {
      id: '',
      taskId: '',
      postForm: {
        answerList: []
      },
      quType: [],
      levels: [],
      chapters: [],
      rules: {
        content: [
          {required: true, message: '题目内容不能为空！'}
        ],

        type: [
          {required: true, message: '题目类型不能为空！'}
        ],

        level: [
          {required: true, message: '必须选择难度等级！'}
        ]
      }
    }
  },
  created() {
    let that = this;
    this.getDicts("vm_qu_type").then(response => {
      this.quType = response.data;
    });
    this.getDicts("vm_qu_level").then(response => {
      this.levels = response.data;
    });
    const id = this.$route.params.id
    this.id = id;

    let chapters
    if (id != null) {
      //修改
      chapters = this.$route.params.chapters
      this.taskId = this.$route.params.quId
    } else {
      chapters = this.$route.query.chapters
      this.taskId = this.$route.query.quId
    }
    this.chapters = chapters
    if (id != null) {
      getAnswer(id).then(response => {
        console.log(response)
        this.postForm = response.data
        this.postForm.level = response.data.level + ""
        this.postForm.type = response.data.type + ""
        // this.postForm.answerList = JSON.parse(response.data.options)
        setTimeout(function () {
          that.$refs.content.setContent(that.postForm.content);

          that.$refs.analysis.setContent(that.postForm.analysis);
        },300)
      })
    }
  },
  methods: {
    handleTypeChange(v) {
      this.postForm.answerList = []
      if (v === '2') {
        this.postForm.answerList.push({isRight: true, content: '正确', analysis: ''})
        this.postForm.answerList.push({isRight: false, content: '错误', analysis: ''})
      }

      if (v === '0' || v === '1') {
        this.postForm.answerList.push({isRight: false, content: '', analysis: ''})
        this.postForm.answerList.push({isRight: false, content: '', analysis: ''})
        this.postForm.answerList.push({isRight: false, content: '', analysis: ''})
        this.postForm.answerList.push({isRight: false, content: '', analysis: ''})
      }

    },
    // 添加子项
    handleAdd() {
      this.postForm.answerList.push({isRight: false, content: '', analysis: ''})
    },
    removeItem(index) {
      this.postForm.answerList.splice(index, 1)
    },
    submitForm() {
      this.postForm.content=this.$refs.content.content;
      this.postForm.analysis=this.$refs.analysis.content;
      let rightCount = 0
      this.postForm.answerList.forEach(function (item) {
        if (item.isRight) {
          rightCount += 1
        }
      })
      if (this.postForm.type === "0") {
        if (rightCount !== 1) {
          this.$message({
            message: '单选题答案只能有一个',
            type: 'warning'
          })
          return
        }
      }
      if (this.postForm.type === "1") {
        if (rightCount < 2) {
          this.$message({
            message: '多选题至少要有两个正确答案！',
            type: 'warning'
          })
          return
        }
      }
      if (this.postForm.type === "2") {
        if (rightCount !== 1) {
          this.$message({
            message: '判断题只能有一个正确项！',
            type: 'warning'
          })

          return
        }
      }
      console.log(this.postForm)
      var that = this;
      this.$refs.postForm.validate((valid) => {
        if (!valid) {
          return
        }
        this.postForm.taskId = this.taskId;
        this.postForm.options = JSON.stringify(this.postForm.answerList)
        if (this.id==null) {
          addAnswer(this.postForm).then(response => {
            if (response.code === 200) {
              this.$notify({
                title: '成功',
                message: '试题保存成功！',
                type: 'success',
                duration: 2000
              })
              // that.$confirm('试题添加成功，接下来您要？', '提示', {
              //   confirmButtonText: '继续添加下一题',
              //   cancelButtonText: '返回列表',
              //   type: 'warning'
              // }).then(function () {
              //   // 刷新页面
              //   // _this2.clearForm();
              //
              // }).catch(function () {
              //   that.$router.push({path: '/vm/question/testQuestionForm/', query: {quId: that.taskId, chapters: that.chapters}});
              // });
            }
             this.$router.go(-1)
          })
        }else {
          console.log("修改")
          updateAnswer(this.postForm).then(response=>{
            if (response.code === 200) {
              this.$notify({
                title: '成功',
                message: '试题保存成功！',
                type: 'success',
                duration: 2000
              })
            }
            this.$router.go(-1)
          })
        }
      })
    },
    onCancel() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
>>>.tox-tinymce {
  border: 1px solid #ccc;
  border-radius: 0;
  box-shadow: none;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Oxygen-Sans,Ubuntu,Cantarell,"Helvetica Neue",sans-serif;
  overflow: hidden;
  position: relative;
  visibility: inherit!important;
  z-index: 1;
}
</style>
