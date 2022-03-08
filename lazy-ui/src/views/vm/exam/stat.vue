<template>
  <div class="app-container">
    <h3> 明细情况 </h3>
    <el-card shadow="always">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="学员概览" name="user">
          <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
            <el-form-item prop="title">
              <el-input
                v-model="queryParams.nickName"
                placeholder="搜索人员"
                clearable
                size="small"
                @change="handleQuery"
              />
            </el-form-item>
          </el-form>
          <el-table
            :data="userTableData"
            height="250"
            border
            style="width: 100%">
            <el-table-column
              prop="nickName"
              label="学员"
              width="180">
            </el-table-column>
            <el-table-column
              prop="count"
              label="答题次数"
              width="180">
            </el-table-column>
            <el-table-column
              prop="max"
              label="最高分">
            </el-table-column>
            <el-table-column
              prop="adopt"
              label="是否通过">
            </el-table-column>
            <el-table-column
              label="操作">
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getUserStat"
          />
        </el-tab-pane>
        <el-tab-pane label="错题分析" name="error">错题分析</el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import {userStat} from "@/api/vm/exam";

export default {
  name: "stat",
  data() {
    return {
      activeName: 'user',
      userTableData: [],
      total:0,

      queryParams: {
        pageNum: 1,
        pageSize: 10,

      }
    };
  },
  created() {
    var id = this.$route.params.id;
    console.log("id", id)
    this.queryParams.examId=id;
    //查询学员统计
    this.getUserStat();
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleQuery() {
      this.getUserStat();
    },
    getUserStat(){
      this.userLoading = true
      userStat(this.queryParams).then(response => {
        this.userTableData = response.rows;
        this.total = response.total;
        this.userLoading = false
      })
    }
  }

}
</script>

<style scoped>
h3 {
  display: block;
  font-size: 1.17em;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}
</style>
