<template>
  <div>
    <h4>题目列表</h4>
    <el-alert v-if="groupList.length===0" title="请点击右侧【添加新的大题】开始添加数据" type="info"></el-alert>
    <qu-select-dialog v-if="dialogVisible" :dialogShow.sync="dialogVisible" @select="select" @close="closeDialog" :quType.sync="quType"
                      :excludes.sync="excludes"></qu-select-dialog>
    <el-card class="content-card" v-for="(item,index) in groupList">
      <div slot="header" class="clearfix" style="background: rgb(238, 238, 238); margin: -20px; padding: 20px">
        <el-input size="small" v-model="item.title" style="width: 200px"></el-input>
        <el-button size="small" style="margin-left: 10px" @click="openSelectQu(index)">添加题目</el-button>
      </div>
      <div v-for="(row,key) in item.quList">
        <div style="display: flex">
          <div style="flex-grow: 1">
            <div class="qu-item">
              <div style="display:flex;">
                <div class="no">{{ key + 1 }}</div>
                <div style="flex-grow: 1">
                  <div class="content">
                    <p>{{ row.contentText }}</p>
                  </div>
                </div>
              </div>
              <div>
                <div style="padding: 5px 0px" v-for="(r,i) in row.answerList">
                  <div class="an-tag" :class="{'an-tag-correct':r.isRight}">{{ String.fromCharCode(i + 65) }}</div>
                  {{ r.content }}
                </div>
              </div> <!--题目选项-->
              <div class="score-box">
                <div v-if="row.analysis!=null&&row.analysis!==''"><div v-html="row.analysis"></div></div>
                <div v-else><span>本题暂未设置题目解析！</span></div>
              </div>
            </div>
          </div>
          <div style="width: 200px;text-align: center;font-size: 12px;line-height: 36px;">
            <el-button type="danger" icon="el-icon-delete" circle @click="removeItem(item.quList,index)"></el-button>
            <div class="opt-box">
              <div>
                <div class="opt-title">本题</div>
                <div>
                  <el-input size="mini" v-model="row.score" style="width: 60px" @input="handleScoreChange"></el-input>
                </div>
                <div class="opt-title">分</div>
              </div>
            </div>
          </div>
        </div> <!--题目内容-->

      </div>
    </el-card>
  </div>
</template>

<script>
import QuSelectDialog from "@/views/vm/testPaper/components/components/QuSelectDialog";

export default {
  name: "JoinQuList",
  components: {QuSelectDialog},
  props: {
    groupList: {
      type: Array
    },
    type: { //试卷分类
      type: String
    }
  },
  data() {
    return {
      dialogVisible: false,
      quType: '', // 题目分类
      index: 0,
      postForm: {
        groupList: []
      },
      excludes: []
    }
  },
  watch: {
    // 内部改动题目列表
    groupList: {
      handler: function handler(val) {
        this.postForm.groupList=val
        this.handleScoreChange();
      },
      deep: true
    }
  },
  created() {
    this.postForm.groupList = this.groupList;
    this.postForm.type = this.type;
  },
  methods: {
    openSelectQu(index) {
      this.quType = this.postForm.groupList[index].quType;
      this.index = index;
      this.dialogVisible = true
    },
    select(data) {
      this.dialogVisible = false
      if (data == undefined || !data || data.length == 0) {
        return;
      }
      data.forEach(row => {
        row.answerList = JSON.parse(row.options)
      })
      var list = this.postForm.groupList[this.index].quList;
      for (let i = 0; i < data.length; i++) {
        var fixedAnswers = [];
        for (let j = 0; j < data[i].answerList.length; j++) {
          var a = data[i].answerList[j];
          console.log("a",a)
          fixedAnswers.push({
            answerId: a.id,
            content: a.content,
            contentText:a.contentText,
            analysis: a.analysis,
            isRight: a.isRight,
            pathScore: 0
          })
        }
        var item = {
          id: data[i].id, //题目id
          type: data[i].type, //题目类型
          content: data[i].content,
          contentText: data[i].contentText,
          analysis: data[i].analysis,
          score: 0,
          answerList: fixedAnswers, //题目选项
          // leftList: data[i].leftList,
          // rightList: data[i].rightList,
          // answerGroup: data[i].answerGroup
        };
        list.push(item)
      }
    },
    // 移除题目
    removeItem(list, index) {
      console.log(list,index)
      // if (list.length === 1) {
      //   list = []
      //   list.splice(index,1)
      // } else {
        list.splice(index, 1);
      // }
    },
    handleScoreChange() {
      var paperQu = 0;
      var paperScore = 0;
      var excludes = [];
      for (let i = 0; i < this.groupList.length; i++) {
        var item = this.groupList[i];
        var totalScore = 0;
        var quList = item.quList;
        for (var j = 0; j < quList.length; j++) {
          totalScore += parseInt(quList[j].score); // 排除的ID
          excludes.push(quList[j].id);
        }
        item.totalScore = totalScore;
        item.quCount = quList.length;
        paperQu += item.quCount;
        paperScore += totalScore;
      }
      this.excludes = excludes;
      this.$emit('count', paperQu);
      this.$emit('score', paperScore);
    },
    closeDialog() {
      this.dialogVisible = false
    }
  }
}
</script>

<style scoped>
.content-card {
  margin-bottom: 20px;
  line-height: 28px;
}

>>> .el-card.is-always-shadow {
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}

>>> .el-card {
  border-radius: 4px;
  border: 1px solid #e6ebf5;
  background-color: #fff;
  overflow: hidden;
  color: #303133;
  transition: 0.3s;
}

.qu-item {
  border-bottom: #eee 1px solid;
  padding: 20px;
  font-size: 14px;
}

.no {
  padding-right: 10px;
  font-size: 16px;
  color: #0a84ff;
  font-style: italic;
  font-weight: 700;
  -webkit-margin-after: 0.5em;
  margin-block-end: 0.5em;
}

.an-tag {
  border: 1px solid #C1C1CB;
  border-radius: 50%;
  text-align: center;
  font-size: 12px;
  width: 28px;
  height: 28px;
  display: inline-block;
  margin-right: 10px;
}

.an-tag-correct {
  background: #1890FF;
  color: #fff;
  border: #0762B7 1px solid;
}

.content p {
  -webkit-margin-before: 0em;
  margin-block-start: 0em;
  -webkit-margin-after: 0.5em;
  margin-block-end: 0.5em;
}

.score-box {
  background: #f5f5f5;
  padding: 10px;
  border-radius: 5px;
  margin-top: 20px;
  line-height: 32px;
}

.opt-box {
  background: #eee;
  padding: 10px;
  text-align: center;
  margin-top: 10px;
  width: 180px;
  float: right;
}

.opt-title {
  width: 42px;
  display: inline-block;
  text-align: center;
}
</style>
