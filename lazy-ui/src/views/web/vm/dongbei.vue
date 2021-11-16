<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>地心地固直角坐标系与东北天坐标系的转换实验</h2></div>
    <el-divider/>
    <div>
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
        </div>
        <img v-if="isType" src="../../../assets/images/firnula/dxdgddjwd.png" />
        <img v-if="!isType" src="../../../assets/images/firnula/dongbei.png" />
      </el-card>
    </div>
    <el-card>
      <span v-if="isType">地心地固直角坐标系:{{ XYZ2ENUDATA.input.X }},{{ XYZ2ENUDATA.input.Y }},{{ XYZ2ENUDATA.input.Z }}</span>
      <span v-else>大地坐标系:{{ XYZ2ENUDATA.input2.B }},{{ XYZ2ENUDATA.input2.H }},{{ XYZ2ENUDATA.input2.L }}</span>
    </el-card>
    <el-card  v-if="isType" class="card" style="margin-top: 10px">
      <p>ps:请将地心地固直角坐标系转换成大地坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="大地坐标系" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="大地坐标系" v-model="ddzb"></el-input>
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
    <el-card  v-else class="card" style="margin-top: 10px">
      <p>ps:大地坐标系转换为东北天坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="东北天坐标" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="东北天坐标系" v-model="dbzb"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary" @click="handle1">
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
import {XYZ2ENU} from "@/api/vm/vmTest";

export default {
  name: "GeocenGeostatiRectan",
  data(){
    return{
      isType:true,
      XYZ2ENUDATA:{},
      ddzb:'',
      dbzb:''
    }
  },
  created() {
    XYZ2ENU().then(response=>{
      console.log(response)
      this.XYZ2ENUDATA=response.data
    })
  },
  methods:{
    handle(){
      var v = this.XYZ2ENUDATA.input2.B + "," + this.XYZ2ENUDATA.input2.H + "," + this.XYZ2ENUDATA.input2.L
      if (this.ddzb === v) {
        this.notifySuccess("正确", "转换正确")
        this.isType=false
      } else {
        this.notifyError("转换错误，请重试")
      }
    },
    handle1(){
      var v = this.XYZ2ENUDATA.output.e
      if ((this.dbzb)*1 === v) {
        this.notifySuccess("正确", "转换正确")
      } else {
        this.notifyError("转换错误，请重试")
      }
    }
  }
}
</script>

<style scoped>

</style>
