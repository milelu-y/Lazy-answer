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
        <div style="padding-top:15px;" v-html="exData.process">

        </div>
      </el-card>
    </div>
    <div class="clock">
      <el-button @click="subitbd" size="small">计算</el-button>
      <el-button @click="subitst" size="small">钟差</el-button>
    </div>
    <div class="bodyall" v-show="markes">
      <el-card>
        <div class="navigation">
          BDT：<el-input  
            v-model="data.input"
            style="width:300px;margin-right:10px;"
            clearable/>
          GPST：<el-input  
            v-model="data.input2"
            style="width:300px;"
            clearable/>
            <el-button @click="refresh" class="properties">刷新</el-button>
        </div>
      </el-card>
      <el-card class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">请输入BDT钟差与GPST钟差</p>
        <div>
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="100px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item label="BDT钟差" prop="time">
                    <el-input :style="{width: '50%'}" ref="BDT" v-model="BDT" placeholder="BDT钟差" :disabled="timesbj"></el-input>
                    <el-button type="primary" @click="handle" class="properties" v-if="operation">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item label="GPST钟差" prop="time">
                    <el-input :style="{width: '50%'}" ref="GPST" v-model="GPST" placeholder="GPST钟差" :disabled="timesbj1"></el-input>
                    <el-button type="primary" @click="handle1" class="properties" v-if="operation1">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- <el-row>
                <el-col :span="24" :style="{ textAlign: 'center' }">
                  <el-button type="primary" @click="handle">
                    提交
                  </el-button>
                </el-col>
              </el-row> -->
            </el-form>
          </div>
        </div>
      </el-card>
    </div>

    <div class="bodyall" v-show="nummakes">
      <el-card>
        <div class="navigation">
          BDT钟差：<el-input  
            v-model="data.output.result1"
            style="width:300px;margin-right:10px;"
            clearable/>
          GPST钟差：<el-input  
            v-model="data.output.result2"
            style="width:300px;"
            clearable/>
            <el-button @click="refresh" class="properties">刷新</el-button>
        </div>
      </el-card>
      <el-card class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">请输入BDT参数与GPST参数</p>
        <div>
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="100px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item label="BDT参数" prop="time">
                    <el-input :style="{width: '50%'}"  v-model="bardes" placeholder="请输入BDT参数" :disabled="timesbj"></el-input>
                    <el-button type="primary" @click="subitm" class="properties" v-if="operation">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item label="GPST参数" prop="time">
                    <el-input :style="{width: '50%'}"  v-model="garpage" placeholder="请输入GPST参数" :disabled="timesbj1"></el-input>
                    <el-button type="primary" @click="subitm1" class="properties" v-if="operation1">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- <el-row>
                <el-col :span="24" :style="{ textAlign: 'center' }">
                  <el-button type="primary" @click="handle">
                    提交
                  </el-button>
                </el-col>
              </el-row> -->
            </el-form>
          </div>
        </div>
      </el-card>
    </div>

  </div>
</template>

<script>
import {exp7_dianwen} from "@/api/vm/vmTest";
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "Navigation",
  data() {
    return {
      data: {},
      exData: {},
      BDT:null,
      GPST:null,
      markes:true,
      nummakes:false,
      bardes:'',
      garpage:'',
      timesbj:false,
      timesbj1:true,
      operation:true,
      operation1:false

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
    //刷新按钮
    refresh(){
      exp7_dianwen().then(response => {
        this.data = response.data
      })
        this.BDT=null
        this.GPST=null
        this.bardes = ''
        this.garpage = ''
        this.timesbj = false
        this.timesbj1 = true
        this.operation = true
        this.operation1 = false
    },
    handle(){
        var v = this.data.output.result1
        if ((this.BDT*1) ===this.data.output.result1){
          //this.notifySuccess("正确", "转换正确")
          this.$confirm('转换正确!', '提示', {
            type: 'success'
          }).then(() => {
              this.timesbj = true
              this.timesbj1 = false
              this.operation = false
              this.operation1 = true
          })
        } else {
          this.$refs.BDT.focus()
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.BDT = this.data.output.result1
          })
        }
      },
      handle1(){
        var v = this.data.output.result2
        if ((this.GPST*1) ===this.data.output.result2){
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {
              this.timesbj = true
              this.timesbj1 = true
              this.operation = false
              this.operation1 = false
          })

        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.GPST = this.data.output.result2
          })
        }
      },
      //计算按钮切换换算
      subitbd(){
        exp7_dianwen().then(response => {
          this.data = response.data
        })
        this.markes = true
        this.nummakes = false
         this.BDT = null
        this.GPST = null
        this.timesbj = false
        this.timesbj1 = true
        this.operation = true
        this.operation1 = false
      },
      //钟差按钮切换
      subitst(){
        exp7_dianwen().then(response => {
          this.data = response.data
        })
        this.markes = false
        this.nummakes = true
        this.bardes = ''
        this.garpage = ''
        this.timesbj = false
        this.timesbj1 = true
        this.operation = true
        this.operation1 = false
      },
      //BDT提交按钮
      subitm(){
        var v = this.data.input
        if (this.bardes === this.data.input){
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {
              this.timesbj = true
              this.timesbj1 = false
              this.operation = false
              this.operation1 = true
          })

        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.bardes = this.data.input
          })
        }
      },
      //GPST提交按钮
      subitm1(){
        var v = this.data.input2
        if (this.garpage === this.data.input2){
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {
              this.timesbj = true
              this.timesbj1 = true
              this.operation = false
              this.operation1 = false
          })

        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.garpage = this.data.input2
          })
        }
      }
      // else {
      //   var v = this.data.output.result2
      //   if ((this.GPST*1) ===this.data.output.result2){
      //     this.$confirm('转换正确!', '提示', {
      //       confirmButtonText: '完成',
      //       type: 'success'
      //     }).then(() => {
      //       //this.showBj = true;
      //       this.isType=false
      //       //this.$refs.GPST.focus();
      //       this.BDT=''
      //       this.GPST=''
      //     })

      //   } else {
      //     this.$refs.GPST.focus();
      //     this.$confirm('转换错误，正确答案为：'+ v, '提示',{
      //       confirmButtonText: '确定',
      //       type:'warning'
      //     }).then(()=>{
      //       this.GPST = this.data.output.result2
      //     })
      //   }
      // }
  }
}
</script>

<style scoped>
 .navigation{
    display: flex;
    line-height: 40px;
    padding: 0 15px;
 }
 .properties{
   margin-left: 10px;
 }
 .clock{
   margin-bottom: 15px;
 }
</style>
