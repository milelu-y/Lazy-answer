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
      tableData: []
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
      })
    }
  }
}
</script>

<style scoped>

</style>
