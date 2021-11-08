<template>
  <div>
    10000分，你他妈太棒了
  </div>
</template>

<script>
import {paperResult} from "@/api/vm/testPaper";

export default {
  name: "result",
  created() {
    let id = this.$route.params.id
    console.log(id)
    paperResult(id).then(response => {
      console.log(response)
    })
  }
}
</script>

<style scoped>

</style>
