<template>

  <!--    <h4>当前题库：{{}}</h4>-->
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="content">
        <el-input
          v-model="queryParams.contentText"
          placeholder="试题内容"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          @input="handleQuery"
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
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          plain-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          v-hasPermi="['vm:testQuestion:edit']"-->
      <!--        >修改-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          v-hasPermi="['vm:testQuestion:remove']"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
    </el-row>
    <el-table border :data="answerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="题型" align="center" prop="type" :formatter="typeFormat"></el-table-column>
      <el-table-column label="难度" align="center" prop="level" :formatter="levelFormat"></el-table-column>
      <el-table-column label="章节" align="center" prop="chapterId" :formatter="chapterFormat"></el-table-column>
      <el-table-column label="试题内容" align="center" prop="content" show-overflow-tooltip>
        <template slot-scope="scope">
          <router-link :to="{ name: 'quUpdate', params:{id: scope.row.id,quId:taskId,chapters:chapters}}"
                       style="color: #00afff">
            {{ scope.row.contentText }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="gmtCreate"></el-table-column>
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
import {delAnswer, listAnswer} from "@/api/vm/answer";
import {getTask} from "@/api/vm/task";
import {delExam} from "@/api/vm/exam";

export default {
  name: "TestQuestions",
  data() {
    return {
      answerList: [],
      types: [],
      levels: [],
      chapters: [],
      taskObj: {},
      taskId: '',
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      total: 0,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        taskId: null,
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
    const id = this.$route.params.id
    this.queryParams.taskId = id
    this.taskId = id
    getTask(id).then(response => {
      this.chapters = response.data.chapters
    })
    this.getList();
    this.queryDicts();
  },
  methods: {
    /** 查询作业题目列表 */
    getList() {
      this.loading = true;
      listAnswer(this.queryParams).then(response => {
        this.answerList = response.rows;
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
        type: null,
        content: null,
        level: null,
        answer: null,
        options: null,
        createTime: null,
        chapterId: null,
        updateTime: null,
        analysis: null,
        taskId: null
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
    /** 新增按钮操作 */
    handleAdd() {
      // this.$router.push('/vm/question/testQuestionForm/')
      console.log(this.taskId)
      this.$router.push({path: '/vm/question/testQuestionForm/', query: {quId: this.taskId, chapters: this.chapters}})
    },

    //删除题目
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除作业编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delAnswer(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    typeFormat(row, column) {
      return this.selectDictLabel(this.types, row.type);
    },
    levelFormat(row, column) {
      return this.selectDictLabel(this.levels, row.level);
    },
    chapterFormat(row, column) {
      let title;
      this.chapters.forEach((r, k) => {
        if (r.id === row.chapterId) {
          title = r.title
        }
      })
      return title;
    },
    queryDicts() {
      this.getDicts("vm_qu_type").then(response => {
        this.types = response.data;
      });
      this.getDicts("vm_qu_level").then(response => {
        this.levels = response.data;
      });
    },
  }
}
</script>

<style scoped>

</style>
