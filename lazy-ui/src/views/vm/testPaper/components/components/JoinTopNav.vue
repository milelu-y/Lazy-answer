<template>
  <div>
    <div class="tool-box">
      <div class="tool-box-left">
        <el-form ref="form" label-position="left" label-width="100px">
          <div class="box-item">
            <el-form-item label="试卷名称:" style="width: 500px">
              <el-input v-model="data.title"></el-input>
            </el-form-item>
          </div>
          <div class="box-item">
            <el-form-item label="试卷分类" prop="title" style="width: 500px">
              <el-select v-model="data.category" placeholder="请选择" style="width: 100%">
                <el-option
                  v-for="item in courseOptions"
                  :key="item.id"
                  :label="item.title"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
        </el-form>
      </div>
      <div class="tool-box-right">
        <el-dropdown @command="handleCommand">
          <el-button type="primary">
            添加新的大题<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item :command="{ quType: '0', title: '单选题' }">单选题</el-dropdown-item>
            <el-dropdown-item :command="{ quType: '1', title: '多选题' }">多选题</el-dropdown-item>
            <el-dropdown-item :command="{ quType: '2', title: '判断题' }">判断题</el-dropdown-item>
            <el-dropdown-item :command="{ quType: '3', title: '简答题' }">简答题</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
import {listCourse} from "@/api/vm/course";
import JoinQuList from "@/views/vm/testPaper/components/components/JoinQuList";

export default {
  name: "JoinTopNav",
  components: {JoinQuList},
  props: {
    data: {
      type: Object
    }
  },
  data() {
    return {
      courseOptions: []
    }
  },
  created() {
    listCourse().then(response => {
      this.courseOptions = response.rows;
    })
  },
  methods: {
    handleCommand(command) {
        this.$emit("create",command)
    }
  }
}
</script>

<style scoped>
.all-box-center {
  flex-grow: 1;
  padding-left: 20px;
}

.tool-box {
  padding: 20px;
  background: rgb(238, 238, 238);
  margin-bottom: 20px;
  border-radius: 5px;
  line-height: 45px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.tool-box-left {
  flex-grow: 1;
  font-weight: 700;
}

.box-item {
  display: flex;
  align-items: center;
}

.tool-box-right {
  width: 200px;
}
</style>
