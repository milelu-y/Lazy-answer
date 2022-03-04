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
        prop="classall"
        label="班级"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="names"
        label="姓名"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="jobname"
        label="作业名称"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="subittime"
        label="提交时间"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="score"
        label="得分"
        align="center"
        >
      </el-table-column>
      <el-table-column
        prop="totalScore"
        label="总分"
        align="center">
      </el-table-column>
      <el-table-column
        prop="operation"
        label="操作"
        align="center">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <!-- <el-button type="text" size="small">编辑</el-button> -->
          </template>
        </el-table-column>
    </el-table>
    <!-- 查看作业情况 -->
    <el-dialog
      title="作业完成情况"
      :visible.sync="open"
      width="30%"
      :before-close="handleClose">
      <span>错题详情</span>
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="dialogVisible">确 定</el-button>
      </span>
    </el-dialog>
    <user-select ref="userSelect" @selectHandle="selectHandle"></user-select>
  </div>
</template>

<script>
import UserSelect from "@/components/UserSelect";
import {userAllWork} from "@/api/vm/report";

export default {
  name: "UserAllWork",
  components: {UserSelect},
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
      data: [],
      loading: false,
      total: 0,
      tableData: [
        {classall:'20级1213',names:'张三',jobname:'第一章',subittime:'2022-3-3 11:20:30',score:'60',totalScore:'60',},
        {classall:'20级1314',names:'admin',jobname:'第二章',subittime:'2022-3-3 11:50:30',score:'80',totalScore:'80',},
        ],
      open:false
    }
  },
  methods: {
    selectUser() {
      this.$refs.userSelect.dialogVisible = true;
    },
    selectHandle(row) {
      this.queryParams.username = row.userName
      this.queryParams.nickName = row.nickName
      this.queryParams.userId = row.userId
      this.$refs.userSelect.dialogVisible = false;
      //查询报表
    },
    handleQuery() {
      userAllWork(this.queryParams.userId).then(response => {
        this.tableData = response.data
        // console.log('???',this.tableData)
      })
    },
    //查看得分情况详情
    handleClick(row){
      this.open = true
    },
    //关闭弹出层
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
    dialogVisible(){
      this.open = false
    },
  }
}
</script>

<style scoped>

</style>
