import request from '@/utils/request'

// 查询实验模块列表
export function listExperiment(query) {
  return request({
    url: '/vm/experiment/list',
    method: 'get',
    params: query
  })
}

// 查询实验模块详细
export function getExperiment(id) {
  return request({
    url: '/vm/experiment/' + id,
    method: 'get'
  })
}

// 新增实验模块
export function addExperiment(data) {
  return request({
    url: '/vm/experiment',
    method: 'post',
    data: data
  })
}

// 修改实验模块
export function updateExperiment(data) {
  return request({
    url: '/vm/experiment',
    method: 'put',
    data: data
  })
}

// 删除实验模块
export function delExperiment(id) {
  return request({
    url: '/vm/experiment/' + id,
    method: 'delete'
  })
}

// 导出实验模块
export function exportExperiment(query) {
  return request({
    url: '/vm/experiment/export',
    method: 'get',
    params: query
  })
}

//接收机参数
export function receiver(query) {
  return request ({
    url: '@public/a',
    method:'get',
    params:query
  })
}
