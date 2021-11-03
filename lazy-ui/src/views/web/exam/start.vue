<template>
  <div class="app-container">
    <el-row style="margin-left: -10px; margin-right: -10px; height: 98vh;">
      <el-col :span="4" style="padding-left: 10px; padding-right: 10px;">
        <el-card shadow="always" style="height: 94vh; overflow-y: auto;">
          <div>
            <div class="title">
              单选题<span data-v-9b6d3d2c="" style="float: right; font-size: 12px;">总1题/共100分</span>
            </div>
            <div class="card-box">
              <div class="item">
                <div class="num current">1</div>
                <div class="flag"></div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="17" style="padding-left: 10px; padding-right: 10px;">
        <el-card shadow="always" style="height: 94vh; overflow-y: auto;">
          <div class="ques">
            <el-row>
              <el-col :span="24"
                      style="background: rgb(238, 238, 238); line-height: 25px; padding: 10px; margin-bottom: 20px;">
                单选题 （本题分值：30分）
              </el-col>
              <el-col :span="23">
                <div style="display: flex">
                  <div class="no">1.</div>
                  <div style="flex-grow: 1;">
                    <div class="content"><p>苹果中含有增强记忆力的微量元素是</p></div>
                  </div>
                </div>
              </el-col>
              <el-col :span="1"></el-col>
              <el-col :span="24" style="padding-top: 20px;">
                <div class="option">
                  <label style="cursor: pointer;">
                    <span>A</span><span>铁</span>
                  </label>
                  <label style="cursor: pointer;">
                    <span>B</span><span>碘</span>
                  </label>
                  <label style="cursor: pointer;">
                    <span>C</span><span>锌</span>
                  </label>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
      <el-col :span="3" style="padding-left: 10px; padding-right: 10px;">
        <el-card shadow="always" style="height: 94vh;">
          <el-row style="margin-left: -5px; margin-right: -5px; text-align: center; line-height: 30px;">
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">剩余时间</el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <span style="color: rgb(255, 0, 0); font-weight: 700;">78分钟31秒</span>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-divider/>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-button type="warning">提交试卷</el-button>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-divider/>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px;">
              <el-button type="warning" disabled icon="el-icon-back">上一题</el-button>
            </el-col>
            <el-col :span="24" style="padding-left: 5px; padding-right: 5px; padding-top: 20px;">
              <el-button type="primary" icon="el-icon-right el-icon--right">下一题</el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "start",
  created() {
    console.log(this.$route.params.id)
  }
}
</script>

<style scoped>
.title {
  font-size: 14px;
  font-weight: 700;
  padding: 10px 0px 10px 0px;
}

.card-box {
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  margin-left: -5px;
}

.card-box .item {
  width: 30px;
  height: 40px;
  background: #eee;
  border-radius: 2px;
  font-size: 6px;
  align-items: center;
  cursor: pointer;
  margin: 5px 5px 10px 5px;
}

.card-box .item .current {
  border-radius: 2px;
  background: #FFBA00;
  color: #fff;
}

.card-box .item .num {
  text-align: center;
  color: #555;
  font-weight: 700;
  padding: 3px;
}

.card-box .item .flag {
  text-align: center;
  border-top: #fff 1px solid;
  padding: 3px;
}

.no {
  padding-right: 10px;
  font-size: 16px;
  color: #0a84ff;
  font-style: italic;
  font-weight: 700;
  -webkit-margin-after: 0.5em;
  margin-block-end: 0.5em;
}

.content p {
  margin-block-start: 0em;
  margin-block-end: 0.5em;
}

.ques .option label {
  padding: 10px;
  border: 1px solid #f6f6f8;
  color: #4f4e58;
  font-size: 14px;
  margin-bottom: 10px;
  text-align: left;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  align-items: center;
  line-height: 20px;
  border-radius: 5px;
}
</style>
