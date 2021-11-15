<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>北斗时与北京时的转换</h2></div>
    <div style="margin-top: 10px">
      <el-steps :current="current">
        <el-step v-for="item in steps" :key="item.title" :title="item.title"/>
      </el-steps>
    </div>
    <div>
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
        </div>
        <div v-if="!showBj" style="font-size:20px;font-weight: 700">
          系统随机生北斗时，用户根据转换公式，计算出UTC，填入提示的框中，由系统判断对错；
        </div>
        <img v-if="!showBj" src="../../../assets/images/firnula/BeidouToUtc.png" />
        <div v-if="showBj" style="font-size:20px;font-weight: 700">
          请将上一步计算的UTC，根据转换公式，计算出北京时间，填入提示的框中，由系统判断对错；
        </div>
        <img v-if="showBj" src="../../../assets/images/firnula/UTCTOLOCAL.png" />
      </el-card>
    </div>
    <div class="card" style="margin-top: 10px">
      <p>ps:请先将北斗时转换成UTC进行输入</p>
      <div>北斗时：{{ form.bd }}</div>
      <div style="text-align: center">
        <div style="padding-top: 25px">
          <el-form :form="form" label-width="80px">
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item label="UTC">
                  <el-input placeholder="请输入UTC" :disabled="showBj" v-model="form.utc"/>
                </el-form-item>

              </el-col>
              <el-col :span="12">
                <el-form-item label="北京时">
                  <el-input placeholder="北京时" :disabled="!showBj" v-model="form.bjTime"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary" html-type="submit" @click="bdsWeekWIS2UTC">
                  提交
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

export default {
  name: "BeijingToUTC",
  data() {
    return {
      form: {},
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
    }
  },
  created() {

    var bdsWeek = Math.floor(Math.random() * 1000 + 1);
    var bdsWIS = Math.floor(Math.random() * 604800 + 1);
    this.form.bd = bdsWeek + "周" + bdsWIS
  },
  methods: {
    bdsWeekWIS2UTC(bdsWeek, bdsWIS) {
      if (!this.showBj) {
        var bd = this.form.bd;
        var utc = this.form.utc;
        var arr = bd.split("周");
        bdsWeek = arr[0] * 1;
        bdsWIS = arr[1] * 1;

        var difFromBegin = bdsWeek * 604800 + bdsWIS;
        var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
        var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
        // 北京时间
        bdsBeginTime.setSeconds(ts)
        console.log("北京时间：", bdsBeginTime)

        // UTC
        var date = moment(bdsBeginTime.getUTCFullYear() + "-" + bdsBeginTime.getUTCMonth() + "-" + bdsBeginTime.getUTCDate()
          + " " + bdsBeginTime.getUTCHours() + ":" + bdsBeginTime.getUTCMinutes() + ":" + bdsBeginTime.getUTCSeconds()).format('YYYY-MM-DD HH:mm:ss');
        var da = new Date(date);//系统UTC时间
        console.log("da",date)
        var db = new Date(utc);//用户输入UTC时间
        if (da.getTime() === db.getTime()) {
          this.notifySuccess("正确", "转换正确")
          this.showBj = true;
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else {
        var utc = this.form.utc;
        var db = new Date(utc);
        console.log(db)
        var bjTime = this.form.bjTime;
        var bj = new Date(bjTime);
        console.log("bj",bj)
        console.log("utc",db.getFullYear(),db.getMonth()+1,db.getDate(),db.getHours(),db.getMinutes(),db.getSeconds())
        var date =bj.getUTCFullYear() + "-" + (bj.getUTCMonth()+1) + "-" + bj.getUTCDate()
          + " " + bj.getUTCHours() + ":" + bj.getUTCMinutes() + ":" + bj.getUTCSeconds()
        var date1 = new Date(date);
        console.log(date1,date)
        if (date1.getTime()===db.getTime()){
          this.notifySuccess("正确", "转换正确")
        }else {
          this.notifyError("转换错误，请重试")
        }
      }

    }


  },
}
</script>

<style scoped>

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
