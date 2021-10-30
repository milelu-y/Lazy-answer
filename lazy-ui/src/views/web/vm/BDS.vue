<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>BDS电离层延迟改正实验</h2></div>
    <el-divider/>
    <el-card>
      <span >卫星位置：{{-13364075.20873,22879822.5064,790480.10088}}</span>
    </el-card>
    <el-card style="margin-top: 10px">
      <span >用户位置:{{-2148744.3969,4426641.2099,4044655.8564}}</span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
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
                  <el-input :style="{width: '100%'}" placeholder="A2"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item  prop="time">
                  <el-input :style="{width: '100%'}" placeholder="A4"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item prop="time">
                  <el-input :style="{width: '100%'}" placeholder="垂直电离层延迟改正值"></el-input>
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
export default {
  name: "GPS",
  data(){
    return{
      isType:true
    }
  }
}
</script>

<style scoped>

</style>
