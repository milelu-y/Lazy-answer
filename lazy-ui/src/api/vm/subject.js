import request from '@/utils/request'

// 查询课程科目列表
export function listSubject(query) {
  return request({
    url: '/vm/subject/list',
    method: 'get',
    params: query
  })
}

// 查询课程科目详细
export function getSubject(id) {
  return request({
    url: '/vm/subject/' + id,
    method: 'get'
  })
}

// 新增课程科目
export function addSubject(data) {
  return request({
    url: '/vm/subject',
    method: 'post',
    data: data
  })
}

// 修改课程科目
export function updateSubject(data) {
  return request({
    url: '/vm/subject',
    method: 'put',
    data: data
  })
}

// 删除课程科目
export function delSubject(id) {
  return request({
    url: '/vm/subject/' + id,
    method: 'delete'
  })
}

// 导出课程科目
export function exportSubject(query) {
  return request({
    url: '/vm/subject/export',
    method: 'get',
    params: query
  })
}
