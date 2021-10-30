<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>导航电文计算卫星钟差实验</h2></div>
    <el-divider/>
    <el-card>
      <span >BDT:{{ 123 }}、GPST:{{2312}}</span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <p>ps:请输入BDT钟差与提示GPST钟差</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="BDT钟差" prop="time">
                  <el-input  :style="{width: '100%'}" placeholder="BDT钟差"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="GPST钟差" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="GPST钟差"></el-input>
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
  name: "Navigation",
  data(){
    return{
      isType:true
    }
  }
}
</script>

<style scoped>

</style>
