<template>
  <!--  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">-->
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>地心地固直角坐标系与大地坐标系的转换</h2></div>
    <el-divider/>
    <div>
      <el-card style="position: absolute;left: 5px; top: 42px; width: 40%;height:700px;overflow:auto;">
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="data.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="data.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div v-html="data.process">

        </div>
      </el-card>
    </div>
    <el-card>
      <span v-if="isType">
        地心地固直角坐标系:
        <el-input v-model="dxdgzj.X" disabled style="width: 170px"/>
        <el-input v-model="dxdgzj.Y" disabled style="width: 170px"/>
        <el-input v-model="dxdgzj.Z" disabled style="width: 170px"/>
        <el-button @click="generateData">刷新</el-button>
      </span>
      <span v-else>
        大地坐标系:
        <el-input v-model="BLHToXYZ.input.B" style="width: 170px"/>
        <el-input v-model="BLHToXYZ.input.L" style="width: 170px"/>
        <el-input v-model="BLHToXYZ.input.H" style="width: 170px"/>
      </span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <!--      <p>ps:请将地心地固直角坐标系转换成大地坐标系</p>-->
      <div>
        <el-form label-width="100px">
          <el-col :span="24">
            <el-form-item label="大地坐标系" prop="time">
              <el-input style="width: 200px"  v-model="ddzbx.B"></el-input>
              <el-input style="width: 200px"  v-model="ddzbx.L"></el-input>
              <el-input style="width: 200px"  v-model="ddzbx.H"></el-input>
              <el-button @click="submitGC">提交</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="地心地固坐标" prop="time">
              <el-input style="width: 200px"  v-model="dxdgzbx.X"></el-input>
              <el-input style="width: 200px"  v-model="dxdgzbx.Y"></el-input>
              <el-input style="width: 200px"  v-model="dxdgzbx.Z"></el-input>
              <el-button @click="submitGeocentric">提交</el-button>
            </el-form-item>
          </el-col>
        </el-form>
      </div>
    </el-card>
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
      ddzbx: {},
      BLHToXYZ: {},
      dxdgzbx: {},
      data: {}
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
          callback: action => {

          }
        });

      }
    },
    submitGeocentric() {
      var system = this.BLHToXYZ.output.X + "," + this.BLHToXYZ.output.Y + "," + this.BLHToXYZ.output.Z
      console.log(system)
      var user =  this.dxdgzbx.X+ "," +this.dxdgzbx.Y+","+this.dxdgzbx.Z
      if (system===user){
        this.notifySuccess("正确", "转换正确")
      }else {
        this.$alert('转换错误，正确答案为：\n'+system, '提示', '标题名称', {
          confirmButtonText: '确定',
        });
      }
    }
  }
}
</script>

<style scoped>
@import "../../../../public/tinymce/skins/content/default/content.min.css";
</style>
