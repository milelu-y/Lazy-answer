<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="title">
        <el-select v-model="queryParams.status" @change="getList" placeholder="请选择">
          <el-option
            v-for="item in paperStates"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
<!--      <el-form-item prop="title">-->
<!--        <el-input-->
<!--          v-model="queryParams.title"-->
<!--          placeholder="作业名称"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--          @input="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

    </el-form>

    <el-table border v-loading="loading" :data="reviewList">
      <el-table-column label="人员" align="center" prop="userId"></el-table-column>
      <el-table-column label="作业时间" align="center" prop="createTime"/>
      <el-table-column label="答题时长" align="center" prop="totalTime" />
      <el-table-column label="学员总分" align="center" prop="userScore"/>
      <el-table-column label="合格分" align="center" prop="qualifyScore"/>
      <el-table-column label="作业总分" align="center" prop="totalScore"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
<!--      <el-table-column label="阅题人" align="center" prop="status"/>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="{ name: 'reviewDetail', params:{id: scope.row.id}}" style="color: #00afff">
            <i class="el-icon-document"></i> 查看/批阅
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import {listTask, getTask, delTask, addTask, updateTask, exportTask} from "@/api/vm/task";
import {listCourse} from "@/api/vm/course";
import _ from "lodash"
import {reviewExamList} from "@/api/vm/testPaper";
export default {
  name: "reviewList",
  data() {
    return {
      paperStates: [{
        value: 0,
        label: '进行中'
      }, {
        value: 1,
        label: '待阅题'
      }, {
        value: 2,
        label: '已阅题'
      }, {
        value: 3,
        label: '!已废弃'
      }],
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 作业表格数据
      reviewList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        status:1
      },
      // 表单参数
      form: {},
      openTypes: [],
      statusOptions: [],
      id:null
    };
  },
  created() {
    this.id = this.$route.params.id
    this.queryParams.id=this.id;
    this.getList();
    this.getDicts("vm_job_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询作业列表 */
    getList() {
      this.loading = true;
      reviewExamList(this.queryParams).then(response => {
        this.reviewList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        courseId: null,
        title: null,
        singleCount: null,
        multipleCount: null,
        createTime: null,
        updateTime: null,
        judgment: null,
        fillCount: null,
        aqCount: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      // this.open = true;
      // this.title = "添加作业";
      this.$router.push('/vm/question/form/')
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTask(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改作业";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTask(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 链接到试题管理 */
    handleLink(row) {
      console.log(row)
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除作业编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delTask(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
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
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
  }
};
</script>
