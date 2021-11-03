<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>北斗时与北京时的转换</h2></div>
    <div style="margin-top: 10px">
      <el-steps :current="current">
        <el-step v-for="item in steps" :key="item.title" :title="item.title" />
      </el-steps>
    </div>
    <div class="card" style="margin-top: 10px">
      <p>ps:请先将北斗时转换成UTC进行输入</p>
      <div style="text-align: center">
        <el>北斗时转换成UTC></el>
        <!--        <a>儒略日转格里高利时></a>-->
        <div style="padding-top: 25px">
          <el-form :form="form">
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol"
                             label="北斗时">
                  <el-input placeholder="请输入北斗时" v-model="form.bd"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="UTC">
                  <el-input placeholder="请输入UTC" v-model="form.utc"/>
                  <!--                  <a-date-picker show-time placeholder="选择时间" @change="onChange" @ok="onOk" />-->
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
const formItemLayout = {
  labelCol: {span: 8},
  wrapperCol: {span: 8},
};
const formTailLayout = {
  labelCol: {span: 4},
  wrapperCol: {span: 8, offset: 4},
};
import moment from 'moment'
export default {
  name: "BeijingToUTC",
  data() {
    return {
      form: {},
      formItemLayout,
      formTailLayout,
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

    var bdsWeek = Math.floor(Math.random()*1000+1);
    var bdsWIS = Math.floor(Math.random()*604800+1);
    this.form.bd=bdsWeek+"周"+bdsWIS
  },
  methods: {
    onChange(value, dateString) {
      console.log('Selected Time: ', value);
      console.log('Formatted Selected Time: ', dateString);
    },
    onOk(value) {
      console.log('onOk: ', value);
    },
    next() {
      this.current++;
    },
    prev() {
      this.current--;
    },
    bdsWeekWIS2UTC(bdsWeek,bdsWIS){
      var bd=  this.form.bd;
      var utc=  this.form.utc;
      var arr= bd.split("周");
      bdsWeek = arr[0]*1;
      bdsWIS = arr[1]*1;

      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      console.log("bdsBeginTime",bdsBeginTime)
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      console.log("ts",ts)
      // 北京时间
      bdsBeginTime.setSeconds(ts)
      console.log("bdsBeginTime",bdsBeginTime)
      // UTC
      console.log( bdsBeginTime.getUTCFullYear(), bdsBeginTime.getUTCMonth(), bdsBeginTime.getUTCDate(), bdsBeginTime.getUTCHours(),bdsBeginTime.getUTCMinutes(), bdsBeginTime.getUTCSeconds() )

     var date  =  moment(bdsBeginTime.getUTCFullYear()+"-"+bdsBeginTime.getUTCMonth()+"-"+bdsBeginTime.getUTCDate()
       + " "+bdsBeginTime.getUTCHours()+":"+bdsBeginTime.getUTCMinutes()+":"+bdsBeginTime.getUTCSeconds()).format('YYYY-MM-DD HH:mm:ss');
     console.log("date ",date)
      var da = new Date(date);
      var db = new Date(utc);
      if(da.getTime() === db.getTime()){
        this.notifySuccess("正确", "转换正确")
      }else{
        this.notifyError("转换错误，请重试")
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
