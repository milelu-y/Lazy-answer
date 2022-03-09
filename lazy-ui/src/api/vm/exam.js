import request from '@/utils/request'

// 查询试卷添加列表
export function listExam(query) {
  return request({
    url: '/vm/exam/list',
    method: 'get',
    params: query
  })
}

export function reviewList(query) {
  return request({
    url: '/vm/exam/review',
    method: 'get',
    params: query
  })
}

// 查询试卷添加列表
export function listUserExam(query) {
  return request({
    url: '/vm/exam/userExamList',
    method: 'get',
    params: query
  })
}

// 查询试卷添加列表关联课程
export function listExamJoinPaper(query) {
  return request({
    url: '/vm/exam/listExamJoinPaper',
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

//查询统计学员概览
export function userStat(query) {
  return request({
    url: '/vm/exam/userStat',
    method: 'get',
    params: query
  })
}
export function errorStat(query) {
  return request({
    url: '/vm/exam/errorStat',
    method: 'get',
    params: query
  })
}
//查询学员做题记录
export function paging(query) {
  return request({
    url: '/vm/exam/paging',
    method: 'get',
    params: query
  })
}

