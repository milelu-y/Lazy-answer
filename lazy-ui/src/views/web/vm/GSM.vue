<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>双频电离层改正实验</h2></div>
    <el-divider/>
    <div>
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
        </div>
        <img src="../../../assets/images/firnula/suan.png" />
      </el-card>
    </div>
    <el-card>
      <span >伪距:{{ data.input }}、TGD:{{data.input2}}</span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <p v-if="isType">ps:计算各频点的伪距修正值，得到修正后的伪距</p>
      <p v-else >ps:计算各频点的电离层延迟值</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="伪距" prop="time">
                  <el-input :disabled="!isType" :style="{width: '100%'}" v-model="wj" placeholder="伪距"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24" v-if="!isType">
              <el-col :span="24">
                <el-form-item label="电离层延迟" prop="time">
                  <el-input :style="{width: '100%'}" v-model="dl"  placeholder="电离层延迟"></el-input>
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
import {exp10_shuangpinDianLiCeng} from "@/api/vm/vmTest";

export default {
  name: "GSM",
  data(){
    return{
      isType:true,
      data:{},
      wj:null,
      dl:null,
    }
  },
  created() {
    exp10_shuangpinDianLiCeng().then(response=>{
      console.log(response)
      this.data=response.data
    })
  },
  methods:{
    handle(){
      if (this.isType){
        var v = this.wj
        if ((v*1) ===this.data.output.result1){
          this.notifySuccess("正确", "转换正确")
          this.isType=false
        } else {
          this.notifyError("转换错误，请重试")
        }
      }else {
        var v = this.dl
        if ((v*1) ===this.data.input3){
          this.notifySuccess("正确", "转换正确")
          this.isType=false
        } else {
          this.notifyError("转换错误，请重试")
        }
      }

    },
  }

}
</script>

<style scoped>

</style>
