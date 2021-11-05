<template>
  <div class="app-container" v-loading="loading">
    <el-row style="margin-left: -10px; margin-right: -10px; height: 98vh;">
      <el-col :span="4" style="padding-left: 10px; padding-right: 10px;">
        <el-card shadow="always" style="height: 94vh; overflow-y: auto;">
          <div v-for="(type,index) in paperData.groupList">
            <div class="title">
              {{ type.title }}<span data-v-9b6d3d2c="" style="float: right; font-size: 12px;">总 {{ type.quCount }} 题/共 {{
                type.totalScore
              }} 分</span>
            </div>
            <div class="card-box">
              <div class="item" v-for="(item,index) in type.quList">
                <div class="num " :class="{'current':item.id===cardItem.id}" @click="handleCurrent(item)">{{
                    index + 1
                  }}
                </div>
                <div class="flag"></div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="17" style="padding-left: 10px; padding-right: 10px;">
        <QuItem :value="quData" @fill="handleFill"></QuItem>
      </el-col>
      <el-col :span="3" style="padding-left: 10px; padding-right: 10px;">
        <el-card shadow="always" style="height: 94vh;">
          <el-row style="margin-left: -5px; margin-right: -5px; text-align: center; line-height: 30px;">
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">剩余时间</el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <exam-timer @timeOut="doHandler" :value="paperData.totalTime"></exam-timer>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-divider/>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-button type="warning" @click="handHandExam">提交试卷</el-button>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-divider/>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-button type="warning" icon="el-icon-back" :disabled="!showPrevious" @click="handleNext(-1)">上一题
              </el-button>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px; padding-top: 20px;">
              <el-button type="primary" icon="el-icon-right" :disabled="!showNext" @click="handleNext(1)">下一题
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import {fullAnswer, paperDetail, quDetail} from "@/api/vm/testPaper";
import QuItem from "@/views/web/exam/components/QuItem";
import ExamTimer from "@/views/web/exam/components/ExamTimer";

export default {
  name: "start",
  components: {ExamTimer, QuItem},
  data() {
    return {
      showPrevious: false,
      showNext: true,
      paperId: null,
      // 当前答题卡
      cardItem: {},
      allItem: [],
      // 当前试题内容
      quData: {
        answerList: []
      },
      paperData: {},
      loading: false,
      leftSeconds: 0,
      min: '00',
      sec: '00'
    }
  },
  created() {
    let id = this.$route.params.id //试卷id
    if (typeof id !== 'undefined') {
      this.paperId = id;
      this.fetchData(id);
    }
  },
  methods: {
    handleCurrent(item) {
      this.nextQu(item);
    },
    fetchData(id) {
      paperDetail(id).then(response => {
        console.log(response)
        this.paperData = response.data
        //考完了，不给靠了
        if (this.paperData.status !== 0) {

        }
        var that = this;
        this.paperData.groupList.forEach((item, index) => {
          item.quList.forEach((qu) => {
            that.allItem.push(qu);
          })
        })

        if (!that.allItem || that.allItem.length == 0) {
          that.$confirm('试卷内容出现问题，无法继续考试！', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'error'
          }).then(function () {
          });
          return;
        }
        that.cardItem = that.allItem[0];

        that.fetchQuData(that.cardItem);
      })
    },
    fetchQuData(item) {
      this.loading = true;

      this.cardItem = item;

      var params = {
        paperId: this.paperId,
        quId: item.quId //题目ID
      }
      quDetail(params).then(response => {
        this.quData = response.data
        this.loading = false
      })
    },
    handleNext(num) {
      var index = 0;

      for (var i = 0; i < this.allItem.length; i++) {
        if (this.allItem[i].id === this.quData.id) {
          index = i;
        }
      }

      index += num;
      this.nextQu(this.allItem[index]);
    },
    nextQu(item) {
      // 最后一个索引
      var last = this.allItem.length - 1;
      this.showPrevious = item.id !== this.allItem[0].id;
      this.showNext = item.id !== this.allItem[last].id; // 查找详情

      this.fetchQuData(item);
    },
    handleFill(quData) {
      console.log("quData", quData)
      fullAnswer(quData).then(response => {
        console.log(response)
      })
    },
    countNotAnswered() {
      var na = 0; // 判断全部未答
      this.paperData.groupList.forEach(function (item) {
        item.quList.forEach(function (qu) {
          if (!qu.answered) {
            na += 1;
          }
        });
      });
      return na;
    },
    handHandExam() {
      var na = this.countNotAnswered();
      console.log(na)
    },
    //超时 交卷
    doHandler() {
      console.log("超时 交卷")
    }
  }
}
</script>

<style scoped>
.title {
  font-size: 14px;
  font-weight: 700;
  padding: 10px 0px 10px 0px;
}

.card-box {
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  margin-left: -5px;
  padding-right: 0px !important;
  padding-left: 0px !important;
}

.card-box .item {
  width: 30px;
  height: 40px;
  background: #eee;
  border-radius: 2px;
  font-size: 6px;
  align-items: center;
  cursor: pointer;
  margin: 4px 4px 10px 4px;
}

.card-box .item .current {
  border-radius: 2px;
  background: #FFBA00;
  color: #fff;
}

.card-box .item .num {
  text-align: center;
  color: #555;
  font-weight: 700;
  padding: 3px;
}

.card-box .item .flag {
  text-align: center;
  border-top: #fff 1px solid;
  padding: 3px;
}


.content p {
  margin-block-start: 0em;
  margin-block-end: 0.5em;
}


</style>
