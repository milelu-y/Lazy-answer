import request from '@/utils/request'

//用户本次作业得分情况
export function userCurrentWork(query) {
  return request({
    url: '/vm/reportForm/userAchievement',
    method: 'post',
    data: query
  })
}

export function userAllWork(id) {
  return request({
    url: '/vm/reportForm/userListAchievement/'+id,
    method: 'get',
  })
}
