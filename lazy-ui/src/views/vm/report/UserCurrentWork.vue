<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="userId">
        <el-input
          placeholder="选择用户"
          v-model="queryParams.nickName">
          <i style="cursor: pointer" @click="selectUser" slot="suffix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </el-form-item>

      <el-form-item prop="examId">
        <el-input
          placeholder="选择作业"
          v-model="queryParams.title">
          <i style="cursor: pointer" @click="selectExam" slot="suffix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      border
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="title"
        label="标题"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userScore"
        label="得分"
        width="180">
      </el-table-column>
      <el-table-column
        prop="totalScore"
        label="总分">
      </el-table-column>
    </el-table>
    <user-select ref="userSelect" @selectHandle="selectHandle"></user-select>
    <exam-select ref="examSelect" @selectHandle="selectExamHandle"></exam-select>
  </div>
</template>

<script>

import UserSelect from "@/components/UserSelect";
import ExamSelect from "@/components/ExamSelect";
import {userCurrentWork} from "@/api/vm/report";

export default {
  name: "UserCurrentWork",
  components: {UserSelect, ExamSelect},
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickName: null,
        username: null,
        title: null,
        examId: null,
        userId: null
      },
      // 显示搜索条件
      showSearch: true,
      //弹出用户选择框
      userDialogVisible: false,
      //用户数据
      userList: [],
      data:[],
      loading: false,
      total: 0,
      tableData: []
    }
  },
  created() {

  },
  methods: {
    selectUser() {
      this.$refs.userSelect.dialogVisible = true;
    },
    selectExam() {
      this.$refs.examSelect.dialogVisible = true;
    },
    selectHandle(row) {
      this.queryParams.username = row.userName
      this.queryParams.nickName = row.nickName
      this.queryParams.userId = row.userId
      this.$refs.userSelect.dialogVisible = false;
      //查询报表
    },
    selectExamHandle(row) {
      this.queryParams.title = row.title
      this.queryParams.examId = row.id
      this.$refs.examSelect.dialogVisible = false;
    },
    handleQuery() {
      console.log(this.queryParams)
      userCurrentWork(this.queryParams).then(response => {
        console.log(response)
        this.tableData.push(response.data)
      })
    }
  }
}
</script>

<style scoped>

</style>
