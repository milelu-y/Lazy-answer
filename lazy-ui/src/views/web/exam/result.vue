<template>
  <div class="result-box">
    <div class="score">
      {{ paperResultData.userScore }}分
    </div>
    <div class="thanks">
      {{paperResultData.thanks}}
    </div>
  </div>
</template>

<script>
import {paperResult} from "@/api/vm/testPaper";

export default {
  name: "result",
  data() {
    return {
      paperResultData: {}
    }
  },
  created() {
    let id = this.$route.params.id
    console.log(id)
    paperResult(id).then(response => {
      console.log(response)
      this.paperResultData = response.data
    })
  }
}
</script>

<style scoped>
.result-box{
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  line-height: 50px;
  min-height: 50vh;
}

.result-box .score{
  font-size: 60px;
  font-weight: 700;
  color: #0a84ff;
}
.result-box .thanks{
  font-size: 15px;
  font-weight: 700;
}
</style>
