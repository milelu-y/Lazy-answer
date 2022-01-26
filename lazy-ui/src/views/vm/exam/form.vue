<template>
  <div class="app-container">
    <el-steps :active="step" simple>
      <el-step title="创建" icon="el-icon-edit"></el-step>
      <el-step title="组装" icon="el-icon-upload"></el-step>
      <el-step title="发布" icon="el-icon-picture"></el-step>
    </el-steps>
    <div v-if="step===1">
      <h3>选择/创建试卷</h3>
      <el-card>
        <div>
          <div>
            <div class="app-container">
              <div>
                <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
                  <el-form-item prop="title">
                    <el-input
                      v-model="queryParams.title"
                      placeholder="试卷名称"
                      clearable
                      size="small"
                      @keyup.enter.native="handleQuery"
                      @input="handleQuery"
                    />
                  </el-form-item>
                </el-form>
              </div>
              <div>
                <el-table border :data="paperList" v-loading="loading">
                  <el-table-column label="试卷名称" align="center" prop="title" width="100"></el-table-column>
                  <el-table-column label="组卷方式" align="center" prop="type" width="150"
                                   :formatter="packageFormatter"></el-table-column>
                  <el-table-column label="试卷分类" align="center" prop="category"
                                   :formatter="courseFormatter"></el-table-column>
                  <el-table-column label="总分" align="center" prop="totalScore" show-overflow-tooltip></el-table-column>
                  <el-table-column label="创建人" align="center" prop="userId" show-overflow-tooltip></el-table-column>
                  <el-table-column label="创建时间" align="center" prop="gmtCreate"></el-table-column>
                  <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                      <el-button type="primary" @click="handleBack(scope.row.id)">选定</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <pagination
                  v-show="total>0"
                  :total="total"
                  :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize"
                  @pagination="getPaperList"
                />
              </div>
            </div>
          </div>
        </div>
      </el-card>
    </div>
    <div v-if="step===3">
      <el-form ref="postForm" :model="postForm" :rules="rules" label-position="left" label-width="100px">
        <el-row>
          <el-col :span="18">
            <h3>作业设置</h3>
            <el-card shadow="always" style="padding-left: 10px; padding-right: 10px;">
              <el-col :span="12">
                <el-form-item label="作业内容" prop="title">
                  <el-input v-model="postForm.title" size="medium" style="width: 200px;"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="及格线" prop="qualifyScore">
                  <el-input-number size="medium" v-model="postForm.qualifyScore" style="width: 200px;"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="时长" prop="totalTime">
                  <el-input-number placeholder="作业时长" size="medium" v-model="postForm.totalTime"
                                   style="width: 200px;"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="限作次数" prop="limitCount">
                  <el-input-number placeholder="限作次数" size="medium" v-model="postForm.limitCount"
                                   style="width: 200px;"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="限制答题时间" prop="limitTime">
                  <el-switch
                    v-model="postForm.limitTime"
                    active-text="是"
                    inactive-text="否">
                  </el-switch>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="结果显示" prop="resultType">
                  <el-radio v-model="postForm.resultType" :label="0">显示分数+答后文字</el-radio>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="注意事项" prop="content">
                  <el-input type="textarea" v-model="postForm.content" maxlength="1500" show-word-limit></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="答后文字" prop="thanks">
                  <el-input type="textarea" v-model="postForm.thanks" maxlength="1500" show-word-limit></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <div style="background: rgb(251, 251, 251); padding: 10px; line-height: 36px; font-size: 14px;">
                  <div style="font-weight: 700;">权限设置</div>
                  <el-radio-group v-model="postForm.openType">
                    <el-radio :label="0">完全开发</el-radio>
                    <el-radio :label="1">需要密码</el-radio>
                    <el-radio :label="2">指定班级</el-radio>
                  </el-radio-group>
                  <div v-if="postForm.openType===0">
                    <el-tag>完全公开，任何人都可以参与</el-tag>
                  </div>
                  <div v-if="postForm.openType===1">
                    <el-input v-model="postForm.password" placeholder="输入权限密码" style="width: 200px"></el-input>
                  </div>
                  <div v-show="postForm.openType===2">
                    <p>选择班级:</p>
                    <el-input
                      placeholder="输入关键字进行过滤"
                      v-model="filterText">
                    </el-input>
                    <el-tree
                      class="filter-tree"
                      ref="dept"
                      :data="deptList"
                      node-key="deptId"
                      default-expand-all
                      :check-strictly="true" :props="defaultProps"
                      show-checkbox :filter-node-method="filterNode" @check-change="deptCheckChange"></el-tree>
                  </div>
                </div>
              </el-col>
            </el-card>
          </el-col>
          <el-col :span="6" style="padding-left: 10px; padding-right: 10px;">
            <h3>试卷信息</h3>
            <el-card style="font-size: 14px; line-height: 28px;">
              <div> 试卷标题：{{ paperData.title }}</div>
              <div> 试题数量： {{ paperData.quCount }}</div>
              <div> 试题总分： {{ paperData.totalScore }}</div>
              <div> 创建时间：{{ paperData.gmtCreate }}</div>
            </el-card>
          </el-col>
        </el-row>
        <el-button type="primary" size="medium" style="margin-top: 20px;" @click="nextStep">保存</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getSimpleTestPaper, getTestPaper, listTestPaper} from "@/api/vm/testPaper";
import {listCourse} from "@/api/vm/course";
import {addExam, getExam, updateExam} from "@/api/vm/exam";
import {listDept} from "@/api/system/dept";

export default {
  name: "form",
  data() {
    return {
      step: 1,
      queryParams: {
        title: '',
      },
      paperData: {},
      paperList: [],
      courseList: [],
      packages: [],
      deptList: [],
      // 总条数
      total: 0,
      // 遮罩层
      loading: true,
      postForm: {
        // 试卷ID
        paperId: null,
        // 作业部门列表
        deptIds: [],
        // 限制作业次数
        chance: 1,
        //作业时长
        totalTime: 30,
        //限考次数
        limitCount: 0,
        // 开放权限
        openType: 0,
        // 积分数量
        points: 0,
        // 是否截屏
        isCapture: true,
        //返回结果类型
        resultType: 0,
        thanks: '感谢您的作答！',
        personList: []
      },
      rules: {
        title: [{
          required: true,
          message: '作业名称不能为空！'
        }],
        open: [{
          required: true,
          message: '作业权限不能为空！'
        }],
        qualifyScore: [{
          required: true,
          message: '及格分不能为空！'
        }],
        totalTime: [{
          required: true,
          message: '作业时间不能为空！'
        }],
        password: [{
          required: true,
          message: '作业口令不能为空！'
        }],
        limitCount: [{
          required: true,
          message: '作业次数不能为空，0为不限制！'
        }],
        examType: [{
          required: true,
          message: '作业类型必须选择'
        }]
      },
      defaultProps: {
        children: 'children',
        label: 'deptName'
      },
      filterText: ''
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {


    this.getPaperList();
    this.getCourseList();
    this.getList();
    this.getDicts("vm_exam_package").then(response => {
      this.packages = response.data;
    });

    var id = this.$route.params.id;
    if (id) {
      //查询明细
      this.fetchData(id);
    }
  },
  methods: {
    fetchData(id) {
      getExam(id).then(response => {
        this.postForm = response.data;
        this.handleBack(this.postForm.paperId)
      })
    },
    deptCheckChange(data) {
    },
    // 所有部门节点数据
    getDeptAllCheckedKeys() {
      // 目前被选中的部门节点
      let checkedKeys = this.$refs.dept.getCheckedKeys();
      // 半选中的部门节点
      let halfCheckedKeys = this.$refs.dept.getHalfCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.deptName.indexOf(value) !== -1;
    },
    getList() {
      listDept().then(response => {
        this.deptList = this.handleTree(response.data, "deptId");
        console.log(this.deptList)
      });
    },
    handleQuery() {
      this.getPaperList();
    },
    getPaperList() {
      this.loading = true;
      listTestPaper(this.queryParams).then(response => {
        this.paperList = response.rows
        this.total = response.total;
        this.loading = false;
      })
    },
    /** 查询课程列表 */
    getCourseList() {
      listCourse().then(response => {
        this.courseList = response.rows;
      });
    },
    packageFormatter(row, column) {
      return this.selectDictLabel(this.packages, row.type);
    },
    courseFormatter(row, column) {
      for (const course of this.courseList) {
        if (course.id === row.category) {
          return course.title;
        }
      }
    },
    /**
     * 选定暗流操作
     */
    handleBack(id) {
      var _this4 = this;
      this.step = 3;
      this.postForm.paperId = id;
      getSimpleTestPaper(id).then(response => {
        _this4.paperData = response.data;
      })
      this.postForm.deptIds.forEach((v) => {
        this.$nextTick(() => {
          this.$refs.dept.setChecked(v, true, false);
        })
      })
    },
    nextStep() {
      var _this = this;
      this.postForm.totalScore = this.paperData.totalScore
      console.log(this.postForm)
      this.$refs.postForm.validate(function (valid) {
        if (!valid) {
          return;
        }

        _this.$confirm('确实要提交保存吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          _this.submitForm();
        });
      });
    },
    submitForm() {
      var _this3 = this;
      this.postForm.deptIds = this.getDeptAllCheckedKeys();
      if (this.postForm.id != null && this.postForm.id != '') {
        updateExam(this.postForm).then(response => {
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '保存成功！',
              type: 'success'
            });
            this.$router.go(-1)
          }
        })
      } else {
        addExam(this.postForm).then(response => {
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '保存成功！',
              type: 'success'
            });
            this.$router.go(-1)
          }
        })
      }

    },

  }
}
</script>
