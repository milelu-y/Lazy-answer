<template>
  <div class="app-container">
    <el-steps :active="1" simple>
      <el-step title="创建" icon="el-icon-edit"></el-step>
      <el-step title="组装" icon="el-icon-upload"></el-step>
      <el-step title="发布" icon="el-icon-picture"></el-step>
    </el-steps>
    <div>
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
                      @change="handleQuery"
                    />
                  </el-form-item>
                </el-form>
              </div>
              <div>
                <el-table border :data="paperList">
                  <el-table-column label="试卷名称" align="center" prop="title" width="100"></el-table-column>
                  <el-table-column label="组卷方式" align="center" prop="type" width="150"></el-table-column>
                  <el-table-column label="试卷分类" align="center" prop="category"></el-table-column>
                  <el-table-column label="总分" align="center" prop="totalScore" show-overflow-tooltip></el-table-column>
                  <el-table-column label="创建人" align="center" prop="userId" show-overflow-tooltip></el-table-column>
                  <el-table-column label="创建时间" align="center" prop="gmtCreate"></el-table-column>
                  <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                      <el-button type="primary">选定</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "form",
  data() {
    return {
      queryParams: {
        title: '',
      },
      paperList: []
    }
  },
  methods: {
    handleQuery() {

    },
    getPaperList() {

    }
  }
}
</script>

<style scoped>

</style>
