import request from '@/utils/request'

// 查询试卷添加列表
export function listExam(query) {
  return request({
    url: '/vm/exam/list',
    method: 'get',
    params: query
  })
}

// 查询试卷添加详细
export function getExam(id) {
  return request({
    url: '/vm/exam/' + id,
    method: 'get'
  })
}

// 新增试卷添加
export function addExam(data) {
  return request({
    url: '/vm/exam',
    method: 'post',
    data: data
  })
}

// 修改试卷添加
export function updateExam(data) {
  return request({
    url: '/vm/exam',
    method: 'put',
    data: data
  })
}

// 删除试卷添加
export function delExam(id) {
  return request({
    url: '/vm/exam/' + id,
    method: 'delete'
  })
}

// 导出试卷添加
export function exportExam(query) {
  return request({
    url: '/vm/exam/export',
    method: 'get',
    params: query
  })
}
