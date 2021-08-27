import request from '@/utils/request'

// 上传文件
export function uploadFile(data) {
  return request({
    url: '/upload/uploadFile',
    method: 'post',
    data: data
  })
}

export function deleteFile(id) {
  return request({
    url: '/upload/deleteFile/'+id,
    method: 'delete',
  })
}
