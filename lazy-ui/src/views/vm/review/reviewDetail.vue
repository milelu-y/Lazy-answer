<template>
  <div class="app-container">
    <div class="all-box" v-loading.fullscreen.lock="loading">
      <div class="all-box-left">
        <el-card shadow="always" class="left-box" :class="{'left-box-normal':!scrolled,'left-box-scrolled':scrolled}"
                 style="width:250px">
          <div class="group-card" v-for="(row,index) in postForm.groupList">
            <div>
              <div style="line-height: 40px;">
                <button type="button" class="el-button el-button--text el-button--medium"
                        style="font-size: 14px; font-weight: 700;"><span>{{ row.title }}</span></button>
                <div class="el-divider el-divider--horizontal"></div>
              </div>
              <div class="group-card-body">
                <div> 共 <i class="num">{{ row.quCount }}</i> 题，共 <i class="num">{{ row.totalScore }}</i> 分
                </div>
              </div>
              <div class="card-box">
                <div class="item" v-for="(item,i) in row.quList" @click="goAnchor(item.id)">
                  <div class="num" :class="{ 'right': item.isRight,'error':!item.isRight,'wait': postForm.status===1&&item.type===3}">
                    {{ i + 1 }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
      <div class="all-box-center">
        <el-card class="el-card content-card is-always-shadow" v-for="(row,index) in postForm.groupList" :id="row.id">
          <div v-for="(qu,i) in row.quList">
            <div style="display: flex" :id="qu.id" v-if="!onlyError || (onlyError && !qu.isRight)">
              <qu-item-show :value="qu" :index="i"></qu-item-show>
              <div style="flex: 0 0 200px">
                <div class="check-box">
                  <div class="item">
                    判断：
                    <i class="el-icon-success check-icon" :class="{'check-icon-right':qu.isRight}" @click="checkAnswer(qu,true)"></i>
                    <i class="el-icon-error check-icon" :class="{'check-icon-error':!qu.isRight}" @click="checkAnswer(qu,false)"></i>
                  </div>
                  <div class="item">
                    得分：
                    <el-input-number size="mini" v-model="qu.actualScore" :min="0" :max="qu.score" style="width:100px"></el-input-number>
                  </div>
                  <div class="item">总分：{{ qu.score }}</div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
        <el-card class="el-card is-always-shadow">
          <el-input
            type="textarea"
            placeholder="评语"
            v-model="postForm.remark"
            maxlength="300"
            show-word-limit
          >
          </el-input>
        </el-card>
      </div>
      <div class="all-box-right">
        <el-card class="left-box" :class="{'left-box-normal':!scrolled,'left-box-scrolled':scrolled}" shadow="always"
                 style="width: 200px;">
          <div>学员姓名</div>
          <div style="color: rgb(10, 132, 255);">{{ postForm.nickName }}</div>
          <el-divider></el-divider>
          <div>成绩</div>
          <div style="color: rgb(255, 75, 80);">{{ postForm.userScore }}</div>
          <el-divider></el-divider>
          <div>仅看错题</div>
          <div>
            <el-switch
              active-color="#13ce66"
              v-model="onlyError"
              inactive-color="#ff4949">
            </el-switch>
          </div>
          <el-divider></el-divider>
          <div>作业状态</div>
          <span style="color: rgb(10,236,14);" v-if="postForm.userScore>=postForm.qualifyScore">通过</span>
          <span style="color: rgb(255, 75, 80);" v-else>未通过</span>
          <el-divider></el-divider>
          <el-button type="primary" @click="submitForm">提交阅题</el-button>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import QuItemShow from "@/views/vm/review/components/QuItemShow";
import {paperResult, reviewPaper} from "@/api/vm/testPaper";

export default {
  name: "reviewDetail",
  components: {QuItemShow},
  data() {
    return {
      paperId: null,
      loading: false,
      postForm: {},
      scrolled: false,
      onlyError:false,
    }
  },
  watch:{
    'postForm.groupList': {
      handler: function handler(val) {
        var userScore=0;
        for (var i = 0; i < val.length; i++) {
          var item = val[i];
          var groupScore = 0;
          var quList = item.quList;
          for (var j = 0; j < quList.length; j++) {
            groupScore = quList[j].actualScore+groupScore;
          }
          item.userScore = item.userScore+groupScore;
          userScore = groupScore+userScore;
        }
        this.postForm.userScore=userScore
      },
      deep: true
    }
  },
  mounted() {
    var _this = this;

    // 监听滚动条
    document.addEventListener('scroll', function () {
      var scrollTop = 0;
      if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
      } else if (document.body) {
        scrollTop = document.body.scrollTop;
      }

      if (scrollTop > 85) {
        _this.scrolled = true;
      } else {
        _this.scrolled = false;
      }
    });
  },
  created() {
    var id = this.$route.params.id //paperId
    console.log(id)
    if (id !== undefined) {
      this.paperId = id;
      this.fetchData(id);
    }
  },
  methods: {
    fetchData(id) {
      this.loading = true;
      paperResult(id).then(response => {
        this.postForm = response.data
        this.loading=false
      })
    },
    goAnchor(selector) {
      var anchor = document.getElementById(selector);
      var to = anchor.offsetTop + 80;
      window.scrollTo(0, to);
    },
    //手动判断正确还是错误
    checkAnswer(qu,isRight){
        if(isRight){
          qu.actualScore=qu.score;
        }else{
          qu.actualScore = 0;
        }
      qu.isRight = isRight;
      qu.right = isRight;
    },
    submitForm(){
      console.log(this.postForm)
      this.loading=true;
      reviewPaper(this.postForm).then(response=>{
        console.log(response)
        this.loading=false
      })
    }
  }
}
</script>

<style scoped>
.all-box {
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  flex-direction: row;
}

.all-box-left {
  width: 250px;
  flex: 0 0 250px;
}

.all-box-center {
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  padding-left: 20px;
  padding-right: 20px;
}

.left-box {
  height: calc(100vh - 120px);
  position: fixed;
  overflow-y: auto;
  clear: both;
}

.left-box-normal {
  top: 103px;
}

.el-card.is-always-shadow {
  -webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}

.group-card {
  margin-bottom: 15px;
  line-height: 28px;
  width: 100%;
}

.group-card-body {
  line-height: 22px;
  font-size: 14px;
}

.group-card .num {
  color: #1890FF;
}

.card-box {
  display: flex;
  flex-wrap: wrap;
  align-content: flex-start;
  margin-left: -5px;
  margin-top: 10px;
}

.card-box .item {
  width: 30px;
  height: 20px;
  line-height: 20px;
  background: #eee;
  border-radius: 2px;
  font-size: 6px;
  align-items: center;
  cursor: pointer;
  margin: 5px 5px 10px 5px;
}

.card-box .item .right {
  border-radius: 2px;
  background: #1890ff;
  color: #fff;
}

.card-box .item .error {
  border-radius: 2px;
  background: #FF4B50;
  color: #fff;
}

.card-box .item .wait {
  border-radius: 2px;
  background: #FFBA00;
  color: #fff;
}

.card-box .item .num {
  text-align: center;
  color: #555;
  font-weight: 700;
  height: 20px;
}

.content-card {
  margin-bottom: 20px;
  line-height: 28px;
}

.left-box-scrolled {
  top: 20px;
  height: calc(100vh - 40px);
}

.all-box-right {
  width: 200px;
  -webkit-box-flex: 0;
  -ms-flex: 0 0 200px;
  flex: 0 0 200px;
  text-align: center;
  line-height: 32px;
  font-size: 14px;
  font-weight: 700;
}

>>> .el-divider--horizontal {
  margin: 5px 0;
}

.check-icon {
  font-size: 28px;
  color: #ddd;
  cursor: pointer;
  margin-right: 10px;
}
.check-icon-right {
  color: #03DD6D;
}
.check-icon-error{
  color: #ff0000;
}
.check-box {
  background: #f5f5f5;
  padding: 20px;
  margin-left: 20px;
  line-height: 40px;
  width: 200px;
  font-size: 14px;
  font-weight: 700;
}

.check-box .item {
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
}
</style>
