<template>
    <div style="flex-grow: 1;">
      <div class="qu-item">
        <div style="display: flex;">
          <div class="no">{{ no + 1 }}</div>
          <div style="flex-grow: 1;">
            <div class="content">{{ qu.content }}</div>
          </div>
        </div>

        <div>
          <div style="padding: 5px 0px;" v-for="(item,index) in qu.answerList">
            <div class="an-tag" :class="{'an-tag-correct':item.checked}">{{ String.fromCharCode(index + 65) }}</div>
            {{ item.content }}
          </div>
        </div>
        <div class="score-box">
          <div>
            <div  v-if="qu.type!=3">
              <div class="right-line">
                <i class="right el-icon-success" v-if="qu.isRight"></i>
                <i class="error el-icon-error" v-else></i>
                <span v-if="qu.isRight">答对了</span>
                <span v-else>答错了</span>
              </div>
            </div>
            <div> 学员得分： <span> {{ qu.actualScore }} </span></div>
            <div> 学员答案： <span>{{ userAnswer(qu.answerList) }}</span></div>
          </div>
          <div>
            <span v-if="qu.analysis==null">本题暂未设置题目解析！</span>
            <span v-html="qu.analysis">
                </span>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: "QuItemShow",
  props: {
    value: Object,
    index: Number,
    showAnalysis: {
      type: Boolean,
      default: true
    },
    showAnswer: {
      type: Boolean,
      default: true
    },
    showType: {
      type: Boolean,
      default: false
    },
  },
  watch: {
    value: {
      handler: function handler(val) {
        this.qu = val;
      },
      deep: true
    },
    index: {
      handler: function handler(val) {
        this.no = val;
      }
    },
    state: {
      handler: function handler(val) {
        this.paperState = val;
      }
    }
  },
  data() {
    return {
      qu: {},
      no: 1,
      paperState: 0
    }
  },
  created() {
    this.qu = this.value;
    this.no = this.index;
    this.paperState = this.state;
  },
  methods: {
    userAnswer(answerList) {
      var answer = '';
      for (var i = 0; i < answerList.length; i++) {
        if (answerList[i].checked) {
          answerList[i].abc = String.fromCharCode(i + 65)
          answer += answerList[i].abc;
        }
      }
      if (answer == '') {
        answer = '未作答';
      }
      return answer;
    }
  }
}
</script>

<style scoped>
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
  margin-block-end: 0.5em;
}

.content {
  margin-block-start: 0em;
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

.score-box {
  background: #f5f5f5;
  padding: 10px;
  border-radius: 5px;
  margin-top: 20px;
  line-height: 32px;
}

.right-line {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  align-items: center;
  font-weight: 700;
  font-size: 16px;
}

.right-line .right {
  color: #03DD6D;
  font-size: 22px;
  margin-right: 10px;
}

.right-line .error {
  color: #FF4B50;
  font-size: 22px;
  margin-right: 10px;
}

.check-icon-right {
  color: #03DD6D;
}

.check-icon {
  font-size: 28px;
  color: #ddd;
  cursor: pointer;
  margin-right: 10px;
}

.check-box {
  background: #f5f5f5;
  padding: 20px;
  margin-left: 20px;
  line-height: 40px;
  width: 200px;
  font-size: 14px;
  font-weight: 700;
}

.check-box .item {
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
}
</style>
