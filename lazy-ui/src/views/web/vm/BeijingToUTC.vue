<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>北斗时与北京时的转换</h2>
      <el-button @click="formation1()" size="small">北斗时</el-button>
      <el-button @click="formation2()" size="small">UTC</el-button>
      <el-button @click="formation3()" size="small">北京时</el-button>
    </div>
    <!--    <div style="margin-top: 10px">-->
    <!--      <el-steps :current="current">-->
    <!--        <el-step v-for="item in steps" :key="item.title" :title="item.title"/>-->
    <!--      </el-steps>-->
    <!--    </div>-->
    <div style="position: absolute;left: 5px; top: 0; width: 40%;height:100%;overflow-y: auto;">
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="data.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="data.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div style="" v-html="data.process">

        </div>
      </el-card>
    </div>
    <div class="card" style="margin-top: 10px;" v-show="isnows">
      <!--      <p>输入格式:YYYY-M-D HH:ss:mm</p>-->
      <el-form label-width="80px" v-if="isbacks">
        <el-col :span="24">
          <el-form-item label="北斗时" prop="bd">
            <el-input v-model="form.bd" style="width: 300px"></el-input>
            <el-button @click="generateData">刷新</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="UTC" prop="utc" >
            <!-- <el-input v-model="form.utc" placeholder="输入北斗时转换的UTC" style="width: 200px"></el-input> -->
            <el-date-picker
              :style="{width: '300px'}"
              v-model="form.utc"
              type="datetime"
              placeholder="选择UTC时间"
              clearable
              @change="timeChange"
              :disabled="timesbj"
            >
            </el-date-picker>
            <el-button @click="submitUTC" v-if="operation">转换</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="北京时" prop="bjTime">
            <!-- <el-input placeholder="输入UTC转换的北京时间" ref="bjInput" v-model="form.bjTime" style="width: 200px"/> -->
            <el-date-picker
              :style="{width: '300px'}"
              v-model="form.bjTime"
              type="datetime"
              placeholder="选择UTC转换的北京时间"
              clearable
              @change="timeChange"
               ref="bjInput"
               :disabled="timesbj1"
            >
            </el-date-picker>
            <el-button @click="submitBEIJIN" v-if="operation1">转换</el-button>
          </el-form-item>
        </el-col>
      </el-form>

       <!-- <el-form label-width="80px" v-if="isbackse">
          <el-col :span="24">
          <el-form-item label="UTC" prop="utc">
            <el-input v-model="form.utc" placeholder="输入北斗时转换的UTC" style="width: 200px"></el-input>
            <el-button @click="submitUTC">刷新</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="北京时" prop="bjTime">
            <el-input placeholder="输入UTC转换的北京时间" ref="bjInput" v-model="form.bjTime" style="width: 200px"/>
            <el-button @click="submitBEIJIN">提交</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="北斗时" prop="bd">
            <el-input v-model="form.bd" style="width: 200px"></el-input>
            <el-button @click="generateData">提交</el-button>
          </el-form-item>
        </el-col>
      </el-form> -->

       <!-- <el-form label-width="80px" v-if="ismacks">
         <el-col :span="24">
          <el-form-item label="北京时" prop="bjTime">
            <el-input placeholder="输入UTC转换的北京时间" ref="bjInput" v-model="form.bjTime" style="width: 200px"/>
            <el-button @click="submitBEIJIN">刷新</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="北斗时" prop="bd">
            <el-input v-model="form.bd" style="width: 200px"></el-input>
            <el-button @click="generateData">提交</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="UTC" prop="utc">
            <el-input v-model="form.utc" placeholder="输入北斗时转换的UTC" style="width: 200px"></el-input>
            <el-button @click="submitUTC">提交</el-button>
          </el-form-item>
        </el-col>
      </el-form> -->
      <!-- <el-row>
        <el-button type="primary" size="mini" plain @click="marks">北斗时</el-button>
        <el-button type="success" size="mini" plain @click="markse">UTC</el-button>
        <el-button type="info" size="mini" plain @click="marksa">北京时</el-button>
      </el-row> -->
    </div>

    <div class="card" style="margin-top: 10px;" v-show ="isnums">
      <!--      <p>输入格式:YYYY-M-D HH:ss:mm</p>-->
      <el-form label-width="80px" v-if="isbacks">
        <el-col :span="24">
          <el-form-item label="UTC" prop="utc">
            <el-input v-model="form.utc" placeholder="输入北斗时转换的UTC" style="width: 300px"></el-input>
            <el-button @click="getUTCdates">刷新</el-button>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="北斗时" prop="bd">
            <el-input placeholder="请输入北斗时" v-model="form.bd" style="width: 300px" :disabled="timesbj"></el-input>
            <el-button @click="getbtime" v-if="operation">转换</el-button>
          </el-form-item>
        </el-col>
        
        <el-col :span="24">
          <el-form-item label="北京时" prop="bjTime">
            <!-- <el-input placeholder="输入UTC转换的北京时间" ref="bjInput" v-model="form.bjTime" style="width: 200px"/> -->
            <el-date-picker
              :style="{width: '300px'}"
              v-model="form.bjTime"
              type="datetime"
              placeholder="选择UTC转换的北京时间"
              clearable
              @change="timeChange"
               ref="bjInput"
               :disabled="timesbj1"
            >
            </el-date-picker>
            <el-button @click="submitbjtime" v-if="operation1">转换</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </div>

    <div class="card" style="margin-top: 10px;" v-show ="iscards">
      <!--      <p>输入格式:YYYY-M-D HH:ss:mm</p>-->
      <el-form label-width="80px" v-if="isbacks">

        <el-col :span="24">
          <el-form-item label="北京时" prop="bjTime">
            <el-input  ref="bjInput" v-model="form.bjTime" style="width: 300px"/>
            <el-button @click="getbjdate">刷新</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="UTC" prop="utc">
            <!-- <el-input v-model="form.utc" placeholder="输入北斗时转换的UTC" style="width: 200px"></el-input> -->
            <el-date-picker
              :style="{width: '300px'}"
              v-model="form.utc"
              type="datetime"
              placeholder="选择UTC时间"
              clearable
              @change="timeChange"
              :disabled="timesbj"
            >
            </el-date-picker>
            <el-button @click="submitUTCnums" v-if="operation">转换</el-button>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="北斗时" prop="bd">
            <el-input placeholder="请输入北斗时" v-model="form.bd" :disabled="timesbj1" style="width: 300px"></el-input>
            <el-button @click="submitBD" v-if="operation1">转换</el-button>
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
      form: {bd: '', bjTime: '', utc:''},
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
      beiJinTime: '',
      bdtime:'',
      isbacks:true,
      isbackse:false,
      ismacks:false,
      isnows:true,
      isnums:false,
      iscards:false,
      timesbj:false,
      timesbj1:true,
      operation:true,
      operation1:false
      // rules:{
      //   bd: [
      //     {required: true, message: '请输入北斗时', trigger: 'blur'}
      //     ],
      //   utc: [
      //     {required: true, message: '请输入UTC', trigger: 'blur'}
      //     ],
      //   bjTime: [
      //     {required: true, message: '请输入北京时', trigger: 'blur'}
      //     ],
      // }
    }
  },
  created() {
    //默认北斗时
    var bdsWeek = Math.floor(Math.random() * 1000 + 1);
    var bdsWIS = Math.floor(Math.random() * 604800 + 1);
    this.form.bd = bdsWeek + "周" + bdsWIS
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      console.log("qeqw",response)
      this.data = response.data
    })

  },
  methods: {
    generateData() {
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      this.form.bd = bdsWeek + "周" + bdsWIS
      this.form.bjTime = ''
      this.form.utc = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    submitUTC() {
      var bd = this.form.bd;
      console.log('bd',bd)
      var utc = this.form.utc;
      
      var arr = bd.split("周");
      console.log('arr',arr)
      let bdsWeek = arr[0] * 1;
      let bdsWIS = arr[1] * 1;
      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      console.log('11',difFromBegin)
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      console.log('事件',bdsBeginTime.getSeconds())
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      console.log('ts',ts)
      // 北京时间
      bdsBeginTime.setSeconds(ts)
      console.log('qqq',bdsBeginTime.setSeconds(ts))
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
        this.timesbj = true
        this.timesbj1 = false
        this.operation = false
        this.operation1 = true
      } else {
        this.$confirm('转换错误，正确答案为：' + utcTime, '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          this.form.utc = this.utcTime
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
          this.timesbj = true
          this.timesbj1 = true
          this.operation = false
          this.operation1 = false
        } else {
          this.$confirm('转换错误，正确答案为：' + systemBeiJinTime, '提示', {
            confirmButtonText: '确定',
            type: 'warning'
          }).then(() => {
            this.form.bjTime = this.beiJinTime
          })
        }
      }
    },
     timeChange(v) {
      if (v) {
        this.active = 1
      } else {
        this.active = 0
      }
    },
    // 北斗时按钮
    formation1(){
      this.isnows = true
      this.isnums = false
      this.iscards = false
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      this.form.bd = bdsWeek + "周" + bdsWIS
      this.form.bjTime = ''
      this.form.utc = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
      
    },
    // UTC按钮
    formation2(){
      this.isnows = false
      this.isnums = true
      this.iscards = false
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      this.bdtime = bdsWeek + "周" + bdsWIS;
      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      bdsBeginTime.setSeconds(ts)
      this.beiJinTime = parseTime(bdsBeginTime, '{y}-{m}-{d} {h}:{i}:{s}')
      var utcTime = moment(bdsBeginTime).utc().format('YYYY-MM-DD HH:mm:ss')
      this.form.utc = utcTime
      this.form.bd = ''
      this.form.bjTime = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //UTC时下北斗提交按钮
    getbtime(){
      if (this.form.bd == '') {
        return;
      }
      if (this.form.bd == this.bdtime) {
        this.notifySuccess("正确", "转换正确")
        this.timesbj = true
        this.timesbj1 = false
        this.operation = false
        this.operation1 = true
      } else {
        this.$confirm('转换错误，正确答案为：' + this.bdtime, '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          this.form.bd = this.bdtime
        })
      }
    },
    //UTC时下北京时提交按钮
    submitbjtime(){
      if (this.form.bjTime == '') {
        return;
      }
      if (this.form.bjTime == this.beiJinTime) {
        this.notifySuccess("正确", "转换正确")
        this.timesbj = true
        this.timesbj1 = true
        this.operation = false
        this.operation1 = false
      } else {
        this.$confirm('转换错误，正确答案为：' + this.beiJinTime, '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          this.form.bjTime = this.beiJinTime
        })
      }
    },
    //切换北京时按钮
     formation3(){
      this.iscards = true
      this.isnows = false
      this.isnums = false
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      this.bdtime = bdsWeek + "周" + bdsWIS;
      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      bdsBeginTime.setSeconds(ts)
      var beiJinTime = parseTime(bdsBeginTime, '{y}-{m}-{d} {h}:{i}:{s}')
      this.utcTime = moment(bdsBeginTime).utc().format('YYYY-MM-DD HH:mm:ss')
      this.form.bjTime = beiJinTime
      this.form.bd = ''
      this.form.utc = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //北京时下UTC提交按钮
    submitUTCnums(){
      if (this.form.utc == '') {
        return;
      }
      if (this.form.utc == this.utcTime) {
        this.notifySuccess("正确", "转换正确")
        this.timesbj = true
        this.timesbj1 = false
        this.operation = false
        this.operation1 = true
      } else {
        this.$confirm('转换错误，正确答案为：' + this.utcTime, '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          this.form.utc = this.utcTime
        })
      }
    },
    //北京时下北斗提交按钮
    submitBD(){
      if (this.form.bd == '') {
        return;
      }
      if (this.form.bd == this.bdtime) {
        this.notifySuccess("正确", "转换正确")
        this.timesbj = true
        this.timesbj1 = true
        this.operation = false
        this.operation1 = false
      } else {
        this.$confirm('转换错误，正确答案为：' + this.bdtime, '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          this.form.bd = this.bdtime
        })
      }
    },
    //刷新默认的UCT时间
    getUTCdates(){
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      bdsBeginTime.setSeconds(ts)
      this.beiJinTime = parseTime(bdsBeginTime, '{y}-{m}-{d} {h}:{i}:{s}')
      var utcTime = moment(bdsBeginTime).utc().format('YYYY-MM-DD HH:mm:ss')
      this.form.utc = utcTime
      this.form.bd = ''
      this.form.bjTime = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //刷新默认的北京时间
    getbjdate(){
      var bdsWeek = Math.floor(Math.random() * 1000 + 1);
      var bdsWIS = Math.floor(Math.random() * 604800 + 1);
      var difFromBegin = bdsWeek * 604800 + bdsWIS;
      var bdsBeginTime = new Date(2006, 1, 1, 0, 0, 0);
      var ts = bdsBeginTime.getSeconds() + difFromBegin - 4.0
      bdsBeginTime.setSeconds(ts)
      var beiJinTime = parseTime(bdsBeginTime, '{y}-{m}-{d} {h}:{i}:{s}')
      // var utcTime = moment(bdsBeginTime).utc().format('YYYY-MM-DD HH:mm:ss')
      this.form.bjTime = beiJinTime
      this.form.bd = ''
      this.form.utc = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
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
