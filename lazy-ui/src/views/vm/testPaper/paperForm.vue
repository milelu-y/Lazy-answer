<template>
  <div class="app-container">
    <join-form :title="title" :type="type" :category="category" :data="data" :id="id"></join-form>
  </div>
</template>

<script>
import JoinForm from "@/views/vm/testPaper/components/joinForm";
import {getTestPaper} from "@/api/vm/testPaper";

export default {
  name: "paperForm",
  components: {JoinForm},
  data() {
    return {
      title: '',
      type: '',
      category: '',
      id: '',
      data:{}
    }
  },
  created() {
    var id = this.$route.params.id
    this.id = this.$route.params.id
    this.title = this.$route.params.title
    this.type = this.$route.params.type
    this.category = this.$route.params.category
    if (typeof id !== 'undefined') {
      this.getPaperInfo(id);
    }
  },
  methods: {
    getPaperInfo(id) {
      getTestPaper(id).then(response => {
        console.log(response.data)
        this.data = response.data
      })
    }
  }
}
</script>

<style scoped>

</style>
