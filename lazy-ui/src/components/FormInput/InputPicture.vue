<template>
  <div>
    <el-col :xl="{ span: 4 }" :lg="{ span: 12 }" :md="{ span: 12 }">
      <div>
        <el-upload
          action="#"
          :http-request="uploadRequest"
          :on-success="uploadSuccess"
          :fileList="fileList"
          :limit="1"
          list-type="picture-card"
          :auto-upload="true"
          :multiple="false">
          <span><i slot="default" class="el-icon-plus"></i>{{ data.fieldAliase }}</span>
          <div slot="file" slot-scope="{file}">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
            >
            <span class="el-upload-list__item-actions">
        <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleDownload(file)"
        >
          <i class="el-icon-download"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
          </div>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible" append-to-body>
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </div>
    </el-col>
  </div>
</template>

<script>
import {uploadFile, deleteFile} from "@/api/tool/upload";

export default {
  name: "InputPicture",
  props: {
    form: {
      type: Object,
      required: false
    },
    data: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      fileList: []
    };
  },
  watch: {},
  methods: {
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
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleDownload(file) {

    },
    /**
     * 覆盖默认上传
     */
    uploadRequest(file) {
      let formData = new FormData();
      formData.append("file", file.file)
      uploadFile(formData).then(response => {
        if (response.code === 200) {
          console.log(response)
          file.onSuccess(response)
          this.msgSuccess("~上传成功~");
        } else {
          this.msgError(response.msg)
        }
      })
    },
    /**
     * 上传成功回调
     */
    uploadSuccess(file, response) {
      this.fileList.push(response);
    },
    getFieldValue() {
      return this.filterField()
    },
    filterField() {
      const fields = []
      if (this.fileList.length > 0) {
        const field = this.fileList[0]
        const fileObj = field.response.data
        fields.push({
          uid: fileObj.fileUid,
          fileUid: fileObj.fileUid,
          name: field.name,
          id: fileObj.id,
          status: 'done',
          url: fileObj.url
        })
      }
      return fields
    },
    setFieldValue(value) {
      if (value) {
        this.beforeSetVal(value)
      }
    },
    fieldClear() {
      this.fileList = []
    },
    beforeSetVal(value) {
      if (value && value instanceof Array) {
        const fileList = []
        for (let i = 0; i < value.length; i++) {
          const val = value[i]
          val.response = {
            code: 0,
            msg: '操作成功!',
            data: Object.assign({}, val)
          }
          fileList.push(val)
        }
        this.fileList = fileList
      }
    }
  }
}
</script>

<style scoped>

</style>
