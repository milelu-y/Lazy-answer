<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>北斗时与北京时的转换</h2></div>
    <!--    <div style="margin-top: 10px">-->
    <!--      <el-steps :current="current">-->
    <!--        <el-step v-for="item in steps" :key="item.title" :title="item.title"/>-->
    <!--      </el-steps>-->
    <!--    </div>-->
    <div style="position: absolute;left: 0; width: 40%;height:700px;overflow:auto;">
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="data.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="data.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div v-html="data.process">

        </div>
      </el-card>
    </div>
    <div class="card" style="margin-top: 10px;">
      <!--      <p>输入格式:YYYY-M-D HH:ss:mm</p>-->
      <el-form label-width="80px">
        <el-col :span="24">
          <el-form-item label="北斗时">
            <el-input v-model="form.bd" style="width: 200px"></el-input>
            <el-button @click="generateData">刷新</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="UTC">
            <el-input v-model="form.utc" placeholder="输入北斗时转换的UTC" style="width: 200px"></el-input>
            <el-button @click="submitUTC">提交</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="北京时">
            <el-input placeholder="输入UTC转换的北京时间" ref="bjInput" v-model="form.bjTime" style="width: 200px"/>
            <el-button @click="submitBEIJIN">提交</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
import {getExperiment} from "@/api/vm/ex";
import {parseTime} from "@/utils/lazy";

export default {
  name: "BeijingToUTC",
  data() {
    return {
      data: {},
      form: {bd: '', bjTime: '',utc:''},
      showBj: false,
      current: 0,
      steps: [
        {
          title: '输入',
        },
        {
          title: '提交',
        },
        {
          title: '结果',
        },
      ],
      utcTime: '',
      beiJinTime: ''
    }
  },
  created() {
    var bdsWeek = Math.floor(Math.random() * 1000 + 1);
    var bdsWIS = Math.floor(Math.random() * 604800 + 1);
    this.form.bd = bdsWeek + "周" + bdsWIS
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.data = response.data
    })
  },
  methods: {
    generateData() {
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      this.form.bd = bdsWeek + "周" + bdsWIS
    },
    submitUTC() {
      var bd = this.form.bd;
      var utc = this.form.utc;
      var arr = bd.split("周");
      let bdsWeek = arr[0] * 1;
      let bdsWIS = arr[1] * 1;
      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      // 北京时间
      bdsBeginTime.setSeconds(ts)
      this.beiJinTime = parseTime(bdsBeginTime, '{y}-{m}-{d} {h}:{i}:{s}')
      console.log("北京时间：", parseTime(bdsBeginTime, '{y}-{m}-{d} {h}:{i}:{s}'))
      var utcTime = moment(bdsBeginTime).utc().format('YYYY-MM-DD HH:mm:ss')
      this.utcTime = utcTime;
      console.log("系统UTC时间：", utcTime)
      console.log("用户UTC时间：", utc)
      if (utc == '') {

        return;
      }
      if (utcTime == utc) {
        this.notifySuccess("正确", "转换正确")
      } else {
        this.$confirm('转换错误，正确答案为：' + utcTime, '提示', {
          confirmButtonText: '重试',
          type: 'warning'
        }).then(() => {

        })
      }
    },
    submitBEIJIN() {
      //获取用户输入的北京时间
      var userBeiJinTime = this.form.bjTime
      var systemBeiJinTime = this.beiJinTime
      console.log("系统北京时间", this.beiJinTime)
      console.log("用户北京时间", userBeiJinTime)
      if (userBeiJinTime !== '') {
        if (userBeiJinTime === systemBeiJinTime) {
          this.notifySuccess("正确", "转换正确")
        } else {
          this.$confirm('转换错误，正确答案为：' + systemBeiJinTime, '提示', {
            confirmButtonText: '重试',
            type: 'warning'
          }).then(() => {

          })
        }
      }
    }
  },
}
</script>

<style scoped>
@import "../../../../public/tinymce/skins/content/default/content.min.css";

.card {
  min-height: 250px;
  border-radius: 2px;
  padding: 16px;
  margin-bottom: 16px;
  box-shadow: 0 1px 6px rgb(0 0 0 / 12%), 0 1px 4px rgb(0 0 0 / 12%);
}

.steps-content {
  margin-top: 16px;
  border: 1px dashed #e9e9e9;
  border-radius: 6px;
  background-color: #fafafa;
  min-height: 200px;
  text-align: center;
  padding-top: 80px;
}

.steps-action {
  margin-top: 24px;
}
</style>
