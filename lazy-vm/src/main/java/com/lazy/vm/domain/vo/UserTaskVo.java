package com.lazy.vm.domain.vo;

import java.util.List;

public class UserTaskVo {

    public List userId;
    public String examId;


    public List getUserId() {
        return userId;
    }

    public void setUserId(List userId) {
        this.userId = userId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
