<template>
  <el-container>
    <el-header style="height:60px;padding: 0px" class="header-bg">
      <div>
        <el-row type="flex" justify="center">
          <el-col :span="20">
            <el-row>
              <el-col :span="6" class="col-logo">
                <a href="/web/index">
                  <div style="display: flex; flex-direction: row; align-items: center;">
                    <div>
                      <img src="../../../assets/images/weblogo.png"
                           alt="科大虚拟实验系统" style="height: 40px;">
                    </div>
                  </div>
                </a>
              </el-col>
              <el-col :span="18">
                <el-row>
                  <el-col :span="18" style="line-height: 60px">
                    <el-button @click="activeHandel(index)" class="nav" :class="{'active':active===item.index}"
                               v-for="(item,index) in Navbar">
                      <router-link :to="item.url"> {{ item.name }}</router-link>
                    </el-button>
                  </el-col>
                  <el-col :span="6" class="right-user">
                    <el-image class="top-avatar"
                              :src="user.avatar"
                    ></el-image>
                    <a>{{user.name}}</a>
                    <a @click="logout">退出</a>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </div>
    </el-header>
    <el-main style="padding-top: 20px;">
      <el-row type="flex" justify="center" style="margin-top: 25px">
        <el-col :span="20">
          <app-main/>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>

import AppMain from "@/views/web/webLayout/components/AppMain";

export default {
  name: "WebLayout",
  components: {AppMain},
  data() {
    return {
      active: 0,
      Navbar: [
        {
          index: 0,
          url: '/web/index',
          name: '在线作业'
        },
        {
          index: 1,
          url: '/web/vm/vmIndex',
          name: '虚拟实验'
        },
        // {
        //   index: 2,
        //   url: '',
        //   name: '用户中心'
        // },
      ]
    }
  },
  computed: {
    user() {
      console.log(this.$store.state.user)
      return this.$store.state.user
    },
  },
  methods: {
    activeHandel(index) {
      this.active = index
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/web/index';
        })
      }).catch(() => {
      });
    }
  }
}
</script>

<style scoped>
.main-bg {
  padding: 20px;
  background: #fff;
}

.app-main {
  min-height: calc(100vh - 145px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.header-bg {
  /*height: 100px;*/
  background: #0f5035;
}

.col-logo {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: 60px;
}

.top {
  height: 120px;
  width: 1200px;
  margin: 0 auto;
  background: url(https://www.nudt.edu.cn/images/header_bg.png) no-repeat right center;
}

.nav {
  color: #fff;
  border: none;
  background: transparent;
  font-weight: 500;
  font-size: 14px;
  padding: 5px 10px 5px 10px;
  margin-right: 20px;
}

.active {
  color: #000000;
  background: #FFD550;
}

.right-user {
  display: flex;
  justify-content: flex-end;
  flex-direction: row;
  line-height: 60px !important;
  align-items: center;
}

.top-avatar {
  text-align: right;
  display: flex;
  align-items: center;
  margin-right: 5px !important;
}

>>> .top-avatar img {
  width: 30px;
  height: 30px;
  border-radius: 15px;
}

.right-user a {
  color: #efefef;
  font-size: 14px;
  font-weight: 500;
}

right-user :nth-child(1), .right-user :nth-child(2) {
  margin-right: 10px;
}
</style>
