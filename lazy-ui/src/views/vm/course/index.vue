<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入课程标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="副标题" prop="subtitle">
        <el-input
          v-model="queryParams.subtitle"
          placeholder="请输入副标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['vm:course:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['vm:course:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['vm:course:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['vm:course:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="任课教师" align="center" prop="teacher.name" show-overflow-tooltip/>
      <el-table-column label="课程标题" align="center" prop="title" show-overflow-tooltip/>
      <el-table-column label="副标题" align="center" prop="subtitle" show-overflow-tooltip/>
      <el-table-column label="销售价格" align="center" prop="price" c/>
      <el-table-column label="总课时" align="center" prop="lessonNum" width="60"/>
      <el-table-column label="封面图片" align="center" width="140" prop="cover">
        <template slot-scope="scope">
          <div class="demo-image__preview">
            <el-image
              style="width: 50%; height: 50%"
              :src="api+scope.row.resource.filePath"
              :preview-src-list="[api+scope.row.resource.filePath]">
            </el-image>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="销售数量" align="center" prop="buyCount" width="80"/>
      <el-table-column label="浏览数量" align="center" prop="viewCount" width="80"/>
      <el-table-column label="课程状态" align="center" prop="status"/>
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="gmtModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleSetTask(scope.row)"-->
<!--            v-hasPermi="['vm:course:task']"-->
<!--          >配置作业-->
<!--          </el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['vm:course:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="讲师" prop="teacherId">
              <el-select v-model="form.teacherId" filterable placeholder="请选择">
                <el-option
                  v-for="item in teachers"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="课程分类" prop="subjectId">
              <el-select v-model="form.subjectId" filterable placeholder="请选择">
                <el-option
                  v-for="item in categorys"
                  :key="item.id"
                  :label="item.title"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="课程标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入课程标题"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="副标题" prop="subtitle">
              <el-input v-model="form.subtitle" type="textarea" placeholder="请输入副标题"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="销售价格" prop="price">
              <el-input-number v-model="form.price" placeholder="请输入销售价格" controls-position="right" :min="0"
                               :max="10000" precision="2"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总课时" prop="lessonNum">
              <el-input-number v-model="form.lessonNum" placeholder="请输入总课时" controls-position="right" :min="0"
                               :max="500"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="课程封面" prop="cover">
          <input-picture ref="picture" :form="form" :data="{fieldAliase:'封面'}"></input-picture>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listCourse, getCourse, delCourse, addCourse, updateCourse, exportCourse} from "@/api/vm/course";
import {listTeacher} from "@/api/vm/teacher";
import {listSubject} from "@/api/vm/subject";
import InputPicture from "@/components/FormInput/InputPicture";

export default {
  name: "Course",
  components: {InputPicture},
  data() {
    return {
      api: process.env.VUE_APP_BASE_API,
      //讲师
      teachers: [],
      //课程分类
      categorys: [],
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
      // 课程表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        subjectId: null,
        subjectParentId: null,
        task: null,
        title: null,
        subtitle: null,
        price: 0,
        lessonNum: 0,
        cover: null,
        buyCount: null,
        viewCount: null,
        version: null,
        status: null,
        isDeleted: null,
        gmtCreate: null,
        gmtModified: null
      },
      // 表单参数
      form: {price: 0, lessonNum: 0},
      // 表单校验
      rules: {
        teacherId: [
          {required: true, message: "课程讲师不能为空", trigger: "blur"}
        ],
        subjectId: [
          {required: true, message: "课程专业不能为空", trigger: "blur"}
        ],
        title: [
          {required: true, message: "课程标题不能为空", trigger: "blur"}
        ],
        price: [
          {required: true, message: "课程销售价格不能为空", trigger: "blur"}
        ],
        lessonNum: [
          {required: true, message: "总课时不能为空", trigger: "blur"}
        ],
        cover: [
          {required: true, message: "课程封面图片路径不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getTeachers();
    this.getSubjects();
    this.getList();
  },
  methods: {
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getTeachers() {
      listTeacher().then(response => {
        this.teachers = response.rows;
      })
    },
    getSubjects() {
      listSubject().then(response => {
        this.categorys = response.rows;
      })
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
        teacherId: null,
        subjectId: null,
        subjectParentId: null,
        task: null,
        title: null,
        subtitle: null,
        price: null,
        lessonNum: null,
        cover: null,
        buyCount: null,
        viewCount: null,
        version: null,
        status: null,
        isDeleted: null,
        gmtCreate: null,
        gmtModified: null
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
      this.open = true;
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程";
        let that = this
        setTimeout(function (){
          that.$refs.picture.setFieldValue([response.data.resource]);
        },200)
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.buildParam(this.form);
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCourse(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            console.log("this.form", this.form)
            addCourse(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    buildParam(params) {
      let value = this.$refs.picture.getFieldValue()
      if (value.length !== 0) {
        params.cover = value[0].id;
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除课程编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delCourse(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有课程数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportCourse(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    },
    handleSetTask(row){

    }
  }
};
</script>
