<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>BDS电离层延迟改正实验</h2></div>
    <el-divider/>
    <el-card>
      <span >卫星位置：{{data.input.X}},{{data.input.Y}},{{data.input.Z}}</span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span >用户位置:{{data.input2.X}},{{data.input2.Y}},{{data.input2.Z}}</span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span >频点参数 B1:{{data.output.result1}}</span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input  :style="{width: '100%'}" placeholder="计算时刻如UTC2015-10-01"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input  :style="{width: '100%'}" placeholder="计算高度角"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input  :style="{width: '100%'}" placeholder="计算方位角"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" placeholder="地心张角"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="12">
                <el-form-item  prop="time">
                  <el-input :style="{width: '100%'}" placeholder="计算刺穿点"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" placeholder="地理经纬度"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item  prop="time">
                  <el-input :style="{width: '100%'}" placeholder="计算刺穿点的日固地磁经纬度"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item  prop="time">
                  <el-input :style="{width: '100%'}" placeholder="Ai(i=1~9)"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item  prop="time">
                  <el-input :style="{width: '100%'}" placeholder="AO"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item  prop="time">
                  <el-input :style="{width: '100%'}" placeholder="计算VTEC"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" placeholder="计算投影函数"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" placeholder="根据投影函数计算斜路径上的电离层延迟改正值"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" placeholder="乘以频率转换因子"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24" :style="{ textAlign: 'center' }">
                <el-button type="primary">
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
import {exp9_gps_dianLiCeng} from "@/api/vm/vmTest";

export default {
  name: "BDS",
  data(){
    return{
      isType:true,
      data: {}
    }
  },
  created() {
    exp9_gps_dianLiCeng().then(response => {
      this.data = response.data
      console.log(response)
    })
  },
}
</script>

<style scoped>

</style>
