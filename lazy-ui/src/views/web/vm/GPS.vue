<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{ exData.title }}</h2></div>
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
              <el-col :span="10">
                <el-row>
                  <el-col :span="22">
                    <el-form-item prop="time">
                      <el-input :style="{width: '100%'}" :disabled="heightFlag" v-model="form.height"
                                placeholder="计算高度角"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="2">
                    <i v-if="form.heightAnswer" style="color: #00ff80" class="el-icon-success"></i>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="10">
                <el-row>
                  <el-col :span="22">
                    <el-form-item prop="time">
                      <el-input :style="{width: '100%'}" :disabled="fwjFlag" v-model="form.fwj"
                                placeholder="计算方位角" ref="fwj"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="2">
                    <i v-if="form.fwjAnswer" style="color: #00ff80" class="el-icon-success"></i>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="23">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="ccdFlag" v-model="form.ccd"
                            placeholder="计算刺穿点" ref="ccd"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="1">
                <i v-if="form.ccdAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="23">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="A2Flag" ref="A2" v-model="form.A2"
                            placeholder="A2"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="1">
                <i v-if="form.a2Answer" style="color: #00ff80;text-align: center" class="el-icon-success"></i>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="23">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="A4Flag" ref="A4" v-model="form.A4"
                            placeholder="A4"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="1">
                <i v-if="form.a4Answer" style="color: #00ff80;text-align: center" class="el-icon-success"></i>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="23">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="czdlFlag" ref="czdl" v-model="form.czdl"
                            placeholder="垂直电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="1">
                <i v-if="form.czdlAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="23">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="tyFlag" ref="ty" v-model="form.ty"
                            placeholder="根据投影函数计算斜路径上的电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="1">
                <i v-if="form.tyAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="23">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" :disabled="yinziFlag" ref="yinzi" v-model="form.yinzi"
                            placeholder="乘以频率转换因子"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="1">
                <i v-if="form.yinziAnswer" style="color: #00ff80;text-align: center" class="el-icon-success"></i>
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
      }
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
    handle() {
      if (!this.heightFlag) {
        if ((this.form.height * 1) === this.data.output.result1) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.fwj.focus()
          })
          this.fwjFlag = false
          this.heightFlag = true
          this.form.heightAnswer = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.fwjFlag) {
        if ((this.form.fwj * 1) === this.data.output.result2) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.ccd.focus()
          })
          this.ccdFlag = false
          this.fwjFlag = true
          this.form.fwjAnswer = true
        } else {
          this.notifyError("转换错误，请重试")
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
          this.form.ccdAnswer = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.A2Flag) {
        if ((this.form.A2 * 1) === this.data.output.result5) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.A4.focus()
          })
          this.A2Flag = true
          this.A4Flag = false
          this.form.a2Answer = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.A4Flag) {
        if ((this.form.A4 * 1) === this.data.output.result6) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.czdl.focus()
          })
          this.A4Flag = true
          this.czdlFlag = false
          this.form.a4Answer = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.czdlFlag) {
        console.log(this.form.czdl*1)
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
          this.form.czdlAnswer = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.tyFlag) {
        if ((this.form.ty * 1) === this.data.output.result8) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.$refs.yinzi.focus()
          })
          this.tyFlag = true
          this.yinziFlag = false
          this.form.tyAnswer = true
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else if (!this.yinziFlag) {
        if ((this.form.yinzi * 1) === this.data.output.result9) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {

          })
          this.form.yinziAnswer = true
          this.yinziFlag = true
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
