<template>
  <el-dialog
    @open="getList"
    title="选择试题"
    :visible.sync="dialogShow"
    width="60%">
    <div>
      <div class="filter-container">
        <el-row>
          <el-form ref="postForm" :model="postForm">
            <el-col :span="24">
              <el-form-item prop="title" class="filter-item">
                <!--                <el-input size="medium" placeholder="选择搜索题库" style="width: 200px" @change="getList" v-model="postForm.title"/>-->
                <el-select v-model="queryParams.taskId" placeholder="选择搜索题库" @change="getList ">
                  <el-option
                    v-for="item in taskList"
                    :key="item.id"
                    :label="item.title"
                    :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="title" class="filter-item">
                <el-input size="medium" placeholder="试题内容" style="width: 200px" @input="getList"
                          v-model="queryParams.content"/>
              </el-form-item>
              <el-button type="primary" @click="handleConfirm()" style="float: right">确认{{ ids.length }}项</el-button>
            </el-col>
          </el-form>
        </el-row>
      </div>
      <el-table
        v-loading="loading"
        border
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column
          align="center"
          type="selection"
          width="50">
        </el-table-column>
        <el-table-column label="试题类型" align="center" prop="type" width="100" :formatter="typeFormat"></el-table-column>
        <el-table-column label="试题内容" prop="contentText" show-overflow-tooltip></el-table-column>
        <el-table-column label="创建时间" align="center" prop="gmtCreate" width="200"></el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
  </el-dialog>
</template>

<script>
import {listAnswer, pagingWithAnswer} from "@/api/vm/answer";
import _ from 'lodash'
import {listTask} from "@/api/vm/task";

export default {
  name: "QuSelectDialog",
  props: {
    quType: String,
    excludes: Array,
    dialogShow: {
      type: Boolean,
      default: false
    }
  },
  watch: {},
  data() {
    return {
      postForm: {},
      tableData: [],
      types: [],
      // 总条数
      total: 0,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      loading: false,
      // 选定的数据列表
      selectedList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        content: '',
        type: '',
        taskId: '',
      },
      taskList: [],
    }
  },
  created() {
    this.getList()
    this.getDicts("vm_qu_type").then(response => {
      this.types = response.data;
    });
    //查询题库
    this.getListQuestion()
  },
  methods: {
    getListQuestion() {
      listTask().then(response => {
        this.taskList = response.rows;
        console.log(this.taskList)
        // this.loading = false;
      });
    },
    getList() {
      this.loading = true;
      this.queryParams.type = this.quType;
      this.queryParams.excludes = this.excludes;
      console.log(this.queryParams)
      pagingWithAnswer(this.queryParams).then(response => {
        this.tableData = response.rows;
        //_.pullAllBy(this.tableData, this.excludes, 'id')
        this.total = response.total;
        this.loading = false;
      });
    },
    typeFormat(row, column) {
      return this.selectDictLabel(this.types, row.type);
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
      this.selectedList = selection;
    },
    handleConfirm() {
      this.$emit('update:dialogShow', false);
      this.$emit('select', this.selectedList);
    }
  }
}
</script>

<style scoped>

</style>
