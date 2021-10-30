<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>地心地固直角坐标系与大地坐标系的转换实验</h2></div>
    <el-divider/>
    <el-card>
      <span v-if="isType">地心地固直角坐标系:{{ 123 }}</span>
      <span v-else>大地坐标系:{{ 123 }}</span>
    </el-card>
    <el-card  v-if="isType" class="card" style="margin-top: 10px">
      <p>ps:请将地心地固直角坐标系转换成大地坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="大地坐标系" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="大地坐标系"></el-input>
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
    <el-card  v-else class="card" style="margin-top: 10px">
      <p>ps:大地坐标系转换为地心地固坐标系</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="地心地固坐标" prop="time">
                  <el-input :style="{width: '100%'}" placeholder="地心地固坐标系"></el-input>
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
  name: "GeocenGeostatiRectan",
  data(){
    return{
      isType:true
    }
  }
}
</script>

<style scoped>

</style>
