<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>时间表示方式的转换</h2></div>
<!--    <div style="margin-top: 10px">-->
<!--      <el-steps :active="active" simple process-status="finish">-->
<!--        <el-step title="输入"></el-step>-->
<!--        <el-step title="提交"></el-step>-->
<!--        <el-step title="结果"></el-step>-->
<!--      </el-steps>-->
<!--    </div>-->
    <el-divider/>
    <div>
      <el-card style="position:absolute;left:5px;top:0px;width:40%;height:100%;overflow-y:auto;">
        <div slot="header" class="clearfix">
          <span>实验流程</span>
          <!--          <span @click="isCaonima" style="color:red"> 展开>>> </span>-->
          <span style="float: right;color: #498c5f"> <a
            :href="data.resource.url" :download="data.resource.name" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div style="padding-top:25px;" v-html="data.process"></div>
        <!--        <div style="font-size:20px;font-weight: 700">-->
        <!--          1）系统随机生成格里高利时，用户根据转换公式，计算出儒略日后，填入提示的框中，由系统判断对错；-->
        <!--        </div>-->
        <!--        <img src="../../../assets/images/firnula/rulueri.png"/>-->
        <!--        <div style="font-size:20px;font-weight: 700">-->
        <!--          2）系统随机生成儒略日，用户根据转换公式，计算出格里高利时后，填入提示的框中，由系统判断对错；-->
        <!--        </div>-->
        <!--        <img v-if="shabi" src="../../../assets/images/firnula/gaolishi.png">-->
      </el-card>
    </div>
    <div class="card" style="margin-top: 10px;">
<!--      <p>ps:{{ isTime ? '请将格里高利时转换为儒略日后进行输入' : '请将儒略日转换为格里高利时后进行输入' }}</p>-->
      <div>
        <el-button @click="generateData">随机生成输入值</el-button>
      </div>
      <div style="text-align: center">
        <a @click="switchTime">切换转换关系</a>
        <!--        <a>儒略日转格里高利时></a>-->
        <div style="padding-top: 25px;">
          <el-form ref="form" :model="form" :rules="rules" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item v-if="isTime" label="格里高利时" prop="time">
                  <el-date-picker
                    :style="{width: '100%'}"
                    v-model="form.time"
                    type="datetime"
                    placeholder="选择日期时间"
                    clearable
                  >
                  </el-date-picker>
                </el-form-item>
                <el-form-item v-if="!isTime" label="儒略日" prop="julian">
                  <el-input  v-model="form.julian" :style="{width: '100%'}"
                            placeholder="请输入儒略日"
                            clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item v-if="isTime" label="儒略日" prop="julian">
                  <el-input v-model="form.julian" @input="julianInput" :style="{width: '100%'}" placeholder="请输入儒略日"
                            clearable/>
                </el-form-item>
                <el-form-item v-if="!isTime" label="格里高利时" prop="time">
                  <el-date-picker
                    :style="{width: '100%'}"
                    v-model="form.time"
                    type="datetime"
                    placeholder="选择日期时间"
                    clearable
                    @change="timeChange"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button @click="submitHandle" type="primary">
                  转换
                </el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "TimeTransition",
  data() {
    return {
      data: {},
      form: {julian: ''},
      answer: 0,
      active: 0,
      rules: {
        time: [
          {required: true, message: '请输入格里高利时', trigger: 'blur'},
        ],
        julian: [
          {required: true, message: '请输入儒略日', trigger: 'blur'},
        ],
      },
      isTime: true,
      shabi: false
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.data = response.data
    })
    this.randomDate();
  },
  methods: {
    isCaonima() {
      this.shabi = !this.shabi
    },
    submitHandle() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          //判断是转什么
          if (this.isTime) {
            if ((this.form.julian * 1) === this.julianTime(this.form.time)) {
              this.active = 2
              this.notifySuccess("正确", "转换正确")
              // this.randomDate()
              // this.rest();
            } else {
              this.active = 2
              this.$confirm('转换错误，正确答案为：' +this.julianTime(this.form.time) , '提示', {
                confirmButtonText: '确定',
                type: 'warning'
              }).then(() => {
                this.form.julian = this.julianTime(this.form.time)
              })
              // this.rest();
            }
          } else {
            let system = moment(this.julianToTime(this.form.julian)).format("YYYY-MM-DD HH:mm:ss")
            var date = new Date(this.form.time);
            let user = moment(date).format("YYYY-MM-DD HH:mm:ss");
            console.log(system,user,date)
            if (user === system) {
              this.active = 2
              this.notifySuccess("正确", "转换正确")
              // this.rest();
              this.$set(this.form.julian, 'julian', this.randomJulianDate())
            } else {
              this.active = 2
              this.$confirm('转换错误，正确答案为：' +system , '提示', {
                confirmButtonText: '确定',
                type: 'warning'
              }).then(() => {
                this.form.time = moment(this.julianToTime(this.form.julian)).format("YYYY-MM-DD HH:mm:ss")
              })
              // this.rest();
            }
          }
        }
      });
    },
    rest() {
      let that = this;
      if (this.isTime) {
        setTimeout(function () {
          that.active = 0
          that.$set(that.form, 'julian', '');
        }, 3000)
      } else {
        setTimeout(function () {
          that.active = 0
          that.$set(that.form, 'time', '');
        }, 3000)
      }

    },
    randomDate() {
      var maxDateRandom = new Date().getTime();
      // 由于当前环境为北京GMT+8时区，所以与GMT有8个小时的差值
      var minDateRandom = new Date(1970, 0, 1, 8).getTime();
      var randomDate = this.getRandom(minDateRandom, maxDateRandom);
      var dateStr = moment(randomDate).format("YYYY-MM-DD HH:mm:ss");
      this.$set(this.form, 'time', dateStr)
      // this.form.time = dateStr;
      // this.julianTime(dateStr);
      // this.julianToTime(this.answer);
    },
    julianTime(str) {
      /*var now = new Date(str);
      var month = now.getUTCMonth() + 1;
      var day = now.getUTCDate();
      var year = now.getUTCFullYear();
      var hours = now.getHours();
      var minutes = now.getMinutes();
      var seconds = now.getSeconds();
      var y = eval(year);
      var m = eval(month);
      var d = eval(day);
      var h = eval(hours);
      var min = eval(minutes);
      var s = eval(seconds);

      if(!((y%4==0 && y%100!=0) || y%400==0)){
        d=d+1;
      }

      let temp = Math.floor(7.0 * (y + Math.floor((m + 9.0) / 12.0)) / 4.0);
      let tem = Math.floor(275.0 * m / 9.0);
      let res = 1721013.5 + 367 * y - temp + d + h / 24 + min / 1440 + s / 86400 + tem;
      return res;*/


      var now = new Date(str);
      var month = now.getUTCMonth() + 1;
      var day = now.getDate();
      // var day = now.getDay();
      var year = now.getUTCFullYear();
      var hours = now.getHours();
      var minutes = now.getMinutes();
      var seconds = now.getSeconds();
      var y = eval(year);
      var m = eval(month);
      var d = eval(day);
      var h = eval(hours);
      var min = eval(minutes);
      var s = eval(seconds);

      // if(!((y%4==0 && y%100!=0) || y%400==0)){
      //   d=d+1;
      // }

      let temp = Math.floor(7.0 * (y + Math.floor((m + 9.0) / 12.0)) / 4.0);
      let tem = Math.floor(275.0 * m / 9.0);
      let res = 1721013.5 + 367 * y - temp + d + h / 24 + min / 1440 + s / 86400 + tem;
      // let temp = Math.floor(7.0 * (y + Math.floor((m + 9.0) / 12.0)) / 4.0);
      // let tem = Math.floor(275.0 * m / 9.0);
      // let res = 1721013.5 + 367 * y - temp + d + h / 24 + min / 1440 + s / 86400 + tem;
      return res;

      // var now = new Date(str);
      // var month = now.getUTCMonth() +1;
      // var day = now.getUTCDate() + 1;
      // var year = now.getUTCFullYear();
      // var hours = now.getHours();
      // var minutes = now.getMinutes();
      // var seconds = now.getSeconds();
      // var y = eval(year);
      // var m = eval(month);
      // var d = eval(day);
      // var h = eval(hours);
      // var min = eval(minutes);
      // var s = eval(seconds);
      //
      // if (m==1 || m==2){
      //   m = m + 12
      //   y = y -1
      // }
      // let B = 0
      // if(y > 1582 || (y == 1582 && m > 10) || (y == 1582 && m == 10 && d >= 15) ){
      //   B = 2 - (y / 100) + (y / 400)
      // }
      //
      // var JD = (365.25 * (y + 4712)) + (30.61 * (m + 1)) + d - 63.5 + B
      // JD = JD + h/24 + min/60/24 + s/60/60/24
      // console.log(JD)
      // return JD


    },
    julianTimeSimple(str) {
      var now = new Date(str);
      var month = now.getUTCMonth() + 1;
      var day = now.getUTCDate();
      var year = now.getUTCFullYear();
      var hours = now.getHours();
      var minutes = now.getMinutes();
      var seconds = now.getSeconds();
      var y = eval(year);
      var m = eval(month);
      var d = eval(day);
      var h = eval(hours);
      var min = eval(minutes);
      var s = eval(seconds);
      let temp = Math.floor(7.0 * (y + Math.floor((m + 9.0) / 12.0)) / 4.0);
      let tem = Math.floor(275.0 * m / 9.0);
      let res = 1721013.5 + 367 * y - temp + d + tem;
      return res;
    },
    julianToTime(jd) {
      jd += 0.5;
      let Z = Math.floor(jd)
      let F = jd - Z
      let A;
      let a;
      if (Z < 2299161) {
        A = Z;
      } else {
        a = Math.floor((Z - 2305507.25) / 36524.25)
        A = Z + 10 + a - Math.floor(a / 4)
      }
      let k = 0;
      let B;
      let C;
      let D;
      let E;
      let day;
      let month;
      let year;
      while (true) {
        B = A + 1524  // 以BC4717年3月1日0时为历元
        C = Math.floor((B - 122.1) / 365.25)  // 积年
        D = Math.floor(365.25 * C)  // 积年的日数
        E = Math.floor((B - D) / 30.6)  // B-D为年内积日，E即月数
        day = B - D - Math.floor(30.6 * E) + F
        if (day >= 1) {
          break
        }
        A -= 1;
        k += 1
      }
      // month = E - 1 ? E < 14 : E - 13
      // year = C - 4716 ? month > 2 : C - 4715
      if (E < 14) {
        month = E - 1
      } else {
        month = E - 13
      }
      if (month > 2) {
        year = C - 4716
      } else {
        year = C - 4715
      }
      day += k;
      if (day === 0) {
        day += 1;
      }
      return [year, month, day]
    },
    getRandom(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
    julianInput(v) {
      if (v) {
        this.active = 1
      } else {
        this.active = 0
      }
    },
    timeChange(v) {
      if (v) {
        this.active = 1
      } else {
        this.active = 0
      }
    },
    switchTime() {
      this.isTime = !this.isTime
      if (this.isTime) {
        this.$set(this.form, 'julian', '')
        this.randomDate();
      } else {
        this.$set(this.form, 'time', '')
        this.form.julian = this.randomJulianDate();
      }
    },

    generateData() {
      if (this.isTime) {
        this.randomDate()
      } else {
        this.$set(this.form, 'julian', '')
        this.form.julian = this.randomJulianDate();
      }
    },
    randomJulianDate() {
      var maxDateRandom = new Date().getTime();
      // 由于当前环境为北京GMT+8时区，所以与GMT有8个小时的差值
      var minDateRandom = new Date(1970, 0, 1, 8).getTime();
      var randomDate = this.getRandom(minDateRandom, maxDateRandom);
      var dateStr = moment(randomDate).format("YYYY-MM-DD HH:mm:ss");
      return this.julianTimeSimple(dateStr);
    }
  },
}
</script>

<style>
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
