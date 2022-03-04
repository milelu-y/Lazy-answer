<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{exData.title}}</h2></div>
    <el-divider/>
    <div>
      <el-card style="position:absolute;left:5px;top:0; width:40%;height:100%;overflow-y:auto;overflow-x:auto;">
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
    <div class="clock">
      <el-button @click="subitfront" size="small">修正前</el-button>
      <el-button @click="subitafter" size="small">修正后</el-button>
    </div>
    <div class="allbody" v-show= "frontlist">
      <el-card>
        <div class="user" style="margin-top: 20px;">
          伪距：
          <el-input v-model="data.input " style="width: 220px"/>
        </div>
        <div class="frequency" style="margin-top: 20px;">
          TGD：
          <el-input v-model="data.input2 " style="width: 220px"/>
          <el-button @click="refresh" class="properties">刷新</el-button>
        </div>
        <!-- <span>伪距:{{ data.input }}、TGD:{{ data.input2 }}</span> -->
      </el-card>

      <el-card class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">计算各频点修正后的伪距,及各频点的电离层延迟值</p>
        <!-- <p v-else>计算各频点的电离层延迟值</p> -->
        <div>
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="100px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item label="修正后的伪距" prop="time">
                    <el-input  :style="{width: '50%'}"  v-model="wj" :disabled="timesbj" placeholder="请输入修正后的伪距"></el-input>
                    <el-button type="primary" @click="handle" class="properties" v-if="operation">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24" >
                <el-col :span="24">
                  <el-form-item label="电离层延迟" prop="time">
                    <el-input :style="{width: '50%'}" v-model="dl" :disabled="timesbj1" placeholder="请输入电离层延迟"></el-input>
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
    
    <div class="allbody" v-show= "afterlist">
      <el-card>
        <div class="user" style="margin-top: 20px;">
          修正伪距值：
          <el-input v-model="data.output.result1" style="width: 220px"/>
        </div>
        <div class="frequency" style="margin-top: 20px;">
          电离层延迟：
          <el-input v-model="data.input3" style="width: 220px"/>
          <el-button @click="refresh" class="properties">刷新</el-button>
        </div>
        <!-- <span>伪距:{{ data.input }}、TGD:{{ data.input2 }}</span> -->
      </el-card>

      <el-card class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">计算各频点修正后的伪距,及各频点的电离层延迟值</p>
        <!-- <p v-else>计算各频点的电离层延迟值</p> -->
        <div>
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="100px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item label="伪距" prop="time">
                    <el-input  :style="{width: '50%'}"  v-model="afternum" :disabled="timesbj" placeholder="请输入伪距"></el-input>
                    <el-button type="primary" @click="submitafter"  class="properties" v-if="operation">
                     转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24" >
                <el-col :span="24">
                  <el-form-item label="TGD" prop="time">
                    <el-input :style="{width: '50%'}" v-model="aftername" :disabled="timesbj1" placeholder="请输入TGD"></el-input>
                    <el-button type="primary" @click="submitafter1" class="properties" v-if="operation1">
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
import {exp10_shuangpinDianLiCeng} from "@/api/vm/vmTest";
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "GSM",
  data() {
    return {
      exData:{},
      // getType:false,
      // isType: true,
      data: {},
      wj: null,
      dl: null,
      frontlist:true,
      afterlist:false,
      afternum:null,
      aftername:null,
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
    exp10_shuangpinDianLiCeng().then(response => {
      console.log(response)
      this.data = response.data
    })
  },
  methods: {
    //刷新按钮
    refresh(){
      exp10_shuangpinDianLiCeng().then(response => {
        console.log(response)
        this.data = response.data
      })
      this.afternum = null
      this.aftername = null
      this.wj = null
      this.dl = null
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    handle() {
      var v = this.data.output.result1
      if ((this.wj * 1) === this.data.output.result1) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '确定',
          type: 'success'
        }).then(() => {
          this.timesbj = true
          this.timesbj1 = false
          this.operation = false
          this.operation1 = true
        })
        this.getType = true
        this.isType = false
      } else {
        this.$confirm('转换错误，正确答案为：'+ v, '提示',{
          confirmButtonText: '确定',
          type:'warning'
        }).then(()=>{
          this.wj = this.data.output.result1
        })
      }
    },
      handle1(){
        var v = this.data.input3
        if ((this.dl * 1) === this.data.input3) {
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
            this.dl = this.data.input3
          })
        }
      },
      //修正前按钮
      subitfront(){
        exp10_shuangpinDianLiCeng().then(response => {
          console.log(response)
          this.data = response.data
        })
        this.wj = null
        this.dl = null
        this.frontlist = true
        this.afterlist = false
        this.timesbj = false
        this.timesbj1 = true
        this.operation = true
        this.operation1 = false
      },
      //修正后按钮
      subitafter(){
        exp10_shuangpinDianLiCeng().then(response => {
          console.log(response)
          this.data = response.data
        })
        this.afternum = null
        this.aftername = null
        this.frontlist = false
        this.afterlist = true
        this.timesbj = false
        this.timesbj1 = true
        this.operation = true
        this.operation1 = false
      },
      //修正后伪距提交按钮
      submitafter(){
        var v = this.data.input
        if (this.afternum  === this.data.input) {
          this.$confirm('转换正确!', '提示', {
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
            this.afternum = this.data.input
          })
        }
      },
      //修正后TGD提交按钮
      submitafter1(){
         var v = this.data.input2
        if (this.aftername  === this.data.input2) {
          this.$confirm('转换正确!', '提示', {
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
            this.aftername = this.data.input2
          })
        }
      }
  }

}
</script>

<style scoped>
  .properties{
    margin-left: 10px;
  }
  .clock{
    margin-bottom: 15px;
  }
</style>
