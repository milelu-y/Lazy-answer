<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>GPS电离层延迟改正实验</h2></div>
    <el-divider/>
    <div>
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
        </div>
        <img v-if="isType" src="../../../assets/images/firnula/BDTTOGPST.png" />
      </el-card>
    </div>
    <el-card>
      <span>卫星位置：{{ data.input.X }},{{ data.input.Y }},{{ data.input.Z }}</span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span>用户位置：{{ data.input2.X }},{{ data.input2.Y }},{{ data.input2.Z }}</span>
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
                  <el-input :style="{width: '100%'}" :disabled="ccdFlag" v-model="form.ccd"
                            placeholder="计算刺穿点"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="A2Flag" v-model="form.A2" placeholder="A2"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="A4Flag" v-model="form.A4" placeholder="A4"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="czdlFlag" v-model="form.czdl"
                            placeholder="垂直电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyFlag" v-model="form.ty"
                            placeholder="根据投影函数计算斜路径上的电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="yinziFlag" v-model="form.yinzi"
                            placeholder="乘以频率转换因子"></el-input>
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
import {exp8_gps_dianLiCeng} from "@/api/vm/vmTest";

export default {
  name: "GPS",
  data() {
    return {
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
        fwj: null,
        ccd: null,
        dljwd: null,
        A2: null,
        A4: null,
        czdl: null,
        ty: null,
        yinzi: null,
      }
    }
  },
  created() {
    exp8_gps_dianLiCeng().then(response => {
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
          this.ccdFlag = false
          this.fwjFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.ccdFlag) {
        var v = this.data.output.result3 + "," + this.data.output.result4
        if (this.form.ccd === v) {
          this.notifySuccess("正确", "转换正确")
          this.A2Flag = false
          this.ccdFlag = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.A2Flag) {
        if ((this.form.A2 * 1) === this.data.output.result5) {
          this.notifySuccess("正确", "转换正确")
          this.A2Flag = true
          this.A4Flag = false
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.A4Flag) {
        if ((this.form.A4 * 1) === this.data.output.result6) {
          this.notifySuccess("正确", "转换正确")
          this.A4Flag = true
          this.czdlFlag = false
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.czdlFlag) {
        if ((this.form.A4 * 1) === this.data.output.result7) {
          this.notifySuccess("正确", "转换正确")
          this.czdlFlag = true
          this.tyFlag = false
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.tyFlag) {
        if ((this.form.ty * 1) === this.data.output.result8) {
          this.notifySuccess("正确", "转换正确")
          this.tyFlag = true
          this.yinziFlag = false
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.yinziFlag) {
        if ((this.form.yinzi * 1) === this.data.output.result9) {
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
