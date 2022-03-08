package com.lazy.vm.domain.vo;

/**
 * @author Lazy
 * @date 2022/3/8 15:57
 */
public class Stat {

    private String examId;

    private String nickName;

    private Integer count;

    private Integer max;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
