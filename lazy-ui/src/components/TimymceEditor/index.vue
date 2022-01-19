<template>
  <vue-tinymce
    v-model="content"
    :setting="setting" />
</template>

<script>
import {uploadFile} from "@/api/tool/upload";

export default {
  name: "index",
  data(){
    return{
      content:'',
      setting: {
        menubar: false,
        toolbar: "kityformula-editor | undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat |",
        toolbar_drawer: "sliding",
        quickbars_selection_toolbar: "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor",
        plugins: "kityformula-editor link image media table lists fullscreen quickbars",
        language: 'zh_CN', //本地化设置
        height: 350,
        images_upload_credentials: true,
        images_upload_handler: this.uploadImgFile,
      },
    }
  },
  methods:{
    uploadImgFile(blobInfo, success, failure) {
      const fd = new FormData()
      fd.append('file', blobInfo.blob())
      uploadFile(fd)
        .then(response => {
          const result = response.data
          success(result.url)
        })
        .catch(err => {
          console.log(err)
        })
    },
    setContent(val){
      this.content = val
    }
  }
}
</script>

<style scoped>

</style>
