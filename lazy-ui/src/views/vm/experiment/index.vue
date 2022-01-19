<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['vm:exam:add']"
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
          v-hasPermi="['vm:exam:edit']"
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
          v-hasPermi="['vm:exam:remove']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table border v-loading="loading" :data="exList">
      <el-table-column label="实验名称" align="center" prop="title">
      </el-table-column>
      <el-table-column label="实验介绍" show-overflow-tooltip align="center" prop="intro"/>
      <el-table-column label="实验地址" align="center" prop="url"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-drawer
      title="我是标题"
      :visible.sync="drawer"
      size="50%"
      :with-header="false">
      <div class="demo-drawer__content" style="padding: 10px">
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item label="实验标题" label-width="80px" prop="title">
            <el-input v-model="form.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="实验介绍" label-width="80px" prop="intro">
            <el-input type="textarea" v-model="form.intro" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="实验地址" label-width="80px">
            <el-input v-model="form.url" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="实验大纲" label-width="80px">
            <file-upload-new ref="upload" @uploadCallback="uploadCallback"></file-upload-new>
          </el-form-item>
          <el-form-item label="实验流程" label-width="80px" prop="process">
            <timymce-editor ref="timymce"></timymce-editor>
          </el-form-item>
        </el-form>
        <div style="float: right;margin-top: 10px">
          <el-button @click="cancelForm">取 消</el-button>
          <el-button type="primary" @click="saveHandler" :loading="loading">{{
              loading ? '提交中 ...' : '确 定'
            }}
          </el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>

import timymceEditor from '@/components/TimymceEditor'
import FileUploadNew from "@/components/FileUploadNew";
import {addExperiment, getExperiment, listExperiment, updateExperiment} from "@/api/vm/ex";
import {getResource} from "@/api/system/resource";

export default {
  name: "index",
  components: {FileUploadNew, timymceEditor},
  data() {
    return {
      // 遮罩层
      loading: false,
      total: 0,
      exList: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //抽屉
      drawer: false,
      //表单数据
      form: {},
      rules: {
        title: [
          {required: true, message: '请输入实验标题', trigger: 'blur'},
        ],
        intro: [
          {required: true, message: '请输入实验简介', trigger: 'blur'},
        ],
        // process: [
        //   {required: true, message: '请输入实验流程', trigger: 'blur'},
        // ],
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true
      listExperiment(this.queryParams).then(response => {
        this.exList = response.rows
        this.total = response.total;
        this.loading = false;
      })
    },
    handleAdd() {
      this.drawer = true;
    },
    handleUpdate(row) {
      let id = row.id
      let that = this
      getExperiment(id).then(response => {
        console.log(response)
        if (response.code === 200) {
          that.drawer = true
          that.form = response.data
          setTimeout(function () {
            // that.getResource(response.data.outline)
            that.$refs.timymce.setContent(response.data.process)
          }, 100)

        }
      })
    },
    handleDelete() {

    },
    saveHandler() {
      this.form.process = this.$refs.timymce.content
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateExperiment(this.form).then(response=>{
              if (response.code===200){
                this.drawer = false
                this.getList()
              }
            })
          } else {
            addExperiment(this.form).then(response => {
              if (response.code === 200) {
                this.drawer = false
                this.getList()
              }
            })
          }
        }
      });
    },
    cancelForm() {
      this.form = {}
      this.drawer = false
    },
    uploadCallback(response) {
      this.form.outline = response.response.data.id
    },
    getResource(id) {
      getResource(id).then(response => {
        if (response.code===200){
          this.$refs.upload.fileList[0] = {name:response.data.fileName,url:response.data.url}
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
