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
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport()"
          v-hasPermi="['vm:task:export']"
        >导出
        </el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-edit"
          :disabled="single"
          size="mini"
          @click="statistics"
          v-hasPermi="['vm:task:export']"
        >统计
        </el-button>
      </el-col> -->
    </el-row>
    <el-table
      border
      :data="tableData"
      style="width: 100%"
      >
      <el-table-column type="selection" width="55" align="center"/>
      <!-- <el-table-column
        prop="classall"
        label="班级"
        align="center"
        >
      </el-table-column> -->
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
      <!-- <el-table-column
        prop="totalScore"
        label="总分"
        align="center">
      </el-table-column> -->
      <el-table-column
        prop="operation"
        label="操作"
        align="center">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
    </el-table>
    <!-- 查看作业情况 -->
    <el-dialog
      title="作业得分情况"
      :visible.sync="open"
      width="30%"
      :before-close="handleClose">
      <div class="choice">选择题：
        <el-tag
          style="margin:0 5px"
          v-for="item in items"
          :key="item.label"
          :type="item.type"
          effect="dark">
          {{ item.label }}
        </el-tag>
      </div>
      <div class="judge">判断题：
        <el-tag
          style="margin:0 5px"
          v-for="item in judge"
          :key="item.label"
          :type="item.type"
          effect="dark">
          {{ item.label }}
        </el-tag>
      </div>
      <div class="answer">简答题：
        <el-tag
          style="margin:0 5px"
          v-for="item in answer"
          :key="item.label"
          :type="item.type"
          effect="dark">
          {{ item.label }}
        </el-tag>
      </div>
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="dialogVisiblelist">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 统计得分情况 -->
    <el-dialog
      title="得分情况"
      :visible.sync="opennice"
      width="30%"
      :before-close="handleClose">
      <span slot="footer" class="dialog-footer">
        <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
        <el-button type="primary" @click="dialogVisiblelist">确 定</el-button>
      </span>
    </el-dialog>
    <user-select ref="userSelect" @selectHandle="selectHandle"></user-select>
  </div>
</template>

<script>
import UserSelect from "@/components/UserSelect";
import {userAllWork} from "@/api/vm/report";
import {exportTask} from "@/api/vm/task";

export default {
  name: "UserAllWork",
  components: {UserSelect},
  data() {
    return {
      // 选中数组
      ids: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickName: null,
        username: null,
        title: null,
        examId: null,
        userId: null,
      },
      open:false,
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
        {names:'张三',jobname:'第一章',subittime:'2022-3-3 11:20:30',score:'60',totalScore:'60',},
        {names:'admin',jobname:'第二章',subittime:'2022-3-3 11:50:30',score:'80',totalScore:'80',},
        ],
      // 查看弹出层
      open:false,
      //统计弹出层
      opennice:false,
      exportLoading:false,
      //弹出层得分情况
      items: [
          { type: 'success', label: '1' },
          { type: 'success', label: '2' },
          { type: 'danger', label: '3' },
          { type: 'danger', label: '4' },
          { type: 'success', label: '5' },
          { type: 'success', label: '6' },
          { type: 'success', label: '7' },
          { type: 'success', label: '8' },
          { type: 'success', label: '9' },
          { type: 'success', label: '10' }
        ],
        judge:[
          { type: 'success', label: '11' },
          { type: 'success', label: '12' },
          { type: 'danger', label: '13' },
          { type: 'danger', label: '14' },
          { type: 'success', label: '15' },
        ],
        answer:[
          { type: 'success', label: '16' },
          { type: 'success', label: '17' },
          { type: 'danger', label: '18' },
        ],
      // 非单个禁用
      // single: true,
    }
  },
  created(){
    
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
    dialogVisiblelist(){
      this.open = false
    },
    //导出按钮
     handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有作业数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportTask(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    },
     // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      // this.single = selection.length !== 1
    },
  }
}
</script>

<style scoped>
  .choice {
    padding: 5px 0;
  }
  .judge{
    padding: 5px 0;
  }
  .answer{
    padding: 5px 0;
  }
</style>
