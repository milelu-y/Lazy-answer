<template>
  <el-upload
    class="upload-demo"
    drag
    action="#"
    accept=".docx,.doc,.pdf"
    :http-request="uploadRequest"
    :on-success="uploadSuccess"
    :on-remove="handleRemove"
    :file-list="fileList"
    :multiple="false">
    <i class="el-icon-upload"></i>
    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <div class="el-upload__tip" slot="tip" style="color:red">只能上传word/pdf文件</div>
  </el-upload>
</template>

<script>
import {deleteFile, uploadFile} from "@/api/tool/upload";

export default {
  name: "FileUploadNew",
  data(){
    return{
      fileList:[]
    }
  },
  methods: {
    /**
     * 覆盖默认上传
     */
    uploadRequest(file) {
      let formData = new FormData();
      formData.append("file", file.file)
      uploadFile(formData).then(response => {
        if (response.code === 200) {
          file.onSuccess(response)
          this.msgSuccess("~上传成功~");
        } else {
          this.msgError(response.msg)
        }
      })
    },
    uploadSuccess(file, response) {
      this.fileList[0]={name:response.name,url:response.response.data.url}
      this.$emit("uploadCallback", response);
    },
    handleRemove(file) {
      if (file && !file.error && file.response && file.response.data.id) {
        deleteFile(file.response.data.id).then(response => {
          if (response.code === 200) {
            this.msgSuccess("~删除成功~")
            console.log('delete file success')
            this.fileList = []
          }
        })
      }

    },
  }
}
</script>

<style scoped>

</style>
