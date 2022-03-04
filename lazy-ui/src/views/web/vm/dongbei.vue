<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{ exData.title }}</h2></div>
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
    <div class="clock" style="margin-bottom:15px;">
      <el-button @click="subitGeocentric" size="small">地心坐标</el-button>
      <el-button @click="subitearth" size="small">大地坐标</el-button>
      <el-button @click="subitnortheast" size="small">东北坐标</el-button>
    </div>

    <div class="allbody" v-show="geocentricX">
      <el-card>
          <div class="user" style="margin-top: 20px;">
            地心地固直角坐标系：
            <el-input v-model="XYZ2ENUDATA.input.X " style="width: 220px"/>
            <el-input v-model="XYZ2ENUDATA.input.Y " style="width: 220px"/>
            <el-input v-model="XYZ2ENUDATA.input.Z " style="width: 220px"/>
            <el-button @click="refresh" class="properties">刷新</el-button>
          </div>
          <!-- <div class="frequency" style="margin-top: 20px;margin-left: 65px;">
            大地坐标系：
            <el-input v-model="XYZ2ENUDATA.input2.B " style="width: 220px"/>
            <el-input v-model="XYZ2ENUDATA.input2.H " style="width: 220px"/>
            <el-input v-model="XYZ2ENUDATA.input2.L " style="width: 220px"/>
            <el-button @click="refresh" class="properties">刷新</el-button>
          </div> -->
        <!-- <span v-if="isType">地心地固直角坐标系:{{ XYZ2ENUDATA.input.X }},{{ XYZ2ENUDATA.input.Y }},{{ XYZ2ENUDATA.input.Z }}</span>
        <span v-else>大地坐标系:{{ XYZ2ENUDATA.input2.B }},{{ XYZ2ENUDATA.input2.H }},{{ XYZ2ENUDATA.input2.L }}</span> -->
      </el-card>

      <el-card  class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">请将地心地固直角坐标系转换成大地坐标系，根据大地坐标系转换成东北坐标系</p>
        <div >
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="100px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item :disabled="isType" label="大地坐标系" prop="time" >
                    <el-input :style="{width: '30%'}" placeholder="大地坐标系X" v-model="earth.B" :disabled="timesbj"></el-input>
                    <el-input :style="{width: '30%'}" placeholder="大地坐标系Y" v-model="earth.H" :disabled="timesbj"></el-input>
                    <el-input :style="{width: '30%'}" placeholder="大地坐标系Z" v-model="earth.L" :disabled="timesbj"></el-input>
                    <el-button type="primary" @click="handle" class="properties" v-if="operation">
                      转换
                    </el-button>
                  </el-form-item>
                  <el-form-item :disabled="plsType" label="东北天坐标" prop="time">
                    <el-input :style="{width: '90%'}" placeholder="东北天坐标系" v-model="northeast" :disabled="timesbj1"></el-input>
                    <el-button type="primary" @click="handle1" class="properties" v-if="operation1">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- <el-row>
                <el-col :span="24" :style="{ textAlign: 'center' }">
                  <el-button type="primary" @click="handle">
                    提交
                  </el-button>
                </el-col>
              </el-row> -->
            </el-form>
          </div>
        </div>
      </el-card>
    </div>
    <!--大地坐标-->
    <div class="allbody" v-show="earthY">
      <el-card>
          <div class="user" style="margin-top: 20px;">
            大地坐标系：
            <el-input v-model="XYZ2ENUDATA.input2.B " style="width: 220px"/>
            <el-input v-model="XYZ2ENUDATA.input2.H " style="width: 220px"/>
            <el-input v-model="XYZ2ENUDATA.input2.L " style="width: 220px"/>
            <el-button @click="refreshearch" class="properties">刷新</el-button>
          </div>
      </el-card>

      <el-card  class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">请将大地坐标系转换成地心地固直角坐标系，根据大地坐标系转换成东北坐标系</p>
        <div >
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="140px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item :disabled="isType" label="地心地固直角坐标系" prop="time">
                    <el-input :style="{width: '30%'}" placeholder="地心地固直角坐标系X" v-model="geocentricint.X" :disabled="timesbj"></el-input>
                    <el-input :style="{width: '30%'}" placeholder="地心地固直角坐标系Y" v-model="geocentricint.Y" :disabled="timesbj"></el-input>
                    <el-input :style="{width: '30%'}" placeholder="地心地固直角坐标系Z" v-model="geocentricint.Z" :disabled="timesbj"></el-input>
                    <el-button type="primary" @click="handleGrend" class="properties" v-if="operation">
                      转换
                    </el-button>
                  </el-form-item>
                  <el-form-item :disabled="plsType" label="东北天坐标" prop="time">
                    <el-input :style="{width: '90%'}" placeholder="东北天坐标系" v-model="northeast" :disabled="timesbj1"></el-input>
                    <el-button type="primary" @click="handle1" class="properties" v-if="operation1">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
      </el-card>
    </div>
    <!--东北坐标-->
    <div class="allbody" v-show="northeastZ">
      <el-card>
          <div class="user" style="margin-top: 20px;">
            东北坐标系：
            <el-input :style="{width: '60%'}" placeholder="东北天坐标系" v-model="XYZ2ENUDATA.output.e"></el-input>
           
            <el-button @click="refreshtheast" class="properties">刷新</el-button>
          </div>
      </el-card>

      <el-card  class="card" style="margin-top: 10px">
        <p style="font-size: 14px;font-weight: 600;">请将东北坐标系转换成地心地固坐标系，根据大地坐标系转换成东北坐标系</p>
        <div >
          <div style="padding-top: 25px;">
            <el-form ref="form" size="medium" label-width="120px">
              <el-row :gutter="24">
                <el-col :span="24">
                  <el-form-item :disabled="plsType" label="地心地固坐标系" prop="time">
                     <el-input :style="{width: '30%'}" placeholder="地心地固坐标系X" :disabled="timesbj" v-model="geocentricint.X " style="width: 220px"/>
                    <el-input :style="{width: '30%'}" placeholder="地心地固坐标系Y" :disabled="timesbj" v-model="geocentricint.Y " style="width: 220px"/>
                    <el-input :style="{width: '30%'}" placeholder="地心地固坐标系Z" :disabled="timesbj" v-model="geocentricint.Z " style="width: 220px"/>
                    <el-button type="primary" @click="handleGrend" class="properties" v-if="operation">
                      转换
                    </el-button>
                  </el-form-item>
                  <el-form-item :disabled="isType" label="大地坐标系" prop="time">
                    <el-input :style="{width: '30%'}" placeholder="大地坐标系X" :disabled="timesbj1" v-model="earth.B"></el-input>
                    <el-input :style="{width: '30%'}" placeholder="大地坐标系Y" :disabled="timesbj1" v-model="earth.H"></el-input>
                    <el-input :style="{width: '30%'}" placeholder="大地坐标系Z" :disabled="timesbj1" v-model="earth.L"></el-input>
                    <el-button type="primary" @click="transformation" class="properties" v-if="operation1">
                      转换
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
      </el-card>
    </div>
    <!-- <el-card v-else class="card" style="margin-top: 10px">
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
    </el-card> -->
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
      isType: false,
      plsType:true,
      XYZ2ENUDATA: {},
      geocentricint:{X:'',Y:'',Z:'',},
      earth:{B:'',H:'',L:''},
      northeast:'',
      geocentricX:true,
      earthY:false,
      northeastZ:false,
      timesbj:false,
      timesbj1:true,
      operation:true,
      operation1:false
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
    //刷新按钮
    refresh(){
       XYZ2ENU().then(response => {
        console.log(response)
        this.XYZ2ENUDATA = response.data
      })
      this.geocentricint = {X:'',Y:'',Z:'',}
      this.earth = {B:'',H:'',L:''}
      this.northeast = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    handle() {
      var earthnum = this.earth.B + "," + this.earth.H + "," + this.earth.L
      var v = this.XYZ2ENUDATA.input2.B + "," + this.XYZ2ENUDATA.input2.L + "," + this.XYZ2ENUDATA.input2.H
      if (earthnum === v) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '完成',
          type: 'success'
        }).then(() => {
          this.timesbj = true
          this.timesbj1 = false
          this.operation = false
          this.operation1 = true
        })
      } else {
         this.$confirm('转换错误，正确答案为：' + v, '提示', {
                confirmButtonText: '确定',
                type: 'warning'
              }).then(()=>{
                this.earth.B = this.XYZ2ENUDATA.input2.B
                this.earth.H = this.XYZ2ENUDATA.input2.L
                this.earth.L = this.XYZ2ENUDATA.input2.H
              })
      }
    },
    handle1() {
      var v = this.XYZ2ENUDATA.output.e
      if ((this.northeast) * 1 === v) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '完成',
          type: 'success'
        }).then(() => {
          this.timesbj = true
          this.timesbj1 = true
          this.operation = false
          this.operation1 = false
        })
      } else {
        this.$confirm('转换错误，正确答案为：'+ v, '提示',{
          confirmButtonText: '确定',
          type:'warning'
        }).then(()=>{
          this.northeast = this.XYZ2ENUDATA.output.e
        })
      }
    },
    //切换地心按钮
    subitGeocentric(){
      XYZ2ENU().then(response => {
        console.log(response)
        this.XYZ2ENUDATA = response.data
      })
      this.earth = {B:'',H:'',L:''}
      this.northeast = ''
      this.geocentricX = true
      this.earthY = false
      this.northeastZ = false
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //切换大地按钮
    subitearth(){
       XYZ2ENU().then(response => {
        console.log(response)
        this.XYZ2ENUDATA = response.data
      })
      this.geocentricint = {X:'',Y:'',Z:'',}
      this.northeast = ''
      this.earthY = true
      this.geocentricX = false
      this.northeastZ = false
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //切换东北坐标按钮
    subitnortheast(){
       XYZ2ENU().then(response => {
        console.log(response)
        this.XYZ2ENUDATA = response.data
      })
      this.earth = {B:'',H:'',L:''}
      this.geocentricint = {X:'',Y:'',Z:'',}
      this.earthY = false
      this.geocentricX = false
      this.northeastZ = true
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //地心地固提交
    handleGrend(){
      var earthnum = this.geocentricint.X + "," + this.geocentricint.Y + "," + this.geocentricint.Z
      var v = this.XYZ2ENUDATA.input.X + "," + this.XYZ2ENUDATA.input.Y + "," + this.XYZ2ENUDATA.input.Z
      if (earthnum === v) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '完成',
          type: 'success'
        }).then(() => {
          this.timesbj = true
          this.timesbj1 = false
          this.operation = false
          this.operation1 = true
        })
      } else {
         this.$confirm('转换错误，正确答案为：' + v, '提示', {
                confirmButtonText: '确定',
                type: 'warning'
              }).then(()=>{
                this.geocentricint.X = this.XYZ2ENUDATA.input.X
                this.geocentricint.Y = this.XYZ2ENUDATA.input.Y
                this.geocentricint.Z = this.XYZ2ENUDATA.input.Z
              })
      }
    },
    //大地坐标切换按钮
    transformation(){
      var earthnum = this.earth.B + "," + this.earth.H + "," + this.earth.L
      var v = this.XYZ2ENUDATA.input2.B + "," + this.XYZ2ENUDATA.input2.L + "," + this.XYZ2ENUDATA.input2.H
      if (earthnum === v) {
        this.$confirm('转换正确!', '提示', {
          confirmButtonText: '完成',
          type: 'success'
        }).then(() => {
          this.timesbj = true
          this.timesbj1 = true
          this.operation = false
          this.operation1 = false
        })
      } else {
         this.$confirm('转换错误，正确答案为：' + v, '提示', {
                confirmButtonText: '确定',
                type: 'warning'
              }).then(()=>{
                this.earth.B = this.XYZ2ENUDATA.input2.B
                this.earth.H = this.XYZ2ENUDATA.input2.L
                this.earth.L = this.XYZ2ENUDATA.input2.H
              })
      }
    },
    //大地刷新按钮
    refreshearch(){
      XYZ2ENU().then(response => {
        console.log(response)
        this.XYZ2ENUDATA = response.data
      })
      this.geocentricint = {X:'',Y:'',Z:''}
      this.northeast = ''
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
    //东北坐标刷新按钮
    refreshtheast(){
       XYZ2ENU().then(response => {
        console.log(response)
        this.XYZ2ENUDATA = response.data
      })
      this.geocentricint = {X:'',Y:'',Z:''}
      this.earth = {B:'',H:'',L:''}
      this.timesbj = false
      this.timesbj1 = true
      this.operation = true
      this.operation1 = false
    },
  }
}
</script>

<style scoped>
  .properties{
    margin-left: 10px;
  }
</style>
