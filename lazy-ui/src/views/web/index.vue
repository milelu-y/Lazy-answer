<template>
  <el-container>
    <el-header>
      <div>
        <el-row type="flex" justify="center" class="header-bg">
          <el-col :span="20">
            <el-row>
              <el-col :span="6" class="col-logo top">
                <a>
                  <div style="display: flex; flex-direction: row; align-items: center;">
                    <div>
                      <img src="../../assets/images/weblogo.png"
                           alt="虚拟实验系统" style="height: 40px;">
                    </div>
                  </div>
                </a>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </div>
    </el-header>
    <el-main style="padding-top: 20px">
      <el-row type="flex" justify="center" style="margin-top: 25px">
        <el-col :span="20">
          <section class="app-main main-bg">
            <div>
              <el-card style="background: rgb(246, 248, 253); border-radius: 0px; padding: 10px 0px;">
                <el-form label-width="68px">
                  <el-input></el-input>
                </el-form>
              </el-card>
              <el-card style="margin-top: -1px; font-size: 14px; border-radius: 0px;">
                <el-row type="flex" style="line-height: 28px">
                  <el-col :span="20">
                    <div style="font-weight: 700; font-size: 16px;">测试考试</div>
                    <div style="color: rgb(102, 102, 102); font-size: 12px;">
                      <el-row>
                        <el-col :span="4">时长：30分钟</el-col>
                        <el-col :span="4">总分：10分</el-col>
                        <el-col :span="4">及格分：2分</el-col>
                        <el-col :span="4">考试时间： 不限时</el-col>
                      </el-row>
                    </div>
                  </el-col>
                  <el-col :span="4" style="display: flex; justify-content: flex-end; align-items: center;">
                    <div>
                      <el-tag type="success">进行中</el-tag>
                    </div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </section>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "index"
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
  height: 100px;
  background: #0f5035;
}

.col-logo {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  /*height: 60px;*/
  line-height: 11;
}

.top {
  height: 120px;
  width: 1200px;
  margin: 0 auto;
  background: url(https://www.nudt.edu.cn/images/header_bg.png) no-repeat right center;
}
</style>
