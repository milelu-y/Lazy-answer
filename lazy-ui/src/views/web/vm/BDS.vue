<template>
  <div  style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{exData.title}}</h2></div>
    <el-divider/>
    <div>
      <el-card style="position: absolute;left: 5px; top: 0; width: 40%;height:100%;overflow-y: auto;">
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="exData.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="exData.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div v-html="exData.process">

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
      </div>
      <div class="frequency" style="margin-top: 20px;">
        频点参数 B1：
        <el-input v-model="data.output.result1 " style="width: 220px"/>
        <el-button @click="refresh">刷新</el-button>
      </div>
    </el-card>
    <!-- <el-card style="margin-top: 10px">
      <span>用户位置:<span style="color: #ffb061">{{ data.input2.X }},{{ data.input2.Y }},{{ data.input2.Z }}</span></span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span>频点参数 B1:<span style="color: #ffb061">{{ data.output.result1 }}</span></span>
    </el-card> -->
    <el-card class="card" style="margin-top: 10px">
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" v-model="form" size="medium" label-width="100px">
            <el-row :gutter="10">
              <el-col :span="12">
                <el-row :gutter="10">
                  <el-col :span="19">
                    <el-form-item prop="time">
                      <el-input :style="{width: '100%'}" :disabled="heightFlag" v-model="form.height"
                                placeholder="计算高度角"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="5">
                    <el-button class="attribute" v-if="btn1" @click="handle">下一步</el-button>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-row :gutter="10">
                    <el-col :span="19">
                      <el-input :style="{width: '100%'}" :disabled="fwjFlag" v-model="form.fwj"
                            placeholder="计算方位角"></el-input>
                    </el-col>
                    <el-col :span="5">
                      <el-button class="attribute" v-if="btn2" @click="handle">下一步</el-button>
                    </el-col>
                  </el-row>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="dxzjFlag" v-model="form.dxzj"
                            placeholder="地心张角"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn3" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="ccdFlag" v-model="form.ccd"
                            placeholder="计算刺穿点"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn4" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="ccdjwdFlag" v-model="form.ccdjwd"
                            placeholder="计算刺穿点的日固地磁经纬度"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn5" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="aiFlag" v-model="form.ai"
                            placeholder="Ai(i=1~9)"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn6" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="aoFlag" v-model="form.ao" placeholder="AO"></el-input>
                </el-form-item>
              </el-col>
              <el_col :span="2">
                <el-button class="attribute" v-if="btn7" @click="handle">下一步</el-button>
              </el_col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="vtecFlag" v-model="form.vtec"
                            placeholder="计算VTEC"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn8" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyFlag" v-model="form.ty"
                            placeholder="计算投影函数"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn9" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyvFlag" v-model="form.tyv"
                            placeholder="根据投影函数计算斜路径上的电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn10" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="yzFlag" v-model="form.yz"
                            placeholder="乘以频率转换因子"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn11" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="15">
              <el-col :span="22">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="bdsFlag" v-model="form.bds"
                            placeholder="BDS的电离层改正值"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button class="attribute" v-if="btn12" @click="handle">下一步</el-button>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary" @click="handle1" v-if="btn13">
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
import {exp9_bds_dianLiCeng} from "@/api/vm/vmTest";
import _ from 'lodash'
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "BDS",
  data() {
    return {
      exData:{},
      isType: true,
      data: {},
      form: {},
      utcFlag: false,
      heightFlag: false,
      fwjFlag: true,
      dxzjFlag: true,
      ccdFlag: true,
      dlFlag: true,
      ccdjwdFlag: true,
      aiFlag: true,
      aoFlag: true,
      vtecFlag: true,
      tyFlag: true,
      tyvFlag: true,
      yzFlag: true,
      bdsFlag: true,
      btn1:true,
      btn2:false,
      btn3:false,
      btn4:false,
      btn5:false,
      btn6:false,
      btn7:false,
      btn8:false,
      btn9:false,
      btn10:false,
      btn11:false,
      btn12:false,
      btn13:false,
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.exData = response.data
    })
    exp9_bds_dianLiCeng().then(response => {
      this.data = response.data
      console.log(response)
    })
  },
  methods: {
    refresh(){
     exp9_bds_dianLiCeng().then(response => {
      this.data = response.data
    })
    this.form = {}
    this.heightFlag = false
    this. fwjFlag = true
    this.dxzjFlag = true
    this. ccdFlag = true
    this.dlFlag = true
    this.ccdjwdFlag = true
    this.aiFlag = true
    this.aoFlag = true
    this.vtecFlag = true
    this.tyFlag = true
    this.tyvFlag = true
    this.yzFlag = true
    this.bdsFlag = true
    this.btn1 = true
    this.btn2 = false
    this.btn3 = false
    this.btn4 = false
    this.btn5 = false
    this.btn6 = false
    this.btn7 = false
    this.btn8 = false
    this.btn9 = false
    this.btn10 = false
    this.btn11 = false
    this.btn12 = false
    this.btn13 = false
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
        var linheight = this.data.output.result1
        if ((this.form.height * 1) === this.data.output.result1) {
          this.notifySuccess("正确", "转换正确")
          this.fwjFlag = false
          this.heightFlag = true
          this.btn1 = false
          this.btn2 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ linheight, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.height = this.data.output.result1
          })
        }
      } else if (!this.fwjFlag) {
         var linheight = this.data.output.result2
        if ((this.form.fwj * 1) === this.data.output.result2) {
          this.notifySuccess("正确", "转换正确")
          this.dxzjFlag = false
          this.fwjFlag = true
          this.btn2 = false
          this.btn3 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ linheight, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.fwj = this.data.output.result2
          })
        }
      } else if (!this.dxzjFlag) {
        var linheight = this.data.output.result2
        if ((this.form.dxzj * 1) === this.data.output.result3) {
          this.notifySuccess("正确", "转换正确")
          this.ccdFlag = false
          this.dxzjFlag = true
          this.btn3 = false
          this.btn4 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ linheight, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.dxzj = this.data.output.result3
          })
        }
      } else if (!this.ccdFlag) {
        var v = this.data.output.result4 + "," + this.data.output.result5
        if (this.form.ccd === v) {
          this.notifySuccess("正确", "转换正确")
          this.ccdjwdFlag = false
          this.ccdFlag = true
          this.btn4 = false
          this.btn5 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ccd = this.data.output.result4 + "," + this.data.output.result5
          })
        }
      } else if (!this.ccdjwdFlag) {
        var v = this.data.output.result6 + "," + this.data.output.result7
        if (this.form.ccdjwd === v) {
          this.notifySuccess("正确", "转换正确")
          this.aiFlag = false
          this.ccdjwdFlag = true
          this.btn5 = false
          this.btn6 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ccdjwd = this.data.output.result6 + "," + this.data.output.result7
          })
        }
      } else if (!this.aiFlag) {
        var v = this.data.output.result21
        let v1 = _.join(v, ",")
        console.log(v1)
        if (this.form.ai === v1) {
          this.notifySuccess("正确", "转换正确")
          this.aoFlag = false
          this.aiFlag = true
          this.btn6 = false
          this.btn7 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v1, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ai = this.data.output.result21.join(",")
          })
        }
      } else if (!this.aoFlag) {
        var v = this.data.output.result8
        if ((this.form.ao * 1) === this.data.output.result8) {
          this.notifySuccess("正确", "转换正确")
          this.vtecFlag = false
          this.aoFlag = true
          this.btn7 = false
          this.btn8 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ao = this.data.output.result8
          })
        }
      } else if (!this.vtecFlag) {
        var v = this.data.output.result9
        if ((this.form.vtec * 1) === this.data.output.result9) {
          this.notifySuccess("正确", "转换正确")
          this.tyFlag = false
          this.vtecFlag = true
          this.btn8 = false
          this.btn9 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.vtec = this.data.output.result9
          })
        }
      } else if (!this.tyFlag) {
        var v = this.data.output.result15
        if ((this.form.ty * 1) === this.data.output.result15) {
          this.notifySuccess("正确", "转换正确")
          this.tyvFlag = false
          this.tyFlag = true
          this.btn9 = false
          this.btn10 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ty = this.data.output.result15
          })
        }
      } else if (!this.tyvFlag) {
        var v = this.data.output.result10
        if ((this.form.tyv * 1) === this.data.output.result10) {
          this.notifySuccess("正确", "转换正确")
          this.yzFlag = false
          this.tyvFlag = true
          this.btn10 = false
          this.btn11 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.tyv = this.data.output.result10
          })
        }
      } else if (!this.yzFlag) {
        var v = this.data.output.result11 + "," + this.data.output.result12
        if (this.form.yz === v) {
          this.notifySuccess("正确", "转换正确")
          this.bdsFlag = false
          this.yzFlag = true
          this.btn11 = false
          this.btn12 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.yz = this.data.output.result11 + "," + this.data.output.result12
          })
        }
      }else if (!this.bdsFlag) {
        var v = this.data.output.result13 + "," + this.data.output.result14
        if (this.form.bds === v) {
          this.notifySuccess("正确", "转换正确")
          this.yzFlag = true
          this.bdsFlag = true
          this.btn12 = false
          this.btn13 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.bds = this.data.output.result13 + "," + this.data.output.result14
          })
        }
      }

    }
  }
}
</script>

<style scoped>

</style>
