<template>
  <div style="max-width: 1500px;margin: 0 auto;padding: 16px;float: right;width: 60%">
    <div><h2>{{exData.title}}</h2></div>
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
      <span>伪距:{{ data.input }}、TGD:{{ data.input2 }}</span>
    </el-card>
    <el-card class="card" style="margin-top: 10px">
      <p v-if="isType">ps:计算各频点的伪距修正值，得到修正后的伪距</p>
      <p v-else>ps:计算各频点的电离层延迟值</p>
      <div style="text-align: center">
        <div style="padding-top: 25px;">
          <el-form ref="form" size="medium" label-width="100px">
            <el-row :gutter="24">
              <el-col :span="24">
                <el-form-item label="伪距" prop="time">
                  <el-input :disabled="!isType" :style="{width: '100%'}" v-model="wj" placeholder="伪距"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="24" v-if="!isType">
              <el-col :span="24">
                <el-form-item label="电离层延迟" prop="time">
                  <el-input :style="{width: '100%'}" v-model="dl" placeholder="电离层延迟"></el-input>
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
  </div>
</template>

<script>
import {exp10_shuangpinDianLiCeng} from "@/api/vm/vmTest";
import {getExperiment} from "@/api/vm/ex";

export default {
  name: "GSM",
  data() {
    return {
      exData:{},
      isType: true,
      data: {},
      wj: null,
      dl: null,
    }
  },
  created() {
    const query = this.$route.query
    getExperiment(query.id).then(response => {
      this.exData = response.data
    })
    exp10_shuangpinDianLiCeng().then(response => {
      console.log(response)
      this.data = response.data
    })
  },
  methods: {
    handle() {
      if (this.isType) {
        var v = this.wj
        if ((v * 1) === this.data.output.result1) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '下一题',
            type: 'success'
          }).then(() => {
          })
          this.isType = false
        } else {
          this.notifyError("转换错误，请重试")
        }
      } else {
        var v = this.dl
        if ((v * 1) === this.data.input3) {
          this.$confirm('转换正确!', '提示', {
            confirmButtonText: '完成',
            type: 'success'
          }).then(() => {
          })
          this.isType = false
        } else {
          this.notifyError("转换错误，请重试")
        }
      }

    },
  }

}
</script>

<style scoped>

</style>
