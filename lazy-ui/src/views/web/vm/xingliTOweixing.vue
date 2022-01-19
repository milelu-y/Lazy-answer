<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <h3 style="font-weight: 50">根据星历计算卫星位置实验</h3>
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
      <div>
        <!--          <p>ps:{{ isTime ? '请将格里高利时转换为儒略日后进行输入' : '请将儒略日转换为格里高利时后进行输入' }}</p>-->
        <el-form ref="form" :model="form" size="medium" label-width="80px">
          <el-row :gutter="24">
            <el-col :span="12">
              <el-form-item label="接收机接收参数" prop="time" label-width="200px" style="width: 100%">
                <el-input disabled v-model="data.input"
                          placeholder="563624175"
                          clearable/>


              </el-form-item>

              <el-form-item v-if="isTime1" label="t时刻与参考时刻的时间差" prop="tTime" label-width="200px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.tTime" :style="{width: '100%'}"
                            :disabled="isTime13"
                            placeholder="请输入t时刻与参考时刻的时间差"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime13" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>


              <el-form-item v-if="isTime13" label="参考时刻的长半轴" prop="tTime13" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.tTime13" :style="{width: '100%'}"
                            :disabled="isTime2"
                            placeholder="请输入参考时刻的长半轴"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime2" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>


              <el-form-item v-if="isTime2" label="t时刻的长半轴" prop="wTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.wTime" :style="{width: '100%'}"
                            :disabled="isTime3"
                            placeholder="请输入t时刻的长半轴"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime3" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>


              <el-form-item v-if="isTime3" label="参考时刻平均运动角速率" prop="wxTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.wxTime" :style="{width: '100%'}"
                            :disabled="isTime14"
                            placeholder="请输入参考时刻的平均运动角速度"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime14" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>


              <el-form-item v-if="isTime14" label="平均运动角速率的偏差" prop="time14" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.time14" :style="{width: '100%'}"
                            :disabled="isTime15"
                            placeholder="请输入平均运动角速率的偏差"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime15" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>


              <el-form-item v-if="isTime15" label="改正后的平均运动角速率" prop="time15" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.time15" :style="{width: '100%'}"
                            :disabled="isTime4"
                            placeholder="请输入改正后的平均运动角速率"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime4" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>


              <el-form-item v-if="isTime4" label="计算平近点角" prop="pjdTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.pjdTime" :style="{width: '100%'}"
                            :disabled="isTime5"
                            placeholder="请输入平近点角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime5" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>

              <el-form-item v-if="isTime5" label="计算偏近点角" prop="pjjTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.pjjTime" :style="{width: '100%'}"
                            :disabled="isTime6"
                            placeholder="请输入偏近点角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime6" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>
              <el-form-item v-if="isTime6" label="计算真近点角" prop="zjjTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.zjjTime" :style="{width: '100%'}"
                            :disabled="isTime7"
                            placeholder="请输入真近点角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime7" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>
              <el-form-item v-if="isTime7" label="计算纬度幅角" prop="wdfjTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.wdfjTime" :style="{width: '100%'}"
                            :disabled="isTime8"
                            placeholder="请输入纬度幅角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime8" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>
              <el-form-item v-if="isTime8" label="计算径向距离" prop="jjjlTime" label-width="500px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.jjjlTime" :style="{width: '100%'}"
                            :disabled="isTime9"
                            placeholder="请输入径向距离"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime9" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>
              <el-form-item v-if="isTime9" label="计算卫星在轨道平面内的坐标" prop="pmzvTime" label-width="500px"
                            style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.pmzvTime" :style="{width: '100%'}"
                            :disabled="isTime10"
                            placeholder="请输入轨道平面内的坐标"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime10" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>
              <el-form-item v-if="isTime10" label="计算改正后的升交点经度" prop="sjdTime" label-width="500px"
                            style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.sjdTime" :style="{width: '100%'}"
                            :disabled="isTime12"
                            placeholder="请输入计算改正后的升交点经度"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <i v-if="isTime12" style="color: #00ff80" class="el-icon-success"></i>
                </el-col>
              </el-form-item>
              <!--              <el-form-item v-if="isTime11" label="计算参考时刻的轨道倾角" prop="gdqjTime" label-width="500px"  style="width: 1000px">
                              <el-input v-model="form.gdqjTime"  @input="isTime11Input" :style="{width: '100%'}"
                                        :disabled="isTime12"
                                        placeholder="请输入参考时刻的轨道倾角"
                                        clearable/>
                            </el-form-item>-->
              <el-form-item v-if="isTime12" label="计算卫星在CGS2000坐标系中的坐标" prop="CGSTime" label-width="500px"
                            style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.CGSTime" :style="{width: '100%'}"
                            placeholder="请输入卫星在CGS2000坐标系中的坐标"
                            clearable/>
                </el-col>
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
    </el-card>
  </div>
</template>

<script>
import {exp6_BDCS_XL} from "@/api/vm/vmTest";
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "xingliTOweixing",
  data() {
    return {
      form: {},
      answer: 0,
      active: 0,
      exData: {},
      data: {},
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
      isTime12: false,
      isTime13: false,
      isTime14: false,
      isTime15: false
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.exData = response.data
    })
    exp6_BDCS_XL().then(response => {
      console.log(response)
      this.data = response.data
    })
  },
  methods: {
    handle(){

    },
    isTime1Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result1) {
        this.isTime13 = true
      } else {
        this.isTime13 = false
      }
    },
    isTime13Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result2) {
        this.isTime2 = true
      } else {
        this.isTime2 = false
      }
    },
    isTime2Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result3) {
        this.isTime3 = true
      } else {
        this.isTime3 = false
      }
    },
    isTime3Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result4) {
        this.isTime14 = true
      } else {
        this.isTime14 = false
      }
    },
    isTime14Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result5) {
        this.isTime15 = true
      } else {
        this.isTime15 = false
      }
    },

    isTime15Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result6) {
        this.isTime4 = true
      } else {
        this.isTime4 = false
      }
    },


    isTime4Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result7) {
        this.isTime5 = true
      } else {
        this.isTime5 = false
      }
    },
    isTime5Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result8) {
        this.isTime6 = true
      } else {
        this.isTime6 = false
      }
    },
    isTime6Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result9) {
        this.isTime7 = true
      } else {
        this.isTime7 = false
      }
    },
    isTime7Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result10) {
        this.isTime8 = true
      } else {
        this.isTime8 = false
      }
    },
    isTime8Input(val) {
      // var d = this.data.output.result11 + "," + this.data.output.result12 + "," + this.data.output.result13
      let v = val;
      if ((v * 1) === this.data.output.result15) {
        this.isTime9 = true
      } else {
        this.isTime9 = false
      }
    },
    isTime9Input(val) {
      var d = this.data.output.result16 + "," + this.data.output.result17
      let v = val;
      if (v === d) {
        this.isTime10 = true
      } else {
        this.isTime10 = false
      }
    },
    isTime10Input(val) {
      let v = val;
      if ((v * 1) === this.data.output.result18) {
        this.isTime12 = true
      } else {
        this.isTime12 = false
      }
    },
    isTime11Input(val) {
      var d = this.data.output.result19 + "," + this.data.output.result20 + "," + this.data.output.result22
      let v = val;
      if (v === d) {
        this.isTime12 = true
      } else {
        this.isTime12 = false
      }
    },
  }
}
</script>

<style scoped>

</style>
