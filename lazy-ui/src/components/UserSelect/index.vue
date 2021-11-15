<template>
  <el-dialog
    title="用户选择器"
    :visible.sync="dialogVisible"
    width="30%">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="roleIds">
        <el-select v-model="queryParams.roleIds" multiple placeholder="请选择" @change="getUserList">
          <el-option
            v-for="item in roleOptions"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
            :disabled="item.status == 1"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-table :data="userList"  highlight-current-row v-loading="loading" @current-change="handleCurrentChange">
      <el-table-column property="userId" label="编码" width="150"></el-table-column>
      <el-table-column property="nickName" label="姓名" width="200"></el-table-column>
      <el-table-column property="phonenumber" label="电话"></el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getUserList"
    />
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="selectHandle">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
import {listUser, userByRoleList} from "@/api/system/user";
import {listRole} from "@/api/system/role";

export default {
  name: "UserSelect",
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleIds: []
      },
      //用户数据
      userList: [],
      loading: false,
      // 显示搜索条件
      showSearch: true,
      total: 0,
      dialogVisible: false,
      roleOptions: [],
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      currentRow:null
    }
  },
  created() {
    this.getUserList();
    listRole().then(response => {
      this.roleOptions = response.rows
      this.roleOptions.forEach(row => {
        if (row.roleKey === 'student') {
          // this.queryParams.roleIds.push(row.id);
        }
      })
    })
  },
  methods: {
    /** 查询用户列表 */
    getUserList() {
      this.loading = true;
      userByRoleList(this.queryParams).then(response => {
          console.log(response)
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    handleCurrentChange(val) {
      this.currentRow = val
    },
    selectHandle(){
      this.$emit("selectHandle",this.currentRow)
    }
  },
  watch: {
    userDialogVisible() {

    }
  }
}
</script>

<style scoped>

</style>
