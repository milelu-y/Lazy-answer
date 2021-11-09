import request from '@/utils/request'

export function Experiment(data) {
  return request({
    url: '/vm/Experiment/XYZToBLH',
    method: 'post',
  })
}

export function BLHToXYZ(data) {
  return request({
    url: '/vm/Experiment/BLHToXYZ',
    method: 'post',
  })
}
export function XYZ2ENU(data) {
  return request({
    url: '/vm/Experiment/XYZ2ENU',
    method: 'post',
  })
}

export function exp10_shuangpinDianLiCeng() {
  return request({
    url: '/vm/Experiment/exp10_shuangpinDianLiCeng',
    method: 'post',
  })
}

