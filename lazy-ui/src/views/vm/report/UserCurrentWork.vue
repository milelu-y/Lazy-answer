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
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['vm:task:export']"
        >导出
        </el-button>
      </el-col>
    </el-row>

    <el-table
      border
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column
        prop="title"
        label="章节"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="nums"
        label="提交人数"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="numbers"
        label="平均分"
        align="center">
      </el-table-column>
      <el-table-column
        prop="total"
        label="总分"
        align="center">
      </el-table-column>
       <el-table-column
        prop="totalScore"
        label="操作"
        align="center">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope)" type="text" size="small">统计分析</el-button>
          </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="统计情况"
      :visible.sync="open"
      width="30%"
      :before-close="handleClose">
      <div class="items">最高分：</div>
      <div class="items">最低分：</div>
      <div class="items">平均分：</div>
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="dialogVisiblelist">确 定</el-button>
      </span>
    </el-dialog>
    <user-select ref="userSelect" @selectHandle="selectHandle"></user-select>
    <exam-select ref="examSelect" @selectHandle="selectExamHandle"></exam-select>
  </div>
</template>

<script>

import UserSelect from "@/components/UserSelect";
import ExamSelect from "@/components/ExamSelect";
import {userCurrentWork} from "@/api/vm/report";
import {exportTask} from "@/api/vm/task";

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
      tableData: [{title:'第一章',nums:'48',numbers:'78',total:'3744'}],
      open:false,
      handleClose:false,
      //导出弹出层
      exportLoading:false
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
    },
    //统计分析
    handleClick(){
      this.open = true
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
    },
    dialogVisiblelist(){
      this.open = false
    },
    //导出列表
    handleExport() {
      const queryParams = this.queryParams;
      console.log("列表",queryParams)
      this.$confirm('是否确认导出所选的作业得分情况?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportTask(queryParams);
      }).then(response => {
        this.download(response.msg);
        console.log("999",response)
        this.exportLoading = false;
      }).catch(() => {
      });
    },
  }
}
</script>

<style scoped>
  .items{
    padding: 5px 0;
  }
</style>
