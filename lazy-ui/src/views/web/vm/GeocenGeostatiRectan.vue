<template>
  <!--  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">-->
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>地心地固直角坐标系与大地坐标系的转换</h2></div>
    <el-divider/>
    <div>
      <el-card style="position: absolute;left: 5px; top: 0; width: 40%;height:100%;overflow-y: auto;overflow-x: auto;">
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="data.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="data.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div style="padding-top: 25px" v-html="data.process">

        </div>
      </el-card>
    </div>
    <div class="clock" style="margin-bottom:15px;">
      <el-button @click="subitbd" size="small">地心坐标</el-button>
      <el-button @click="subitst" size="small">大地坐标</el-button>
    </div>
    <div class="allboady" v-show="markes">
      <el-card>
        <span>
          地心地固直角坐标系:
          <el-input v-model="dxdgzj.X"  style="width: 240px"/>
          <el-input v-model="dxdgzj.Y"  style="width: 240px"/>
          <el-input v-model="dxdgzj.Z"  style="width: 240px"/>
          <el-button @click="generateData">刷新</el-button>
        </span>
      </el-card>
      <el-card class="card" style="margin-top: 10px;font-size: 14px;font-weight: 600;">
             <p>请将地心地固直角坐标系转换成大地坐标系</p>
        <div>
          <el-form label-width="100px">
            <el-col :span="24">
              <el-form-item label="大地坐标系：" prop="time">
                <el-input style="width: 200px"  v-model="ddzbx.B"></el-input>
                <el-input style="width: 200px"  v-model="ddzbx.L"></el-input>
                <el-input style="width: 200px"  v-model="ddzbx.H"></el-input>
                <el-button @click="submitGC">转换</el-button>
              </el-form-item>
            </el-col>
          </el-form>
        </div>
      </el-card>
    </div>

    <div class="allboady" v-show="markenums">
      <el-card>
        <span>
          大地坐标系:
          <el-input v-model="output.B"  style="width: 240px"/>
          <el-input v-model="output.L"  style="width: 240px"/>
          <el-input v-model="output.H"  style="width: 240px"/>
          <el-button @click="generateData">刷新</el-button>
        </span>
      </el-card>
      <el-card class="card" style="margin-top: 10px;font-size: 14px;font-weight: 600;">
             <p>请将大地坐标系转换成地心地固直角坐标系</p>
        <div>
          <el-form label-width="100px">
            <el-col :span="24">
              <el-form-item label="地心地固直角坐标系：" prop="time" label-width="160px">
                <el-input style="width: 200px"  v-model="geocentric.X"></el-input>
                <el-input style="width: 200px"  v-model="geocentric.Y"></el-input>
                <el-input style="width: 200px"  v-model="geocentric.Z"></el-input>
                <el-button @click="geocentricSubit">提交</el-button>
              </el-form-item>
            </el-col>
          </el-form>
        </div>
      </el-card>
    </div>
    
  </div>
</template>

<script>
import {Experiment, BLHToXYZ} from "@/api/vm/vmTest";
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "GeocenGeostatiRectan",
  data() {
    return {
      isType: true,
      dxdgzj: {},
      output: {},
      ddzbx: {B:'',L:'',H:''},
      BLHToXYZ: {},
      dxdgzbx: {X:'',Y:'',Z:''},
      data: {},
      markes:true,
      markenums:false,
      geocentric:{X:'',Y:'',Z:''}
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.data = response.data
    })
    Experiment().then(response => {
      console.log(response)
      this.dxdgzj = response.data.input
      this.output = response.data.output
    })
  },
  methods: {
    handle() {
      var v = this.output.B + "," + this.output.H + "," + this.output.L
      if (this.ddzbx === v) {
        this.$confirm('转换正确!', '正确', {
          confirmButtonText: '下一题',
          type: 'info'
        }).then(() => {
          this.isType = false
        })
        // this.notifySuccess("正确", "转换正确")
        BLHToXYZ().then(response => {
          console.log(response)
          this.BLHToXYZ = response.data
        })
      } else {
        this.$confirm("错误,正确答案：" + v, '提示', {
          type: 'error'
        })
      }
    },
    handle1() {
      var v = this.BLHToXYZ.output.X + "," + this.BLHToXYZ.output.Y + "," + this.BLHToXYZ.output.Z
      if (this.dxdgzbx === v) {
        // this.notifySuccess("正确", "转换正确")
        this.$confirm('转换正确!', '正确', {
          type: 'info'
        })
      } else {
        this.$confirm("错误,正确答案：" + v, '提示', {
          type: 'error'
        })
      }
    }, generateData() {
      Experiment().then(response => {
        console.log(response)
        this.dxdgzj = response.data.input
        this.output = response.data.output
      })
      this.ddzbx = {B:'',L:'',H:''}
      this.geocentric = {X:'',Y:'',Z:''}
    },
    submitGC() {
      var system = this.output.B + "," + this.output.L + "," + this.output.H
      var user = this.ddzbx.B+","+this.ddzbx.L+","+this.ddzbx.H;
      console.log(system,user)
      if (system===user){
        this.notifySuccess("正确", "转换正确")
        BLHToXYZ().then(response => {
          console.log(response)
          this.BLHToXYZ = response.data
        })
        this.isType=false
      }else {
        this.$alert('转换错误，正确答案为：\n'+system, '提示', '标题名称', {
          confirmButtonText: '确定',
          type:'warbing',}).then(()=>{
            this.ddzbx.B = this.output.B
            this.ddzbx.L = this.output.L
            this.ddzbx.H = this.output.H
          })
      }
    },
    submitGeocentric() {
      var system = this.dxdgzj.X + "," + this.dxdgzj.Y + "," + this.dxdgzj.Z
      var user =  this.dxdgzbx.X + "," +this.dxdgzbx.Y + "," + this.dxdgzbx.Z
      console.log(system,user)
      if (system===user){
        this.notifySuccess("正确", "转换正确")
      }else {
        this.$alert('转换错误，正确答案为：\n'+system, '提示', '标题名称', {
          confirmButtonText: '确定',
          type:'warbing',}).then(()=>{
            this.dxdgzbx.X = this.dxdgzj.X
            this.dxdgzbx.Y = this.dxdgzj.Y
            this.dxdgzbx.Z = this.dxdgzj.Z
          })
      }
    },

    //切换地心坐标按钮
    subitbd(){
       Experiment().then(response => {
        console.log(response)
        this.dxdgzj = response.data.input
        this.output = response.data.output
      })
      this.ddzbx = {B:'',L:'',H:''}
      this.geocentric = {X:'',Y:'',Z:''}
      this.markes = true
      this.markenums = false
    },
    //切换大地坐标系按钮
    subitst(){
       Experiment().then(response => {
        console.log(response)
        this.dxdgzj = response.data.input
        this.output = response.data.output
      })
      this.ddzbx = {B:'',L:'',H:''}
      this.geocentric = {X:'',Y:'',Z:''}
      this.markes = false
      this.markenums = true
    },
    //地心地固提交按钮
    geocentricSubit(){
      var system = this.geocentric.X + "," + this.geocentric.Y + "," + this.geocentric.Z
      var user =  this.dxdgzj.X + "," +this.dxdgzj.Y + "," + this.dxdgzj.Z
      console.log(system,user)
      if (system===user){
        this.notifySuccess("正确", "转换正确")
      }else {
        this.$alert('转换错误，正确答案为：\n'+user, '提示', '标题名称', {
          confirmButtonText: '确定',
          type:'warbing',}).then(()=>{
            this.geocentric.X = this.dxdgzj.X
            this.geocentric.Y = this.dxdgzj.Y
            this.geocentric.Z = this.dxdgzj.Z
          })
      }
    },
  }
}
</script>

<style scoped>
@import "../../../../public/tinymce/skins/content/default/content.min.css";
</style>
