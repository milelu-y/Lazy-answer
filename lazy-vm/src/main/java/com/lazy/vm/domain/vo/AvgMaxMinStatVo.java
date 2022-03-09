package com.lazy.vm.domain.vo;

/**
 * @author Lazy
 * @date 2022/3/9 15:28
 */
public class AvgMaxMinStatVo {

    private String title;

    private String userCount;

    private Integer count;

    private Double agv;

    private Integer sum;

    private Integer max;

    private Integer min;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAgv() {
        return agv;
    }

    public void setAgv(Double agv) {
        this.agv = agv;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
