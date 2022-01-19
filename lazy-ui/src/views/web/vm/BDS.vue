<template>
  <div  style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{exData.title}}</h2></div>
    <el-divider/>
    <div>
      <el-card style="position: absolute;left: 5px; top: 42px; width: 40%;height:700px;overflow:auto;">
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
      <span>卫星位置：<span style="color: #ffb061">{{ data.input.X }},{{ data.input.Y }},{{ data.input.Z }}</span></span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span>用户位置:<span style="color: #ffb061">{{ data.input2.X }},{{ data.input2.Y }},{{ data.input2.Z }}</span></span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span>频点参数 B1:<span style="color: #ffb061">{{ data.output.result1 }}</span></span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" v-model="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="heightFlag" v-model="form.height"
                            placeholder="计算高度角"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="fwjFlag" v-model="form.fwj"
                            placeholder="计算方位角"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="dxzjFlag" v-model="form.dxzj"
                            placeholder="地心张角"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="ccdFlag" v-model="form.ccd"
                            placeholder="计算刺穿点"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="ccdjwdFlag" v-model="form.ccdjwd"
                            placeholder="计算刺穿点的日固地磁经纬度"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="aiFlag" v-model="form.ai"
                            placeholder="Ai(i=1~9)"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="aoFlag" v-model="form.ao" placeholder="AO"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="vtecFlag" v-model="form.vtec"
                            placeholder="计算VTEC"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyFlag" v-model="form.ty"
                            placeholder="计算投影函数"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyvFlag" v-model="form.tyv"
                            placeholder="根据投影函数计算斜路径上的电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="yzFlag" v-model="form.yz"
                            placeholder="乘以频率转换因子"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="bdsFlag" v-model="form.bds"
                            placeholder="BDS的电离层改正值"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary" @click="handle">
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
    handle() {
      if (!this.heightFlag) {
        if ((this.form.height * 1) === this.data.output.result1) {
          this.notifySuccess("正确", "转换正确")
          this.fwjFlag = false
          this.heightFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.fwjFlag) {
        if ((this.form.fwj * 1) === this.data.output.result2) {
          this.notifySuccess("正确", "转换正确")
          this.dxzjFlag = false
          this.fwjFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.dxzjFlag) {
        if ((this.form.dxzj * 1) === this.data.output.result3) {
          this.notifySuccess("正确", "转换正确")
          this.ccdFlag = false
          this.dxzjFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.ccdFlag) {
        var v = this.data.output.result4 + "," + this.data.output.result5
        if (this.form.ccd === v) {
          this.notifySuccess("正确", "转换正确")
          this.ccdjwdFlag = false
          this.ccdFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.ccdjwdFlag) {
        var v = this.data.output.result6 + "," + this.data.output.result7
        if (this.form.ccdjwd === v) {
          this.notifySuccess("正确", "转换正确")
          this.aiFlag = false
          this.ccdjwdFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.aiFlag) {
        var v = this.data.output.result21
        let v1 = _.join(v, ",")
        console.log(v1)
        if (this.form.ai === v1) {
          this.notifySuccess("正确", "转换正确")
          this.aoFlag = false
          this.aiFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.aoFlag) {
        if ((this.form.ao * 1) === this.data.output.result8) {
          this.notifySuccess("正确", "转换正确")
          this.vtecFlag = false
          this.aoFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.vtecFlag) {
        if ((this.form.vtec * 1) === this.data.output.result9) {
          this.notifySuccess("正确", "转换正确")
          this.tyFlag = false
          this.vtecFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.tyFlag) {
        if ((this.form.ty * 1) === this.data.output.result15) {
          this.notifySuccess("正确", "转换正确")
          this.tyvFlag = false
          this.tyFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.tyvFlag) {
        if ((this.form.tyv * 1) === this.data.output.result10) {
          this.notifySuccess("正确", "转换正确")
          this.yzFlag = false
          this.tyvFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.yzFlag) {
        var v = this.data.output.result11 + "," + this.data.output.result12
        if (this.form.yz === v) {
          this.notifySuccess("正确", "转换正确")
          this.bdsFlag = false
          this.yzFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      }else if (!this.bdsFlag) {
        var v = this.data.output.result13 + "," + this.data.output.result14
        if (this.form.bds === v) {
          this.notifySuccess("正确", "转换正确")
        } else {
          this.notifyError("转换错误，请重试")
        }
      }

    }
  }
}
</script>

<style scoped>

</style>
