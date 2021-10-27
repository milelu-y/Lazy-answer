package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.Excel;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import com.lazy.vm.domain.Answer;

import java.util.Date;
import java.util.List;

public class TestPaperVo {

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 分数
     */
    private Long fraction;

    /**
     * 0 系统出题   1  老师选题
     */
    private Long type;

    /**
     * 试卷类别（0：理工类   1：文工类）
     */
    private Long category;

    /**
     * 选择老师
     */
    private String userId;

    /**
     * 预留
     */
    private String reserveField1;

    /**
     * 预留
     */
    private String reserveField2;

    /**
     * 预留
     */
    private String reserveField3;

    /**
     * 预留
     */
    private String reserveField4;

    /**
     * 预留
     */
    private String reserveField5;


    private List<Answer>  answers;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getFraction() {
        return fraction;
    }

    public void setFraction(Long fraction) {
        this.fraction = fraction;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReserveField1() {
        return reserveField1;
    }

    public void setReserveField1(String reserveField1) {
        this.reserveField1 = reserveField1;
    }

    public String getReserveField2() {
        return reserveField2;
    }

    public void setReserveField2(String reserveField2) {
        this.reserveField2 = reserveField2;
    }

    public String getReserveField3() {
        return reserveField3;
    }

    public void setReserveField3(String reserveField3) {
        this.reserveField3 = reserveField3;
    }

    public String getReserveField4() {
        return reserveField4;
    }

    public void setReserveField4(String reserveField4) {
        this.reserveField4 = reserveField4;
    }

    public String getReserveField5() {
        return reserveField5;
    }

    public void setReserveField5(String reserveField5) {
        this.reserveField5 = reserveField5;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
