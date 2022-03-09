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
            v-loading="errorLoading"
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
              prop="passed"
              label="是否通过" :formatter="passedFormatter">
            </el-table-column>
            <el-table-column
              label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-view"
                  @click="handleView(scope.row)"
                >查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getUserStat"
          />
          <el-drawer
            append-to-body
            title="答题记录"
            :visible.sync="userDrawer"
            direction="rtl">
            <el-card shadow="always" v-for="(row , index) in pagingData">
              <el-col :span="12" style="margin: 5px 0px 5px 0px">答题时间：{{
                  parseTime(new Date(row.createTime)) }}</el-col>
              <el-col :span="12" style="margin: 5px 0px 5px 0px">用时：</el-col>
              <el-col :span="12" style="margin: 5px 0px 5px 0px">得分：{{ row.userScore }}</el-col>
              <el-col :span="12" style="margin: 5px 0px 5px 0px">
                是否合格：{{ row.userScore >= row.qualifyScore ? '是' : '否' }}
              </el-col>
              <el-col :span="12" style="margin: 5px 0px 5px 0px">状态：
                <span v-if="row.status===0">进行中</span>
                <span v-if="row.status===1">待阅题</span>
                <span v-if="row.status===2">已阅题</span>
              </el-col>
              <el-col :span="24" style="margin-top: 10px;margin-bottom: 10px">
                <el-button @click="reviewDetail(row.id)">查看作业</el-button>
              </el-col>
            </el-card>
          </el-drawer>
        </el-tab-pane>
        <el-tab-pane label="错题分析" name="error">
<!--          <el-form :model="queryErrorParams" ref="queryForm" :inline="true" label-width="68px">-->
<!--            <el-form-item prop="title">-->
<!--              <el-input-->
<!--                v-model="queryParams.nickName"-->
<!--                placeholder="搜索人员"-->
<!--                clearable-->
<!--                size="small"-->
<!--                @change="handleQuery"-->
<!--              />-->
<!--            </el-form-item>-->
<!--          </el-form>-->
          <el-table
            :data="errorTableData"
            height="250"
            border
            style="width: 100%">
            <el-table-column
              prop="content"
              show-overflow-tooltip
              label="试题内容"
              width="350">
            </el-table-column>
            <el-table-column
              prop="type"
              label="试题类型" :formatter="typeFormat">
            </el-table-column>
            <el-table-column
              prop="count"
              label="答题次数"
              width="100">
            </el-table-column>
            <el-table-column
              prop="errorCount"
              label="错误次数">
            </el-table-column>
            <el-table-column
              prop="errorPro"
              label="错误率">
            </el-table-column>
          </el-table>
          <pagination
            v-show="errorTotal>0"
            :total="errorTotal"
            :page.sync="queryErrorParams.pageNum"
            :limit.sync="queryErrorParams.pageSize"
            @pagination="getErrorStat"
          />
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import {errorStat, paging, userStat} from "@/api/vm/exam";
export default {
  name: "stat",
  data() {
    return {
      errorLoading:false,
      activeName: 'user',
      userTableData: [],
      errorTableData: [],
      pagingData: [],
      total: 0,
      errorTotal: 0,
      userDrawer: false,
      types:[],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      queryErrorParams: {
        pageNum: 1,
        pageSize: 10,
      }
    };
  },
  created() {
    var id = this.$route.params.id;
    this.queryParams.examId = id;
    this.queryErrorParams.examId = id;
    //查询学员统计
    this.getUserStat();
    this.getErrorStat();
    this.getDicts("vm_qu_type").then(response => {
      this.types = response.data;
    });
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },

    handleQuery() {
      this.getUserStat();
    },
    getUserStat() {
      this.userLoading = true
      userStat(this.queryParams).then(response => {
        this.userTableData = response.rows;
        this.total = response.total;
        this.userLoading = false
      })
    },
    getErrorStat(){
      this.errorLoading = true
      errorStat(this.queryErrorParams).then(response=>{
        this.errorTableData = response.rows;
        this.errorTotal = response.total;
        this.errorLoading=false
      })
    },
    //打开作业答题记录列表进行查看
    handleView(row) {
      this.userDrawer = true;
      this.queryParams.userId = row.userId
      paging(this.queryParams).then(response => {
        console.log(response);
        this.pagingData = response
      })
    },
    reviewDetail(id){
      this.$router.push({name:'reviewDetail',params: {id:id}})
    },
    passedFormatter(row) {
      console.log(row)
      if (row.passed) {
        return <span style="color:0ceb5f">通过</span>
      } else {
        return <span style="color:red">未通过</span>
      }
    },
    typeFormat(row, column) {
      return this.selectDictLabel(this.types, row.type);
    },
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
