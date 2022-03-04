<template>
  <div class="app-container home">
    <div  v-for="(item,index) in exList">
      <el-col :span="6">
        <el-card id="backs" 
        style="height: 25vh;position: relative;border-radius: 10px;margin: 5px;" >
          <div class="card-title1">
            {{ item.title }}
          </div>
          <div class="card-conters">
            {{ item.intro }}
          </div>
          <el-button class="card-btn btn-bg1" type="primary"  @click="toPage(item)" size="small" plain>查看详情</el-button>
          <div class="card-img">
            <img width="100%">
          </div>
        </el-card>
      </el-col>
    </div>
  </div>
</template>

<script>
import {addExperiment, getExperiment, listExperiment, updateExperiment} from "@/api/vm/ex";

export default {
  name: "index",
  data() {
    return {
      // 版本号
      version: "3.6.0",
      exList: []
    };
  },
  created() {
    listExperiment().then(response => {
      this.exList = response.rows
    })
  },
  methods: {
    
    goTarget(href) {
      window.open(href, "_blank");
    },
    getList() {

    },
    toPage(item) {
      let url = item.url
      let id = item.id
      this.$router.push({
        path: url,
        query: { id: id}
      })
    }
  },
};
</script>

<style scoped lang="scss">
.main {
  width: 100%;
  height: 100vh;
  background-color: #2e9adf;
}

/**非公共部分 */
.c-box1 {
  background-color: #2e9adf;
  padding: 0 20px;
  margin: 20px auto 0;
  height: calc(90vh - 20px);
}

.s-box2 {
  display: flex;
  justify-content: space-between;
  margin: 20px auto 0;
  padding: 0 20px;
}

.s-box3a {
  width: 36%;
  background-color: #fff;
  height: 40vh;
  border-radius: 12px;
}

.s-box3b {
  width: 26%;
  height: 40vh;
}

.s-box4a {
  width: 100%;
  height: 10vh;
  background-color: #fff;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.s-box4a .el-input__inner {
  border-radius: 100px !important;
}

.s-box4b {
  width: 100%;
  height: 28vh;
  background-color: #fff;
  margin-top: 20px;
  border-radius: 12px;
}

.s-box2b {
  //height: 40vh;
  margin: 20px auto 0;
  border-radius: 12px;
  background-color: #fff;
  width: calc(100% - 40px);
}

.v-title {
  padding: 0 20px;
  height: 6vh;
  text-align: left;
  line-height: 6vh;
  font-size: 20px;
  border-bottom: 1px solid #f2f2f2;
}

.v-title1 {
  padding: 0 20px;
  height: 6vh;
  text-align: left;
  line-height: 6vh;
  display: flex;
  font-size: 20px;
  align-items: center;
  border-bottom: 1px solid #f2f2f2;
  justify-content: space-between;
}

.v-title1 span:nth-child(2) {
  color: #666666;
  font-size: 16px;
}

.v-box {
  align-items: center;
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
}

.box-card {
  //width: 32%;
  margin: 20 auto 0;
  max-height: 220px;
  height: 40vh;
  position: relative;
}

.box-card:nth-child(1) {
  background-color: #44d9c0;
}

.box-card:nth-child(2) {
  background-color: #f0c783;
}

.box-card:nth-child(3) {
  background-color: #3dafe2;
}

.card-title1 {
  margin-top: 5px;
  font-size: 22px;
  color: #000;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: 600;
}
.card-conters{
  color: #000;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  text-align: center;
    line-height: 50px;
}

.card-title2 {
  margin-top: 5px;
  font-size: 20px;
  text-align: left;
}

.title-color1 {
  color: #1ead95;
}


.title-color2 {
  color: #c98d2c;
}

.btn-bg2 {
  background-color: #c98d2c;
  border: 1px solid #ffe9c4;
  color: #ffe9c4;
}

.title-color3 {
  color: #187fad;
}

.btn-bg3 {
  background-color: #187fad;
  border: 1px solid #c0ecff;
  color: #c0ecff;
}

.card-btn {
  position: absolute;
  text-align: center;
  right: 5%;
  bottom: 5%;
}

.card-img {
  position: absolute;
  overflow: hidden;
  right: 20px;
  bottom: 20px;
  width: 80px;
  overflow: hidden;
}

.v-bar {
  width: 100%;
  height: 48vh;
  margin: 10 auto;
  overflow: hidden;
}

.v-pie {
  width: 100%;
  height: 48vh;
  margin: 10 auto;
  overflow: hidden;
  position: relative;
}

.v-expie {
  position: absolute;
  right: 30px;
  top: 0;
  bottom: 0;
  margin-top: auto;
  margin-bottom: auto;
  height: 48vh;
}

.v-pie1 {
  height: calc(48vh / 9);
  font-size: 14px;
  display: flex;
  align-items: center;
}

.v-pie1 div {
  position: relative;
  margin-right: 14px;
}

.v-pie1 div:nth-child(1):before {
  position: absolute;
  content: '';
  top: 0;
  bottom: 0;
  margin-bottom: auto;
  margin-top: auto;
  left: -16px;
  width: 6px;
  height: 6px;
  border-radius: 50%;
}

.v-pie1:nth-child(1) div {
  color: #42c3af;
}

.v-pie1:nth-child(1) div:nth-child(1):before {
  background-color: #42c3af;
}

.v-pie1:nth-child(2) div {
  color: #31a4ff;
}

.v-pie1:nth-child(2) div:nth-child(1):before {
  background-color: #31a4ff;
}

.v-pie1:nth-child(3) div {
  color: #f263b4;
}

.v-pie1:nth-child(3) div:nth-child(1):before {
  background-color: #f263b4;
}

.v-pie1:nth-child(4) div {
  color: #ffc15e;
}

.v-pie1:nth-child(4) div:nth-child(1):before {
  background-color: #ffc15e;
}

.v-pie1:nth-child(5) div {
  color: #fa675c;
}

.v-pie1:nth-child(5) div:nth-child(1):before {
  background-color: #fa675c;
}

.v-pie1:nth-child(6) div {
  color: #1c9399;
}

.v-pie1:nth-child(6) div:nth-child(1):before {
  background-color: #1c9399;
}

.v-pie1 div:nth-child(1) {
  color: #333 !important;
}


.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

