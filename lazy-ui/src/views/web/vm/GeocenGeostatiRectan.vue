<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>地心地固直角坐标系与大地坐标系的转换实验</h2></div>
    <el-divider/>
    <div>
      <el-card>
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
        </div>
        <img v-if="isType" src="../../../assets/images/firnula/dxdgddjwd.png" />
        <img v-if="!isType" src="../../../assets/images/firnula/ddjwdtodg.png" />
      </el-card>
    </div>
    <el-card>
      <span v-if="isType">地心地固直角坐标系:{{ dxdgzj.X }},{{ dxdgzj.Y }},{{ dxdgzj.Z }}</span>
      <span v-else>大地坐标系:{{ BLHToXYZ.input.B }},{{ BLHToXYZ.input.L }},{{ BLHToXYZ.input.H }}</span>
    </el-card>
    <el-card v-if="isType" class="card" style="margin-top: 10px">
      <p>ps:请将地心地固直角坐标系转换成大地坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="大地坐标系" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="大地坐标系" v-model="ddzbx"></el-input>
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
    <el-card v-else class="card" style="margin-top: 10px">
      <p>ps:大地坐标系转换为地心地固坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="地心地固坐标" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="地心地固坐标系" v-model="dxdgzbx"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary" @click="handle1()">
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
import {Experiment, BLHToXYZ} from "@/api/vm/vmTest";

export default {
  name: "GeocenGeostatiRectan",
  data() {
    return {
      isType: true,
      dxdgzj: {},
      output: {},
      ddzbx: '',
      BLHToXYZ: {},
      dxdgzbx:''
    }
  },
  created() {
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
        this.notifySuccess("正确", "转换正确")
        this.isType = false
        BLHToXYZ().then(response => {
          console.log(response)
          this.BLHToXYZ = response.data
        })
      } else {
        this.notifyError("转换错误，请重试")
      }
    },
    handle1() {
      var v = this.BLHToXYZ.output.X + "," + this.BLHToXYZ.output.Y + "," + this.BLHToXYZ.output.Z
      if (this.dxdgzbx === v) {
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
