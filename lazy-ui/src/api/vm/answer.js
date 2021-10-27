import request from '@/utils/request'

// 查询作业题目列表
export function listAnswer(query) {
  return request({
    url: '/vm/Answer/list',
    method: 'get',
    params: query
  })
}

// 查询作业题目详细
export function getAnswer(id) {
  return request({
    url: '/vm/Answer/' + id,
    method: 'get'
  })
}

// 新增作业题目
export function addAnswer(data) {
  return request({
    url: '/vm/Answer',
    method: 'post',
    data: data
  })
}

// 修改作业题目
export function updateAnswer(data) {
  return request({
    url: '/vm/Answer',
    method: 'put',
    data: data
  })
}

// 删除作业题目
export function delAnswer(id) {
  return request({
    url: '/vm/Answer/' + id,
    method: 'delete'
  })
}

// 导出作业题目
export function exportAnswer(query) {
  return request({
    url: '/vm/Answer/export',
    method: 'get',
    params: query
  })
}
