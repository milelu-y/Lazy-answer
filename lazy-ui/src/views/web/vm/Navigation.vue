<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>导航电文计算卫星钟差实验</h2></div>
    <el-divider/>
    <el-card>
      <span>BDT： {{ data.input }}、GPST： {{ data.input2 }}</span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <p>ps:请输入BDT钟差与提示GPST钟差</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="BDT钟差" prop="time">
                  <el-input :style="{width: '100%'}" v-model="BDT" placeholder="BDT钟差"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="GPST钟差" prop="time">
                  <el-input :style="{width: '100%'}" :disabled="isType" v-model="GPST" placeholder="GPST钟差"></el-input>
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
import {exp7_dianwen} from "@/api/vm/vmTest";

export default {
  name: "Navigation",
  data() {
    return {
      isType: true,
      data: {},
      BDT:null,
      GPST:null
    }
  },
  created() {
    exp7_dianwen().then(response => {
      console.log(response)
      this.data = response.data
    })
  },
  methods:{
    handle(){
      if (this.isType){
        var v = this.BDT
        if ((v*1) ===this.data.output.result1){
          this.notifySuccess("正确", "转换正确")
          this.isType=false
        } else {
          this.notifyError("转换错误，请重试")
        }
      }else {
        var v = this.GPST
        if ((v*1) ===this.data.output.result2){
          this.notifySuccess("正确", "转换正确")
          this.isType=false
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
