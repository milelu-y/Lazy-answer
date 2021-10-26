<template>
  <div class="app-container">
    <el-col :span="14">
      <h3>基本信息</h3>
      <el-form ref="postForm" :model="postForm" label-position="top" label-width="100%">
        <el-card>
          <el-form-item label="题库名称" prop="title">
            <el-input size="medium" v-model="postForm.title"/>
          </el-form-item>

          <el-form-item label="题库分类" prop="title">
            <el-select v-model="postForm.type" placeholder="请选择" style="width: 100%">
              <el-option
                v-for="item in courseOptions"
                :key="item.id"
                :label="item.title"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="题库备注" prop="title">
            <el-input size="medium" type="textarea" v-model="postForm.remark"/>
          </el-form-item>
        </el-card>
      </el-form>
    </el-col>
    <el-col :span="10">
      <el-col :span="24">
        <h3 style="display: inline-block">章节管理</h3>
        <el-button type="primary" icon="el-icon-plus" size="small" style="float: right;margin-block: 1em"
                   @click="addItem">添加
        </el-button>
      </el-col>
      <el-card>
        <el-table
          row-key="id"
          :data="tableData"
          ref="dragTable"
          style="width: 100%">
          <el-table-column
            label="章节名称"
            width="300">
            <template slot-scope="scope">
              <el-input size="medium" v-model="scope.row.title"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="试题数量"
            width="100">
            <template slot-scope="scope">
              <el-input size="medium" v-model="scope.row.quCount"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button icon="el-icon-d-caret" circle @click="setSort(scope.$index, scope.row)"></el-button>
              <el-button type="danger" icon="el-icon-delete" circle
                         v-hasPermi="['vm:question:remove']"
                         @click="handleDelete(scope.$index, scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
    <el-col :span="24" style="padding-top: 10px">
      <el-button type="primary" @click="handleSave" v-hasPermi="['vm:question:add']">保存</el-button>
      <el-button type="info" @click="$router.go(-1)">返回</el-button>
    </el-col>
  </div>
</template>

<script>
import Sortable from 'sortablejs'
import {listCourse} from "@/api/vm/course";
import {addTask, getTask, updateTask} from "@/api/vm/task";

export default {
  name: "form",
  data() {
    return {
      postForm: {},
      loading: false,
      tableData: [],
      courseOptions: [],
      value: '',
    }
  },
  computed: {
    sort() {
      return this.tableData.length
    }
  },
  created() {
    const id = this.$route.params.id
    listCourse().then(response => {
      this.courseOptions = response.rows;
    })
    getTask(id).then(response => {
      console.log(response)
      this.postForm = response.data
      this.tableData = response.data.chapters
    })
  },
  mounted() {
    this.setSort()
  },
  methods: {
    addItem() {
      let params = {
        title: '新章节',
        quCount: 0,
        sort: this.sort,
      }
      this.tableData.push(params)
      this.sort++;
    },
    //删除章节
    handleDelete(index, row) {
      let temp = [];
      this.tableData.forEach((row, key) => {
        if (key !== index) {
          temp.push(row);
        }
      })
      this.tableData = temp
    },
    handleMove(index, row) {

    },
    handleSave() {
      this.postForm.chapters = this.tableData;
      //保存题库
      if (this.postForm.id) {
        updateTask(this.postForm).then(response => {
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '添加题库成功',
              type: 'success'
            });
            this.$router.go(-1)
          } else {
            this.$notify.error({
              title: '错误',
              message: '添加题库失败',
            });
          }
        })
      } else {
        addTask(this.postForm).then(response => {
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '添加题库成功',
              type: 'success'
            });
            this.$router.go(-1)
          } else {
            this.$notify.error({
              title: '错误',
              message: '添加题库失败',
            });
          }
        })
      }

    },
    setSort() {
      const tbody = document.querySelector('.el-table__body-wrapper tbody')
      let arr = this.tableData;
      Sortable.create(tbody, {
        onEnd({newIndex, oldIndex}) {
          const currRow = arr.splice(oldIndex, 1)[0]
          currRow.sort = newIndex;
          arr.splice(newIndex, 0, currRow)
          this.tableData = arr;
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
