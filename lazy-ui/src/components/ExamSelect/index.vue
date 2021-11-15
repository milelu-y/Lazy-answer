<template>
  <el-dialog
    title="用户选择器"
    :visible.sync="dialogVisible"
    width="30%">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="roleIds">
        <el-input v-model="queryParams.title" placeholder="作业标题" @input="getExamList"></el-input>
      </el-form-item>
    </el-form>
    <el-table :data="examList" highlight-current-row v-loading="loading" @current-change="handleCurrentChange">
      <el-table-column property="title" label="标题" width="150"></el-table-column>
      <el-table-column property="totalScore" label="总分" width="200"></el-table-column>
      <el-table-column property="qualifyScore" label="合格分数"></el-table-column>
      <el-table-column property="limitCount" label="限做次数"></el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getExamList"
    />
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="selectHandle">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
import {listExam} from "@/api/vm/exam";

export default {
  name: "index",
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleIds: []
      },
      //作业数据
      examList: [],
      loading: false,
      // 显示搜索条件
      showSearch: true,
      total: 0,
      dialogVisible: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      currentRow: null
    }
  },
  created() {
    this.getExamList()
  },
  methods: {
    getExamList() {
      this.loading=true
      listExam(this.queryParams).then(response => {
        console.log(response)
        this.examList=response.rows
        this.total = response.total;
        this.loading = false;
      })
    },
    handleCurrentChange(val) {
      this.currentRow = val
    },
    selectHandle() {
      this.$emit("selectHandle", this.currentRow)
    }
  }
}
</script>

<style scoped>

</style>
