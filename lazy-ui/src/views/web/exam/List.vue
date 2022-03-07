<template>
  <div>
     <el-alert
        title="消息通知"
        type="warning"
        :description="endatetime"
        style="margin-bottom:15px;">
      </el-alert>
    <el-row :gutter="20">
      <el-col :span="5">
        <el-card style="cursor: pointer;margin-top: 1px" shadow="hover" v-for="(item,index) in courseList">
          <div @click="getExamList(item)" style="text-align:center;">
            <span style="font-weight: 600">{{ item.title }}</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="19">
        <el-card style="background: rgb(246, 248, 253); border-radius: 0px; padding: 10px 0px;">
          <el-form label-width="68px">
            <el-input v-model="queryParams.title" @input="getList" placeholder="搜索作业名称" style="width: 200px"></el-input>
          </el-form>
        </el-card>
        <el-card shadow="never" style="margin-top: -1px; font-size: 14px; border-radius: 0px;"
                v-for="(item,index) in listExams">
          <el-row type="flex" style="line-height: 28px">
            <el-col :span="20">
              <div style="font-weight: 700; font-size: 16px;">
                <router-link :to="{ name: 'check', params:{id: item.id}}">
                  {{ item.title }}
                </router-link>
              </div>
              <div style="color: rgb(102, 102, 102); font-size: 12px;">
                <el-row>
                  <el-col :span="4">时长：{{ item.totalTime }}</el-col>
                  <el-col :span="4">总分：{{ item.totalScore }} 分</el-col>
                  <el-col :span="4">及格分：{{ item.qualifyScore }} 分</el-col>
                  <el-col :span="4">答题时间：{{ item.totalTime }} 分钟</el-col>
                  <!-- <el-col :span="8">答题时间： {{ item.limitTime ? item.startTime + '~' + item.endTime : '不限时' }}</el-col> -->
                </el-row>
              </div>
            </el-col>
            <el-col :span="4" style="display: flex; justify-content: flex-end; align-items: center;">
              <div>
                <el-tag v-if="item.status===2" type="success">进行中</el-tag>
                <el-tag v-if="item.status===1" type="primary">完成</el-tag>
                <el-tag v-if="item.status===0" type="error">已结束</el-tag>
              </div>
            </el-col>
          </el-row>
        </el-card>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>

  </div>
</template>

<script>
import {listExam, listExamJoinPaper, listUserExam} from "@/api/vm/exam";
import {listCourse} from "@/api/vm/course";

export default {
  name: "OnlineList",
  data() {
    return {
      listExams: [],
      courseList: [],
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        courseId:null,
        endatetime:'',
        status:null,
        endtime:''
      },
    }
  },
  created() {
    this.getCourseList();
    this.getList();
    listExamJoinPaper(this.queryParams).then(response => {
        this.listExams = response.rows
       this.endtime = listExams.endtime
      })
  
    var date1 = new Date()//获取现在的时间
    var date2 = new Date('2022-3-8 17:39:50')
    
    var s1 = date1.getTime(),s2 = date2.getTime();
    var total = (s2 - s1)/1000;
    
    var day = parseInt(total / (24*60*60));//计算天数
    var afterDay = total - day*24*60*60;//取得算出天数后剩余的秒数
    var hour = parseInt(afterDay/(60*60));//计算小时数
    var afterHour = total - day*24*60*60 - hour*60*60;//取得算出小时数后剩余的秒数
    var min = parseInt(afterHour/60);//计算整数分
    // var afterMin = total - day*24*60*60 - hour*60*60 - min*60;//取得算出分后剩余的秒数
    // this.tableList[i].cumputerTimes = day + '天' + hour + '时' + min + '分' + afterMin + '秒'
    this.endatetime = '你的作业提交时间离截止时间还剩' + day + '天' + hour + '时' + min + '分' 
    if(total <=0) {
      this.endatetime = '你的作业提交时间离截止时间还剩0天0时0分'
     
    } 
  },
  methods: {
    getList() {
      listUserExam(this.queryParams).then(response => {
        this.listExams = response.rows
        this.total = response.total;
      })
    },
    getCourseList() {
      listCourse().then(response => {
        this.courseList = response.rows;
      });
    },
    getExamList(item) {
      console.log(item)
      this.queryParams.courseId = item.id;
      listExamJoinPaper(this.queryParams).then(response => {
        this.listExams = response.rows
        console.log("times",this.listExams)
        this.total = response.total;
      })
    }
  }
}
</script>

<style scoped>
</style>
