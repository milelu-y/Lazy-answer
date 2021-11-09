<template>
  <div class="app-container">
    <h3 style="font-weight: 50">根据历书计算卫星位置实验</h3>
    <el-card>
      <div>
        <!--          <p>ps:{{ isTime ? '请将格里高利时转换为儒略日后进行输入' : '请将儒略日转换为格里高利时后进行输入' }}</p>-->
        <el-form ref="form" :model="form" size="medium" label-width="100px">
          <el-row :gutter="24">
            <el-col :span="12">
              <el-form-item label="接收机接收参数" prop="time" label-width="500px" style="width: 1000px">
                <el-input disabled v-model="data.input"
                          placeholder="563624175"
                          clearable/>
              </el-form-item>

              <el-form-item v-if="isTime1" label="t时刻与历书参考时刻的时间差" prop="tTime" label-width="500px"
                            style="width: 1000px">
                <el-input v-model="form.tTime" @input="isTime1Input" :style="{width: '100%'}"
                          :disabled="isTime2"
                          placeholder="请输入t时刻与历书参考时刻的时间差"
                          clearable/>
              </el-form-item>

              <el-form-item v-if="isTime2" label="卫星轨道长半轴" prop="wTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.wTime" @input="isTime2Input" :style="{width: '100%'}"
                          :disabled="isTime3"
                          placeholder="请输入卫星轨道长半轴"
                          clearable/>
              </el-form-item>


              <el-form-item v-if="isTime3" label="卫星平均运动角速度" prop="wxTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.wxTime" @input="isTime3Input" :style="{width: '100%'}"
                          :disabled="isTime4"
                          placeholder="请输入卫星平均运动角速度"
                          clearable/>
              </el-form-item>

              <el-form-item v-if="isTime4" label="计算平近点角" prop="pjdTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.pjdTime" @input="isTime4Input" :style="{width: '100%'}"
                          :disabled="isTime5"
                          placeholder="请输入平近点角"
                          clearable/>
              </el-form-item>

              <el-form-item v-if="isTime5" label="计算偏近点角" prop="pjjTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.pjjTime" @input="isTime5Input" :style="{width: '100%'}"
                          :disabled="isTime6"
                          placeholder="请输入偏近点角"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime6" label="计算真近点角" prop="zjjTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.zjjTime" @input="isTime6Input" :style="{width: '100%'}"
                          :disabled="isTime7"
                          placeholder="请输入真近点角"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime7" label="计算纬度幅角" prop="wdfjTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.wdfjTime" @input="isTime7Input" :style="{width: '100%'}"
                          :disabled="isTime8"
                          placeholder="请输入纬度幅角"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime8" label="计算径向距离" prop="jjjlTime" label-width="500px" style="width: 1000px">
                <el-input v-model="form.jjjlTime" @input="isTime8Input" :style="{width: '100%'}"
                          :disabled="isTime9"
                          placeholder="请输入径向距离"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime9" label="计算卫星在轨道平面内的坐标" prop="pmzvTime" label-width="500px"
                            style="width: 1000px">
                <el-input v-model="form.pmzvTime" @input="isTime9Input" :style="{width: '100%'}"
                          :disabled="isTime10"
                          placeholder="请输入轨道平面内的坐标"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime10" label="计算改正后的升交点经度" prop="sjdTime" label-width="500px"
                            style="width: 1000px">
                <el-input v-model="form.sjdTime" @input="isTime10Input" :style="{width: '100%'}"
                          :disabled="isTime11"
                          placeholder="请输入计算改正后的升交点经度"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime11" label="计算参考时刻的轨道倾角" prop="gdqjTime" label-width="500px"
                            style="width: 1000px">
                <el-input v-model="form.gdqjTime" @input="isTime11Input" :style="{width: '100%'}"
                          :disabled="isTime12"
                          placeholder="请输入参考时刻的轨道倾角"
                          clearable/>
              </el-form-item>
              <el-form-item v-if="isTime12" label="计算卫星在CGS2000坐标系中的坐标" prop="CGSTime" label-width="500px"
                            style="width: 1000px">
                <el-input v-model="form.CGSTime" @input="isTime12Input" :style="{width: '100%'}"
                          placeholder="请输入卫星在CGS2000坐标系中的坐标"
                          clearable/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import {exp5_BDCS_LS} from "@/api/vm/vmTest";

export default {
  name: "LiShuToSatellitePosition",
  data() {
    return {
      data: {},
      form: {},
      answer: 0,
      active: 0,
      isTime1: true,
      isTime2: false,
      isTime3: false,
      isTime4: false,
      isTime5: false,
      isTime6: false,
      isTime7: false,
      isTime8: false,
      isTime9: false,
      isTime10: false,
      isTime11: false,
      isTime12: false
    }
  },
  created() {
    exp5_BDCS_LS().then(response => {
      console.log(response)
      this.data = response.data
    })
  },
  methods: {
    isTime1Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result1) {
        this.isTime2 = true
      } else {
        this.isTime2 = false
      }
    },
    isTime2Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result2) {
        this.isTime3 = true
      } else {
        this.isTime3 = false
      }
    },
    isTime3Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result3) {
        this.isTime4 = true
      } else {
        this.isTime4 = false
      }
    },
    isTime4Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result4) {
        this.isTime5 = true
      } else {
        this.isTime5 = false
      }
    },
    isTime5Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result5) {
        this.isTime6 = true
      } else {
        this.isTime6 = false
      }
    },
    isTime6Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result6) {
        this.isTime7 = true
      } else {
        this.isTime7 = false
      }
    },
    isTime7Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result7) {
        this.isTime8 = true
      } else {
        this.isTime8 = false
      }
    },
    isTime8Input(val) {
      var d = this.data.output.result8 + "," + this.data.output.result9
      let v = val
      if (v === d) {
        this.isTime9 = true
      } else {
        this.isTime9 = false
      }
    },
    isTime9Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result10) {
        this.isTime10 = true
      } else {
        this.isTime10 = false
      }
    },
    isTime10Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result11) {
        this.isTime11 = true
      } else {
        this.isTime11 = false
      }
    },
    isTime11Input(val) {
      let v = val
      if ((v * 1) === this.data.output.result12) {
        this.isTime12 = true
      } else {
        this.isTime12 = false
      }
    },
    isTime12Input(val) {
      var d = this.data.output.result13 + "," + this.data.output.result14 + "," + this.data.output.result15
      let v = val
      if (v === d) {
        this.isTime12 = true
        this.notifySuccess("正确", "转换正确")


      }
    }
  }
}
</script>

<style scoped>

</style>
