<template>
    <div >
      <el-dialog  :visible.sync="deviceDebug" width="50%">
        <div>
          <div class="form-box">
            <el-row style="width: 100%">

            </el-row>
          </div>
        </div>
      </el-dialog>
    </div>
</template>

<script>
import {getExam} from "@/api/vm/exam";

export default {
  name: "check",
  data(){
    return{
      deviceDebug:false,
      loading:true,
      checkData:{},
      postForm: {
        examId: null
      },
    }
  },
  created() {
    var id = this.$route.params.id;
    if (typeof id !== 'undefined') {
      this.postForm.examId = id;
      this.fetchData();
    }
  },
  methods:{
    fetchData() {
      getExam(this.postForm.examId).then(response=>{
        this.checkData=response.data
      })
    }
  }
}
</script>

<style scoped>
.form-box{
  font-size: 14px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  line-height: 42px;
}
</style>
