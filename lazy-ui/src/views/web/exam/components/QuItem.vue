<template>
  <el-card shadow="always" style="height: 94vh; overflow-y: auto;">
    <div class="ques">
      <el-row>
        <el-col :span="24"
                style="background: rgb(238, 238, 238); line-height: 25px; padding: 10px; margin-bottom: 20px;">
          <span v-if="quData.type===0">单选题</span>
          <span v-if="quData.type===1">多选题</span>
          <span v-if="quData.type===2">判断题</span>
          （本题分值：{{ quData.score }}分）
        </el-col>
        <el-col :span="23">
          <div style="display: flex">
            <div class="no">1.</div>
            <div style="flex-grow: 1;">
              <div class="content"><p>{{ quData.contentText }}</p></div>
            </div>
          </div>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="24" style="padding-top: 20px;" v-if="quData.type===0||quData.type===1||quData.type===2">
          <div class="option">
            <label :class="{'selected':item.checked}" style="cursor: pointer;"
                   v-for="(item ,index) in quData.answerList" @click="handleItemClick(item)">
              <span>{{ String.fromCharCode(index + 65) }} </span> &nbsp; &nbsp; &nbsp;<span
              style="margin-left: 5px"> {{ item.content }}</span>
            </label>
          </div>
        </el-col>
        <el-col :span="24" style="padding-top: 20px;" v-if="quData.type===3">
          <div class="option">
            <label>
              <timymce-editor ref="timymce" :value="quData.answer" @setData="setData"></timymce-editor>
            </label>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
import timymceEditor from "@/components/TimymceEditor";

export default {
  name: "QuItem",
  components: {timymceEditor},
  props: {
    value: {
      type: Object,
      default: function _default() {
        return {};
      }
    }
  },
  watch: {
    // 检测查询变化
    value: {
      handler: function handler(val) {
        this.fillData(val);
      }
    },
    quData: {
      handler: function handler() {
        if (this.quData && this.quData.id) {
          this.$emit('fill', this.quData);
        }
      },
      deep: true
    }
  },
  data() {
    return {
      // 当前试题内容
      quData: {
        quType: '',
        answer: '',
        answerList: []
      }
    }
  },
  created() {
    this.fillData(this.value);
  },
  methods: {
    setData(val) {
      this.quData.answer=val;
      // let that =this;
      // this.quData.answer="1";
      // console.log("this.quData",this.quData)
      // that.$set(that.quData.answer,'answer',val)
    },
    // 填充数据
    fillData: function fillData(data) {
      if (!data) {
        return;
      }
      this.quData = data;
      if (data.type === 3 && this.$refs.timymce) {
        this.$refs.timymce.setContent(data.answer || '');
      }
    },
    handleItemClick(item) {
      console.log(item)
      item.checked = !item.checked
      if (this.quData.type === 0 || this.quData.type === 2) {
        this.quData.answerList.forEach(function (qu) {
          if (item.id !== qu.id) {
            qu.checked = false;
          }
        });
      }
    }
  }
}
</script>

<style scoped>
.ques .option label {
  padding: 10px;
  border: 1px solid #f6f6f8;
  color: #4f4e58;
  font-size: 14px;
  margin-bottom: 10px;
  text-align: left;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  align-items: center;
  line-height: 20px;
  border-radius: 5px;
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

.content p {
  margin-block-start: 0em;
  margin-block-end: 0.5em;
}

ques .option label {
  padding: 10px;
  border: 1px solid #f6f6f8;
  color: #4f4e58;
  font-size: 14px;
  margin-bottom: 10px;
  text-align: left;
  display: flex;
  align-items: center;
  line-height: 20px;
  border-radius: 5px;
}

.ques .option label.selected {
  padding: 10px;
  border: 1px solid #5794f7;
  background: #E8F1F9;
}
</style>
