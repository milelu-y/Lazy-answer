<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="试卷名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          @change="handleQuery"
        />
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['vm:testQuestion:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
    </el-row>
    <el-table border :data="paperList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="试卷名称" align="center" prop="title" width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="handleUpdate(scope.row)">{{ scope.row.title }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="组卷方式" align="center" prop="type" width="150"></el-table-column>
      <el-table-column label="试卷分类" align="center" prop="category"></el-table-column>
      <el-table-column label="总分" align="center" prop="totalScore" show-overflow-tooltip></el-table-column>
      <el-table-column label="创建人" align="center" prop="userId" show-overflow-tooltip></el-table-column>
      <el-table-column label="创建时间" align="center" prop="gmtCreate"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <router-link :to="{ name: 'quManage', params:{id: scope.row.id}}" style="color: #00afff">
            <i class="el-icon-view"></i> 预览
          </router-link>
          <el-button icon="el-icon-document-copy" type="text">复制</el-button>
          <router-link :to="{ name: 'quManage', params:{id: scope.row.id}}" style="color: #00afff">
            <i class="el-icon-loading"></i> 创建考试
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="创建试卷" :visible.sync="dialogVisible" width="30%">
      <el-form ref="postForm" :model="postForm" :rules="rules" label-position="left" label-width="100px">
        <el-form-item label="试卷名称" prop="title">
          <el-input size="medium" v-model="postForm.title"/>
        </el-form-item>
        <el-form-item label="题库分类" prop="category">
          <el-select v-model="postForm.category" placeholder="请选择" style="width: 100%">
            <el-option
              v-for="item in courseOptions"
              :key="item.id"
              :label="item.title"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="组卷方式" prop="type">
          <el-radio v-model="postForm.type" label="0">选题组卷</el-radio>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleCreate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {delTestPaper, listTestPaper} from "@/api/vm/testPaper";
import {listCourse} from "@/api/vm/course";
import {param} from "@/utils";
import paperForm from "@/views/vm/testPaper/paperForm";
import {delExam} from "@/api/vm/exam";

export default {
  name: "index",
  data() {
    return {
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 表格数据
      paperList: [],
      // 显示搜索条件
      showSearch: true,
      //加载
      loading: true,
      //创建试卷窗口
      dialogVisible: false,
      postForm: {
        type: '0'
      },
      courseOptions: [],
      rules: {
        title: [{required: true, message: '试卷名称不能为空！'}],
        category: [{required: true, message: '试卷分类不能为空！'}],
        type: [{required: true, message: '请选择组卷方式', trigger: 'change'}],
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        title: null,
        singleCount: null,
        multipleCount: null,
        judgment: null,
        fillCount: null,
        aqCount: null
      },
    }
  },
  created() {
    this.getList();
    listCourse().then(response => {
      this.courseOptions = response.rows;
    })
  },
  methods: {
    /** 查询考试管理列表 */
    getList() {
      this.loading = true;
      listTestPaper(this.queryParams).then(response => {
        this.paperList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      //跳转到添加考试界面
      //打开窗口
      this.dialogVisible = true;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /**
     * 创建试卷
     */
    handleCreate() {
      this.$refs["postForm"].validate(valid => {
        if (!valid) {
          return
        }
        this.$router.push({
          name: 'paperAdd',
          params: {title: this.postForm.title, type: this.postForm.type, category: this.postForm.category}
        })
      })
    },
    handleDelete(row){
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除作业编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delTestPaper(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    handleUpdate(row) {
      const ids = row.id || this.ids[0];
      if (ids){
        this.$router.push({
          name: 'paperUpdate',
          params: {id:ids!=null&&ids!=undefined?ids:row.id,title: this.postForm.title, type: this.postForm.type, category: this.postForm.category}
        })
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
  }
}
</script>

<style scoped>

</style>
