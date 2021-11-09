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

export function exp9_bds_dianLiCeng() {
  return request({
    url: '/vm/Experiment/exp9_bds_dianLiCeng',
    method: 'post',
  })
}
export function exp8_gps_dianLiCeng() {
  return request({
    url: '/vm/Experiment/exp8_gps_dianLiCeng',
    method: 'post',
  })
}
export function exp7_dianwen() {
  return request({
    url: '/vm/Experiment/exp7_dianwen',
    method: 'post',
  })
}export function exp5_BDCS_LS() {
  return request({
    url: '/vm/Experiment/exp5_BDCS_LS',
    method: 'post',
  })
}

