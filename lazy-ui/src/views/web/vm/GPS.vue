<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{ exData.title }}</h2></div>
    <el-divider/>
    <div>
      <el-card style="position: absolute;left: 5px; top: 0; width: 40%;height:100%;overflow-y: auto;">
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="exData.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="exData.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div style="" v-html="exData.process">

        </div>
      </el-card>
    </div>
    <el-card>
      <div class="satellite">
        卫星位置：
        <el-input v-model="data.input.X " style="width: 220px"/>
        <el-input v-model="data.input.Y " style="width: 220px"/>
        <el-input v-model="data.input.Z " style="width: 220px"/>
      </div>
      <div class="user" style="margin-top: 20px;">
        用户位置：
        <el-input v-model="data.input2.X " style="width: 220px"/>
        <el-input v-model="data.input2.Y " style="width: 220px"/>
        <el-input v-model="data.input2.Z " style="width: 220px"/>
        <el-button @click="refresh">刷新</el-button>
      </div>
      
      <!-- <span>卫星位置：{{ data.input.X }},{{ data.input.Y }},{{ data.input.Z }}</span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span>用户位置：{{ data.input2.X }},{{ data.input2.Y }},{{ data.input2.Z }}</span> -->
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <div>
        <div style="padding-top: 25px;">
          <el-form ref="form" v-model="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="10">
                <el-row :gutter="10">
                  <el-col :span="22">
                    <el-form-item prop="time">
                      <el-input :style="{width: '100%'}" :disabled="heightFlag" v-model="form.height"
                                placeholder="计算高度角"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="2">
                    <!-- <i v-if="form.heightAnswer" style="color: #00ff80" class="el-icon-success"></i> -->
                    <el-button class="attribute" v-if="btn1" @click="handle">下一步</el-button>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="10">
                <el-row :gutter="10">
                  <el-col :span="22">
                    <el-form-item prop="time">
                      <el-input :style="{width: '100%'}" :disabled="fwjFlag" v-model="form.fwj"
                                placeholder="计算方位角" ref="fwj"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="2">
                    <el-button class="attribute" v-if="btn2" @click="handle">下一步</el-button>
                    <!-- <i v-if="form.fwjAnswer" style="color: #00ff80" class="el-icon-success"></i> -->
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="19">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="ccdFlag" v-model="form.ccd"
                            placeholder="计算刺穿点" ref="ccd"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <!-- <i v-if="form.ccdAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i> -->
                <el-button class="attribute" v-if="btn3" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="19">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="A2Flag" ref="A2" v-model="form.A2"
                            placeholder="A2"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-button class="attribute" v-if="btn4" @click="handle">下一步</el-button>
                <!-- <i v-if="form.a2Answer" style="color: #00ff80;text-align: center" class="el-icon-success"></i> -->
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="19">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="A4Flag" ref="A4" v-model="form.A4"
                            placeholder="A4"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-button class="attribute" v-if="btn5" @click="handle">下一步</el-button>
                <!-- <i v-if="form.a4Answer" style="color: #00ff80;text-align: center" class="el-icon-success"></i> -->
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="19">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="czdlFlag" ref="czdl" v-model="form.czdl"
                            placeholder="垂直电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <!-- <i v-if="form.czdlAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i> -->
                <el-button class="attribute" v-if="btn6" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="19">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyFlag" ref="ty" v-model="form.ty"
                            placeholder="根据投影函数计算斜路径上的电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-button class="attribute" v-if="btn7" @click="handle">下一步</el-button>
                <!-- <i v-if="form.tyAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i> -->
              </el-col>
            </el-row>
            <el-row :gutter="10">
              <el-col :span="19">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="yinziFlag" ref="yinzi" v-model="form.yinzi"
                            placeholder="乘以频率转换因子"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <!-- <i v-if="form.yinziAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i> -->
                <el-button class="attribute" v-if="btn8" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary" @click="handle1" v-if="btn9">
                  提交
                </el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import {exp8_gps_dianLiCeng} from "@/api/vm/vmTest";
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "GPS",
  data() {
    return {
      exData: {},
      isType: true,
      data: {},
      heightFlag: false,
      fwjFlag: true,
      ccdFlag: true,
      dljwdFlag: true,
      A2Flag: true,
      A4Flag: true,
      czdlFlag: true,
      tyFlag: true,
      yinziFlag: true,
      form: {
        height: null,
        heightAnswer: false,
        fwj: null,
        fwjAnswer: false,
        ccd: null,
        ccdAnswer: false,
        dljwd: null,
        A2: null,
        A4: null,
        czdl: null,
        ty: null,
        yinzi: null,
      },
      btn1:true,
      btn2:false,
      btn3:false,
      btn4:false,
      btn5:false,
      btn6:false,
      btn7:false,
      btn8:false,
      btn9:false,
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.exData = response.data
    })
    exp8_gps_dianLiCeng().then(response => {
      this.data = response.data
      console.log(response)

    })
  },
  methods: {
    //刷新页面
    refresh(){
      exp8_gps_dianLiCeng().then(response => {
      this.data = response.data
    })
    Object.assign(this.$data.form, this.$options.data().form)
    this.form.heightAnswer = false
    this.heightFlag = false,
    this.fwjFlag = true
    this.ccdFlag = true
    this.dljwdFlag = true
    this.A2Flag = true
    this.A4Flag = true
    this.czdlFlag = true
    this.tyFlag = true
    this.yinziFlag = true
    this.btn1 = true
    this.btn2 = false
    this.btn3 = false
    this.btn4 = false
    this.btn5 = false
    this.btn6 = false
    this.btn7 = false
    this.btn8 = false
    this.btn9 = false
    },
    handle1(){
      this.$confirm('转换正确，点击刷新按钮重新答题!', '提示', {
            confirmButtonText: '确定',
            type: 'success'
          }).then(() => {
    
          })
    },
    handle() {
      if (!this.heightFlag) {
        var v = this.data.output.result1
        if ((this.form.height * 1) === this.data.output.result1) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.fwj.focus()
          })
          this.fwjFlag = false
          this.heightFlag = true
          this.btn1 = false
          this.btn2 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.height = this.data.output.result1
          })
        }
      } else if (!this.fwjFlag) {
        var v = this.data.output.result2
        if ((this.form.fwj * 1) === this.data.output.result2) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.ccd.focus()
          })
          this.ccdFlag = false
          this.fwjFlag = true
          this.btn2 = false
          this.btn3 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.fwj = this.data.output.result2
          })
        }
      } else if (!this.ccdFlag) {
        var v = this.data.output.result3 + "," + this.data.output.result4
        if (this.form.ccd === v) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.A2.focus()
          })
          this.A2Flag = false
          this.ccdFlag = true
          this.btn3 = false
          this.btn4 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ccd = this.data.output.result3 + "," + this.data.output.result4
          })
        }
      } else if (!this.A2Flag) {
        var v = this.data.output.result5
        if ((this.form.A2 * 1) === this.data.output.result5) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.A4.focus()
          })
          this.A2Flag = true
          this.A4Flag = false
          this.btn4 = false
          this.btn5 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.A2 = this.data.output.result5
          })
        }
      } else if (!this.A4Flag) {
        var v = this.data.output.result6
        if ((this.form.A4 * 1) === this.data.output.result6) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.czdl.focus()
          })
          this.A4Flag = true
          this.czdlFlag = false
          this.btn5 = false
          this.btn6 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.A4 = this.data.output.result6
          })
        }
      } else if (!this.czdlFlag) {
        console.log(this.form.czdl*1)
        var v = this.data.output.result7
        console.log((this.form.czdl * 1) === this.data.output.result7)
        if ((this.form.czdl * 1) === this.data.output.result7) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.ty.focus()
          })
          this.czdlFlag = true
          this.tyFlag = false
          this.btn6 = false
          this.btn7 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.czdl = this.data.output.result7
          })
        }
      } else if (!this.tyFlag) {
        var v = this.data.output.result8
        if ((this.form.ty * 1) === this.data.output.result8) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.yinzi.focus()
          })
          this.tyFlag = true
          this.yinziFlag = false
          this.btn7 = false
          this.btn8 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ty = this.data.output.result8
          })
        }
      } else if (!this.yinziFlag) {
        var v = this.data.output.result9
        if ((this.form.yinzi * 1) === this.data.output.result9) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {

          })
          this.yinziFlag = true
          this.btn8 = false
          this.btn9 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.yinzi = this.data.output.result9
          })
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
