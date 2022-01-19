import request from '@/utils/request'

export function Experiment(data) {
  return request({
    url: '/vm/experiment/XYZToBLH',
    method: 'post',
  })
}

export function BLHToXYZ(data) {
  return request({
    url: '/vm/experiment/BLHToXYZ',
    method: 'post',
  })
}
export function XYZ2ENU(data) {
  return request({
    url: '/vm/experiment/XYZ2ENU',
    method: 'post',
  })
}

export function exp10_shuangpinDianLiCeng() {
  return request({
    url: '/vm/experiment/exp10_shuangpinDianLiCeng',
    method: 'post',
  })
}

export function exp9_bds_dianLiCeng() {
  return request({
    url: '/vm/experiment/exp9_bds_dianLiCeng',
    method: 'post',
  })
}
export function exp8_gps_dianLiCeng() {
  return request({
    url: '/vm/experiment/exp8_gps_dianLiCeng',
    method: 'post',
  })
}
export function exp7_dianwen() {
  return request({
    url: '/vm/experiment/exp7_dianwen',
    method: 'post',
  })
}
export function exp5_BDCS_LS() {
  return request({
    url: '/vm/experiment/exp5_BDCS_LS',
    method: 'post',
  })
}
export function exp6_BDCS_XL() {
  return request({
    url: '/vm/experiment/exp6_BDCS_XL',
    method: 'post',
  })
}
