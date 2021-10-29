<template>
  <div>
    <div class="all-box">
      <div class="all-box-left">
        <el-card class="left-box left-box-normal" style="width: 250px">
          <div class="group-card">
            <div style="font-size: 16px; font-weight: 700;">
              <div> 组卷方式：<span>{{ type === '0' ? '选题组卷' : '' }}</span></div>
              <div> 试题总数： <i class="num">{{ postForm.quCount }}</i></div>
              <div> 试卷总分：<i class="num">{{postForm.totalScore}}</i></div>
            </div>
            <el-button type="primary" size="mini" style="width: 100%;margin-top: 20px" @click="handleSave">
              保存
            </el-button>
          </div>
          <div v-for="(item,index) in postForm.groupList">
            <div class="group-card">
              <div style="line-height: 40px;vertical-align: middle">
                <el-button type="text" size="medium">{{ item.title }}</el-button>
                <el-divider/>
                <span style="float: right">
                  <el-popover
                    placement="top"
                    width="160"
                  >
                      <p>这是一段内容这是一段内容确定删除吗？</p>
                      <div style="text-align: right; margin: 0">
                        <el-button size="mini" type="text">取消</el-button>
                        <el-button type="primary" size="mini" @click="removeItem(postForm.groupList,index)">确定</el-button>
                      </div>
                      <el-button slot="reference" type="text" icon="el-icon-delete">删除</el-button>
                      </el-popover>
                </span>
              </div>
              <div class="group-card-body">
                <div> 共 <i class="num">{{ item.quCount }}</i> 题，共 <i class="num">{{ item.totalScore }}</i> 分 </div>
                <div>每题
                  <el-input size="mini" v-model="item.perScore" style="width: 60px;" @input="handlePerChange($event, item, index)"></el-input>
                  分
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
      <div class="all-box-center">
        <join-top-nav :data.sync="postForm" @create="createGroup"></join-top-nav>
        <join-qu-list ref="joinQuList" :groupList.sync="postForm.groupList" :type.sync="postForm.type" @count="countCall" @score="paperScoreCall"> </join-qu-list>
      </div>
    </div>
  </div>
</template>

<script>
import JoinTopNav from "@/views/vm/testPaper/components/components/JoinTopNav";
import JoinQuList from "@/views/vm/testPaper/components/components/JoinQuList";
import {addTestPaper, updateTestPaper} from "@/api/vm/testPaper";

export default {
  name: "joinForm",
  components: {JoinQuList, JoinTopNav},
  props: {
    title: {
      type: String
    },
    type: {
      type: String
    },
    category: {
      type: String
    },
    id: {
      type: String
    },
    data: Object
  },
  watch: {
    data: {
      handler: function handler(val) {
        if (val && val.id) {
          this.postForm = this.data;
        }
      }
    }
  },
  data() {
    return {
      postForm: {
        quCount: 0,
        totalScore: 0,
        title: '',
        type: '',
        category: '',
        groupList: []
      }
    }
  },
  created() {
    this.postForm.title = this.title;
    this.postForm.type = this.type;
    this.postForm.category = this.category;
  },
  mounted() {
    var _this = this;
    // 监听滚动条
    window.addEventListener('scroll', function () {
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
  methods: {
    countCall(count){
      this.postForm.quCount = count
    },
    paperScoreCall(score){
      this.postForm.totalScore = score
    },
    createGroup(command) {
      var data = {
        anchor: new Date().getTime(),
        title: command.title,
        quType: command.quType,
        quCount: 0,
        totalScore: 0,
        perScore: 0,
        quRand: false,
        itemRand: false,
        strictSort: 0,
        quList: [],
        pathScore: false
      };
      this.postForm.groupList.push(data);
      var index = this.postForm.groupList.length - 1;
      this.$refs.joinQuList.openSelectQu(index);
    },
    removeItem(list,index){
      list.splice(index, 1);
    },
    //同步分数
    handlePerChange(e, item, index){
      if (!e || e == '') {
        e = 0;
        item.perScore = 0;
      }
      var quGroup = this.postForm.groupList[index];
      var quList = quGroup.quList;
      for (var i = 0; i < quList.length; i++) {
        quList[i].score = e;
      } // 覆盖分数
      quGroup.quCount = quList.length;
      quGroup.totalScore = e * quList.length;
    },
    handleSave(){
      console.log(this.id)
      if (this.id!='' && this.id!=undefined){
        console.log(this.postForm)
        updateTestPaper(this.postForm).then(response=>{
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '试卷保存成功',
              type: 'success'
            });
            this.$router.go(-1)
          }
        })
      }else {
        addTestPaper(this.postForm).then(response => {
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '试卷保存成功',
              type: 'success'
            });
            this.$router.go(-1)
          }
        })
      }

    }
  }
}
</script>

<style scoped>
.all-box {
  display: flex;
  flex-direction: row;
}

.all-box-left {
  width: 250px;
  flex: 0 0 250px;
}

.all-box-center {
  flex-grow: 1;
  padding-left: 20px;
}

>>> .el-card.is-always-shadow {
  -webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}

.left-box-normal {
  top: 103px;
}

.left-box {
  height: calc(100vh - 120px);
  position: fixed;
  overflow: auto;
  clear: both;
}

.group-card {
  margin-bottom: 15px;
  line-height: 28px;
  width: 100%;
}

.group-card-body {
  line-height: 32px;
  font-size: 14px;
}

.group-card .num {
  color: #1890FF;
}

>>> .el-divider--horizontal {
  margin: 5px 0;
}
</style>
