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
                  <el-input :style="{width: '100%'}" ref="BDT" v-model="BDT" placeholder="BDT钟差"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="GPST钟差" prop="time">
                  <el-input :style="{width: '100%'}" ref="GPST" :disabled="isType" v-model="GPST" placeholder="GPST钟差"></el-input>
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
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "Navigation",
  data() {
    return {
      isType: true,
      data: {},
      exData: {},
      BDT:null,
      GPST:null
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.exData = response.data
    })
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
          //this.notifySuccess("正确", "转换正确")
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            this.isType=false
            this.$refs.GPST.focus();
          })
        } else {
          this.notifyError("转换错误，请重试")
          this.$refs.BDT.focus()
        }
      }else {
        var v = this.GPST
        if ((v*1) ===this.data.output.result2){
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {
            //this.showBj = true;
            this.isType=false
            //this.$refs.GPST.focus();
            this.BDT=''
            this.GPST=''
          })

        } else {
          this.$refs.GPST.focus();
          this.notifyError("转换错误，请重试")
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
