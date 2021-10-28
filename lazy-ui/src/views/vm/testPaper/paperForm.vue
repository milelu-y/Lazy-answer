<template>
  <div class="app-container">
    <join-form :title="title" :type="type" :category="category"></join-form>
  </div>
</template>

<script>
import JoinForm from "@/views/vm/testPaper/components/joinForm";
export default {
  name: "paperForm",
  components: {JoinForm},
  data(){
    return{
      title:'',
      type:'',
      category:'',
    }
  },
  created() {
    this.title = this.$route.params.title
    this.type = this.$route.params.type
    this.category = this.$route.params.category
  }
}
</script>

<style scoped>

</style>
