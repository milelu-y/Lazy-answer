import request from '@/utils/request'

// 查询试卷列表
export function listTestPaper(query) {
  return request({
    url: '/vm/testPaper/list',
    method: 'get',
    params: query
  })
}

// 查询试卷详细
export function getTestPaper(id) {
  return request({
    url: '/vm/testPaper/' + id,
    method: 'get'
  })
}

// 新增试卷
export function addTestPaper(data) {
  return request({
    url: '/vm/testPaper',
    method: 'post',
    data: data
  })
}

// 修改试卷
export function updateTestPaper(data) {
  return request({
    url: '/vm/testPaper',
    method: 'put',
    data: data
  })
}

// 删除试卷
export function delTestPaper(id) {
  return request({
    url: '/vm/testPaper/' + id,
    method: 'delete'
  })
}

// 导出试卷
export function exportTestPaper(query) {
  return request({
    url: '/vm/testPaper/export',
    method: 'get',
    params: query
  })
}
