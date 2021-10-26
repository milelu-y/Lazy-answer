import request from '@/utils/request'

// 查询作业列表
export function listTask(query) {
  return request({
    url: '/vm/task/list',
    method: 'get',
    params: query
  })
}

// 查询作业详细
export function getTask(id) {
  return request({
    url: '/vm/task/' + id,
    method: 'get'
  })
}

// 新增作业
export function addTask(data) {
  return request({
    url: '/vm/task',
    method: 'post',
    data: data
  })
}

// 修改作业
export function updateTask(data) {
  return request({
    url: '/vm/task',
    method: 'put',
    data: data
  })
}

// 删除作业
export function delTask(id) {
  return request({
    url: '/vm/task/' + id,
    method: 'delete'
  })
}

// 导出作业
export function exportTask(query) {
  return request({
    url: '/vm/task/export',
    method: 'get',
    params: query
  })
}
