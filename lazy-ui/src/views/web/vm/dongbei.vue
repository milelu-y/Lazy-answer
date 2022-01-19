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
      <span v-if="isType">地心地固直角坐标系:{{ XYZ2ENUDATA.input.X }},{{ XYZ2ENUDATA.input.Y }},{{ XYZ2ENUDATA.input.Z }}</span>
      <span v-else>大地坐标系:{{ XYZ2ENUDATA.input2.B }},{{ XYZ2ENUDATA.input2.H }},{{ XYZ2ENUDATA.input2.L }}</span>
    </el-card>
    <el-card v-if="isType" class="card" style="margin-top: 10px">
      <p>ps:请将地心地固直角坐标系转换成大地坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="大地坐标系" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="大地坐标系" ref="ddzb" v-model="ddzb"></el-input>
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
      <p>ps:大地坐标系转换为东北天坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="东北天坐标" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="东北天坐标系" ref="dbzb" v-model="dbzb"></el-input>
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
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "GeocenGeostatiRectan",
  data() {
    return {
      exData: {},
      isType: true,
      XYZ2ENUDATA: {},
      ddzb: '',
      dbzb: ''
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.exData = response.data
    })
    XYZ2ENU().then(response => {
      console.log(response)
      this.XYZ2ENUDATA = response.data
    })
  },
  methods: {
    handle() {
      var v = this.XYZ2ENUDATA.input2.B + "," + this.XYZ2ENUDATA.input2.L + "," + this.XYZ2ENUDATA.input2.H
      if (this.ddzb === v) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '下一题',
          type: 'success'
        }).then(() => {

        })
        this.isType = false
      } else {
        this.notifyError("转换错误，请重试")
      }
    },
    handle1() {
      var v = this.XYZ2ENUDATA.output.e
      if ((this.dbzb) * 1 === v) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '完成',
          type: 'success'
        }).then(() => {

        })
      } else {
        this.notifyError("转换错误，请重试")
      }
    }
  }
}
</script>

<style scoped>

</style>
