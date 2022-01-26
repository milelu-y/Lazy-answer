<template>
  <div>
    <el-dialog :visible="true" width="50%">
      <div>
        <div class="form-box">
          <el-row style="width: 100%">
            <el-col :span="24">
              <div class="title">
                作业名称：
              </div>
              {{ checkData.title }}
            </el-col>
            <el-col :span="12">
              <div class="title">
                作业总分：
              </div>
              {{ checkData.totalScore }} 分
            </el-col>
            <el-col :span="12">
              <div class="title">
                合格分数：
              </div>
              {{ checkData.qualifyScore }} 分
            </el-col>
            <el-col :span="12">
              <div class="title">
                答题时长：
              </div>
              {{ checkData.totalTime }} 分钟
            </el-col>
            <el-col :span="24">
              <div class="title">注意事项：</div>
              <div class="notice">
                {{ checkData.content }}
              </div>
            </el-col>
            <el-col :span="24" v-if="checkData.openType===1">
              <div style="display: flex; align-items: center;">
                <div class="title" style="margin-right: 0px;">提供密码：</div>
              </div>
              <el-input v-model="postForm.password" size="small"></el-input>
            </el-col>
          </el-row>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button>
          <router-link to="/web/index">取 消</router-link>
        </el-button>
        <el-button type="primary" :loading="loading" @click="handleCreate">开始答题</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {getExam} from "@/api/vm/exam";
import {createPaper} from "@/api/vm/testPaper";

export default {
  name: "check",
  data() {
    return {
      deviceDebug: false,
      loading: false,
      checkData: {},
      postForm: {
        examId: null,
        password: ''
      },
    }
  },
  created() {
    var id = this.$route.params.id;
    if (typeof id !== 'undefined') {
      this.postForm.examId = id;
      this.fetchData();
    }
  },
  methods: {
    fetchData() {
      getExam(this.postForm.examId).then(response => {
        this.checkData = response.data
      })
    },
    handleCreate() {
      if (this.checkData.openType === 1 && !this.postForm.password) {
        this.$message.error('答题密码不能为空！');
        return
      }
      //创建试卷
      this.loading = true
      createPaper(this.postForm).then(response => {
        if (response.code === 200) {
          this.loading = false;
          this.$router.push({
            name: 'startExam',
            params: {id: response.data.id}
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.form-box {
  font-size: 14px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  line-height: 42px;
}

.form-box .title {
  font-weight: 700;
  margin-right: 10px;
  display: inline-block;
}

.form-box .notice {
  background: #fcfcfc;
  padding: 15px;
  line-height: 22px;
  font-size: 12px;
}
</style>
