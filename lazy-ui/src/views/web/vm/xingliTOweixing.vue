<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <h3 style="font-weight: 50">根据星历计算卫星位置实验</h3>
    <el-divider/>
    <div>
      <el-card style="position: absolute;left: 5px; top: 0; width: 40%;height:100%;overflow-y: auto;">
        <div slot="header" class="clearfix">
          <span>实验步骤</span>
          <span style="float: right;color: #498c5f" v-if="exData.resource==null">暂未上传实验大纲</span>
          <span v-else style="float: right;color: #498c5f"> <a
            :href="exData.resource.url" target="_blank">点击此处下载实验大纲</a></span>
        </div>
        <div style="" v-html="exData.process">

        </div>
      </el-card>
    </div>
    <el-card style="margin:10px 0">
      <div class="siz">
        接收机接收参数：
        <el-input  
          v-model="data.input"
          style="width: 260px"
          clearable/>
        <el-button @click="refresh" style="margin-left:10px;">刷新</el-button>
      </div>
    </el-card>
    <el-card>
      <div>
        <!--          <p>ps:{{ isTime ? '请将格里高利时转换为儒略日后进行输入' : '请将儒略日转换为格里高利时后进行输入' }}</p>-->
        <el-form ref="form" :model="form" size="medium" label-width="100px">
          <el-row :gutter="24">
            <el-col :span="12">
              <!-- <el-form-item label="接收机接收参数" prop="time" label-width="310px" style="width: 100%;" >
                <el-input  
                  v-model="data.input"
                  placeholder="563624175"
                  clearable/>
                <el-button @click="refresh">刷新</el-button>
              </el-form-item> -->

              <el-form-item  label="t时刻与参考时刻的时间差" prop="type" label-width="300px" style="width: 1000px" >
                <el-col :span="22">
                  <el-input v-model="form.tTime" :style="{width: '100%'}"
                            :disabled="isTime1"
                            placeholder="请输入t时刻与参考时刻的时间差"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <el-button class="attribute" v-if="btn1" @click="handle">下一步</el-button>
                  <!-- <i v-if="lisTime1" style="color: #00ff80" class="el-icon-success"></i> -->
                </el-col>
              </el-form-item>


              <el-form-item  label="参考时刻的长半轴" prop="type" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.ckTime" :style="{width: '100%'}"
                            :disabled="isTime2"
                            placeholder="请输入参考时刻的长半轴"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <el-button class="attribute" v-if="btn2" @click="handle">下一步</el-button>
                  <!-- <i v-if="lisTime2" style="color: #00ff80" class="el-icon-success"></i> -->
                </el-col>
              </el-form-item>


              <el-form-item  label="t时刻的长半轴" prop="type" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.wTime" :style="{width: '100%'}"
                            :disabled="isTime3"
                            placeholder="请输入t时刻的长半轴"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <el-button class="attribute" v-if="btn3" @click="handle">下一步</el-button>
                  <!-- <i v-if="lisTime3" style="color: #00ff80" class="el-icon-success"></i> -->
                </el-col>
              </el-form-item>


              <el-form-item  label="参考时刻平均运动角速率" prop="type" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.wxTime" :style="{width: '100%'}"
                            :disabled="isTime4"
                            placeholder="请输入参考时刻的平均运动角速度"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <el-button class="attribute" v-if="btn4" @click="handle">下一步</el-button>
                  <!-- <i v-if="lisTime4" style="color: #00ff80" class="el-icon-success"></i> -->
                </el-col>
              </el-form-item>


              <el-form-item  label="平均运动角速率的偏差" prop="pjtime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.pjtime" :style="{width: '100%'}"
                            :disabled="isTime5"
                            placeholder="请输入平均运动角速率的偏差"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if ="lisTime5" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn5" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>


              <el-form-item  label="改正后的平均运动角速率" prop="gztime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.gztime" :style="{width: '100%'}"
                            :disabled="isTime6"
                            placeholder="请输入改正后的平均运动角速率"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime6" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn6" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>


              <el-form-item  label="计算平近点角" prop="jspTime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.jspTime" :style="{width: '100%'}"
                            :disabled="isTime7"
                            placeholder="请输入平近点角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime7" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn7" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>

              <el-form-item  label="计算偏近点角" prop="pjjTime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.pjjTime" :style="{width: '100%'}"
                            :disabled="isTime8"
                            placeholder="请输入偏近点角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime8" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn8" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
              <el-form-item  label="计算真近点角" prop="zjjTime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.zjjTime" :style="{width: '100%'}"
                            :disabled="isTime9"
                            placeholder="请输入真近点角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime9" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn9" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
              <el-form-item  label="计算纬度幅角" prop="wdfjTime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.wdfjTime" :style="{width: '100%'}"
                            :disabled="isTime10"
                            placeholder="请输入纬度幅角"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime10" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn10" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
              <el-form-item  label="计算径向距离" prop="jjjlTime" label-width="300px" style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.jjjlTime" :style="{width: '100%'}"
                            :disabled="isTime11"
                            placeholder="请输入径向距离"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime11" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn11" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
              <el-form-item  label="计算卫星在轨道平面内的坐标" prop="pmzvTime" label-width="300px"
                            style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.pmzvTime" :style="{width: '100%'}"
                            :disabled="isTime12"
                            placeholder="请输入轨道平面内的坐标"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime12" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn12" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
              <el-form-item  label="计算改正后的升交点经度" prop="sjdTime" label-width="300px"
                            style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.sjdTime" :style="{width: '100%'}"
                            :disabled="isTime13"
                            placeholder="请输入计算改正后的升交点经度"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime13" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn13" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
              <!--              <el-form-item v-if="isTime11" label="计算参考时刻的轨道倾角" prop="gdqjTime" label-width="500px"  style="width: 1000px">
                              <el-input v-model="form.gdqjTime"  @input="isTime11Input" :style="{width: '100%'}"
                                        :disabled="isTime12"
                                        placeholder="请输入参考时刻的轨道倾角"
                                        clearable/>
                            </el-form-item>-->
              <el-form-item label="计算卫星在CGS2000坐标系中的坐标" prop="CGSTime" label-width="300px"
                            style="width: 1000px">
                <el-col :span="22">
                  <el-input v-model="form.CGSTime" :style="{width: '100%'}"
                            :disabled="isTime14"
                            placeholder="请输入卫星在CGS2000坐标系中的坐标"
                            clearable/>
                </el-col>
                <el-col :span="2">
                  <!-- <i v-if="lisTime14" style="color: #00ff80" class="el-icon-success"></i> -->
                  <el-button class="attribute" v-if="btn14" @click="handle">下一步</el-button>
                </el-col>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" :style="{ textAlign: 'center' }">
              <el-button type="primary" @click="handle1" v-if="btn15">
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
      form: {tTime:'',ckTime:'',wTime:'',wxTime:'',},
      answer: 0,
      active: 0,
      exData: {},
      data: {},
      isTime1: false,
      isTime2: true,
      isTime3: true,
      isTime4: true,
      isTime5: true,
      isTime6: true,
      isTime7: true,
      isTime8: true,
      isTime9: true,
      isTime10: true,
      isTime11: true,
      isTime12: true,
      isTime13: true,
      isTime14: true,
      btn1:true,
      btn2:false,
      btn3:false,
      btn4:false,
      btn5:false,
      btn6:false,
      btn7:false,
      btn8:false,
      btn9:false,
      btn10:false,
      btn11:false,
      btn12:false,
      btn13:false,
      btn14:false,
      btn15:false
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      console.log(response)
      this.exData = response.data
    })
    exp6_BDCS_XL().then(response => {
      console.log(response)
      this.data = response.data
    })
  },
  methods: {
    //刷新按钮
    
    refresh(){
       exp6_BDCS_XL().then(response => {
      console.log(response)
      this.data = response.data
    }) 
    Object.assign(this.$data.form, this.$options.data().form) 
      this.isTime1 = false
      this.isTime2 = true
      this.isTime3 = true
      this.isTime4 = true
      this.isTime5 = true
      this.isTime6 = true
      this.isTime7 = true
      this.isTime8 = true
      this.isTime9 = true
      this.isTime10 = true
      this.isTime11 = true
      this.isTime12 = true
      this.isTime13 = true
      this.isTime14 = true
      this.btn1 = true
      this.btn2 = false
      this.btn3 = false
      this.btn4 = false
      this.btn5 = false
      this.btn6 = false
      this.btn7 = false
      this.btn8 = false
      this.btn9 = false
      this.btn10 = false
      this.btn11 = false
      this.btn12 = false
      this.btn13 = false
      this.btn14 = false
      this.btn15 = false
    },
    handle1(){
       this.$confirm('转换正确，点击刷新重新答题!', '提示', {
            confirmButtonText: '确定',
            type: 'success'
          }).then(() => {
            
          })
    },
    handle(){
      if (!this.isTime1) {
        var v = this.data.output.result1
        if ((this.form.tTime * 1) === this.data.output.result1) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime1 = true
          this.isTime2 = false
          this.btn1 = false
          this.btn2 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.tTime = this.data.output.result1
          })
        }
      }else if(!this.isTime2){
        var v = this.data.output.result2
        if ((this.form.ckTime * 1) === this.data.output.result2) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime2 = true
          this.isTime3 = false
          this.btn2 = false
          this.btn3 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.ckTime = this.data.output.result2
          })
        }
      }else if(!this.isTime3){
        var v = this.data.output.result3
        if ((this.form.wTime * 1) === this.data.output.result3) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime3 = true
          this.isTime4 = false
          this.btn3 = false
          this.btn4 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.wTime = this.data.output.result3
          })
        }
      }else if(!this.isTime4) {
         var v = this.data.output.result4
        if ((this.form.wxTime * 1) === this.data.output.result4) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime4 = true
          this.isTime5 = false
         this.btn4 = false
          this.btn5 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.wxTime = this.data.output.result4
          })
        }
      }else if(!this.isTime5) {
        var v = this.data.output.result5
        if ((this.form.pjtime * 1) === this.data.output.result5) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime5 = true
          this.isTime6 = false
          this.btn5 = false
          this.btn6 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.pjtime = this.data.output.result5
          })
        }
      }else if(!this.isTime6) {
        var v = this.data.output.result6
        if ((this.form.gztime * 1) === this.data.output.result6) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime6 = true
          this.isTime7 = false
          this.btn6 = false
          this.btn7 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.gztime = this.data.output.result6
          })
        }
      }else if(!this.isTime7) {
        var v = this.data.output.result7
        if ((this.form.jspTime * 1) === this.data.output.result7) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime7 = true
          this.isTime8 = false
          this.btn7 = false
          this.btn8 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.jspTime = this.data.output.result7
          })
        }
      }else if(!this.isTime8) {
        var v = this.data.output.result8
        if ((this.form.pjjTime * 1) === this.data.output.result8) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime8 = true
          this.isTime9 = false
          this.btn8 = false
          this.btn9 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.pjjTime = this.data.output.result8
          })
        }
      }else if (!this.isTime9) {
        var v = this.data.output.result9
        if ((this.form.zjjTime * 1) === this.data.output.result9) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime9 = true
          this.isTime10 = false
          this.btn9 = false
          this.btn10 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.zjjTime = this.data.output.result9
          })
        }
      }else if(!this.isTime10) {
        var v = this.data.output.result10
        if ((this.form.wdfjTime * 1) === this.data.output.result10) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime10 = true
          this.isTime11 = false
          this.btn10 = false
          this.btn11 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.wdfjTime = this.data.output.result10
          })
        }
      }else if(!this.isTime11) {
        var v = this.data.output.result11
        if ((this.form.jjjlTime * 1) === this.data.output.result11) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime11 = true
          this.isTime12 = false
          this.btn11 = false
          this.btn12 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.jjjlTime = this.data.output.result11
          })
        }
      }else if(!this.isTime12) {
         var v = this.data.output.result12
        if ((this.form.pmzvTime * 1) === this.data.output.result12) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime12 = true
          this.isTime13 = false
          this.btn12 = false
          this.btn13 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.pmzvTime = this.data.output.result12
          })
        }
      }else if(!this.isTime13){
        var v = this.data.output.result13
        if ((this.form.sjdTime * 1) === this.data.output.result13) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime13 = true
          this.isTime14 = false
          this.btn13 = false
          this.btn14 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.sjdTime = this.data.output.result13
          })
        }
      }else if(!this.isTime14){
        var v = this.data.output.result14
        if ((this.form.CGSTime * 1) === this.data.output.result14) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
            
          })
          this.isTime14 = true
          this.lisTime14 = true
          this.btn14 = false
          this.btn15 = true
        } else {
          this.$confirm('转换错误，正确答案为：'+ v, '提示',{
            confirmButtonText: '确定',
            type:'warning'
          }).then(()=>{
            this.form.CGSTime = this.data.output.result14
          })
        }
      }
    },
    // isTime1Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result1) {
    //     this.isTime13 = true
    //   } else {
    //     this.isTime13 = false
    //   }
    // },
    // isTime13Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result2) {
    //     this.isTime2 = true
    //   } else {
    //     this.isTime2 = false
    //   }
    // },
    // isTime2Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result3) {
    //     this.isTime3 = true
    //   } else {
    //     this.isTime3 = false
    //   }
    // },
    // isTime3Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result4) {
    //     this.isTime14 = true
    //   } else {
    //     this.isTime14 = false
    //   }
    // },
    // isTime14Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result5) {
    //     this.isTime15 = true
    //   } else {
    //     this.isTime15 = false
    //   }
    // },

    // isTime15Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result6) {
    //     this.isTime4 = true
    //   } else {
    //     this.isTime4 = false
    //   }
    // },


    // isTime4Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result7) {
    //     this.isTime5 = true
    //   } else {
    //     this.isTime5 = false
    //   }
    // },
    // isTime5Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result8) {
    //     this.isTime6 = true
    //   } else {
    //     this.isTime6 = false
    //   }
    // },
    // isTime6Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result9) {
    //     this.isTime7 = true
    //   } else {
    //     this.isTime7 = false
    //   }
    // },
    // isTime7Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result10) {
    //     this.isTime8 = true
    //   } else {
    //     this.isTime8 = false
    //   }
    // },
    // isTime8Input(val) {
    //   // var d = this.data.output.result11 + "," + this.data.output.result12 + "," + this.data.output.result13
    //   let v = val;
    //   if ((v * 1) === this.data.output.result15) {
    //     this.isTime9 = true
    //   } else {
    //     this.isTime9 = false
    //   }
    // },
    // isTime9Input(val) {
    //   var d = this.data.output.result16 + "," + this.data.output.result17
    //   let v = val;
    //   if (v === d) {
    //     this.isTime10 = true
    //   } else {
    //     this.isTime10 = false
    //   }
    // },
    // isTime10Input(val) {
    //   let v = val;
    //   if ((v * 1) === this.data.output.result18) {
    //     this.isTime12 = true
    //   } else {
    //     this.isTime12 = false
    //   }
    // },
    // isTime11Input(val) {
    //   var d = this.data.output.result19 + "," + this.data.output.result20 + "," + this.data.output.result22
    //   let v = val;
    //   if (v === d) {
    //     this.isTime12 = true
    //   } else {
    //     this.isTime12 = false
    //   }
    // },
  }
}
</script>

<style scoped>
  
</style>
