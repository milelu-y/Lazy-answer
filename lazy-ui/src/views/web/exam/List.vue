<template>
  <div>
     <el-alert
        title="消息通知"
        type="warning"
        :description="endtime"
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
                  <el-col :span="8">答题时间： {{ item.limitTime ? item.startTime + '~' + item.endTime : '不限时' }}</el-col>
                </el-row>
              </div>
            </el-col>
            <el-col :span="4" style="display: flex; justify-content: flex-end; align-items: center;">
              <div>
                <el-tag v-if="item.status===0" type="success">进行中</el-tag>
                <el-tag v-if="item.status===3" type="error">已结束</el-tag>
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
        endtime:''
      },
    }
  },
  created() {
    this.getCourseList();
    this.getList();
    this.endtime = '你的作业提交时间离截止时间还剩48小时'
  },
  methods: {
    getList() {
      listUserExam(this.queryParams).then(response => {
        this.listExams = response.rows
        console.log('课题',this.listExams)
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
        this.total = response.total;
      })
    }
  }
}
</script>

<style scoped>
</style>
