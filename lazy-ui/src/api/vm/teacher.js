import request from '@/utils/request'

// 查询讲师列表
export function listTeacher(query) {
  return request({
    url: '/vm/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询讲师详细
export function getTeacher(id) {
  return request({
    url: '/vm/teacher/' + id,
    method: 'get'
  })
}

// 新增讲师
export function addTeacher(data) {
  return request({
    url: '/vm/teacher',
    method: 'post',
    data: data
  })
}

// 修改讲师
export function updateTeacher(data) {
  return request({
    url: '/vm/teacher',
    method: 'put',
    data: data
  })
}

// 删除讲师
export function delTeacher(id) {
  return request({
    url: '/vm/teacher/' + id,
    method: 'delete'
  })
}

// 导出讲师
export function exportTeacher(query) {
  return request({
    url: '/vm/teacher/export',
    method: 'get',
    params: query
  })
}
