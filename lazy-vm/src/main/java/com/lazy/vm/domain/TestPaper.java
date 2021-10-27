package com.lazy.vm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 试卷对象 edu_test_paper
 *
 * @author fgq
 * @date 2021-10-27
 */
public class TestPaper extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private String id;

    /**
     * 试卷标题
     */
    @Excel(name = "试卷标题")
    private String title;

    /**
     * 分数
     */
    @Excel(name = "分数")
    private Long fraction;

    /**
     * 0 系统出题   1  老师选题
     */
    @Excel(name = "0 系统出题   1  老师选题")
    private Long type;

    /**
     * 试卷类别（0：理工类   1：文工类）
     */
    @Excel(name = "试卷类别", readConverterExp = "0=：理工类,1=：文工类")
    private Long category;

    /**
     * 添加时间
     */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /**
     * 选择老师
     */
    @Excel(name = "选择老师")
    private String userId;

    /**
     * 预留
     */
    @Excel(name = "预留")
    private String reserveField1;

    /**
     * 预留
     */
    @Excel(name = "预留")
    private String reserveField2;

    /**
     * 预留
     */
    @Excel(name = "预留")
    private String reserveField3;

    /**
     * 预留
     */
    @Excel(name = "预留")
    private String reserveField4;

    /**
     * 预留
     */
    @Excel(name = "预留")
    private String reserveField5;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setFraction(Long fraction) {
        this.fraction = fraction;
    }

    public Long getFraction() {
        return fraction;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getCategory() {
        return category;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setReserveField1(String reserveField1) {
        this.reserveField1 = reserveField1;
    }

    public String getReserveField1() {
        return reserveField1;
    }

    public void setReserveField2(String reserveField2) {
        this.reserveField2 = reserveField2;
    }

    public String getReserveField2() {
        return reserveField2;
    }

    public void setReserveField3(String reserveField3) {
        this.reserveField3 = reserveField3;
    }

    public String getReserveField3() {
        return reserveField3;
    }

    public void setReserveField4(String reserveField4) {
        this.reserveField4 = reserveField4;
    }

    public String getReserveField4() {
        return reserveField4;
    }

    public void setReserveField5(String reserveField5) {
        this.reserveField5 = reserveField5;
    }

    public String getReserveField5() {
        return reserveField5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("fraction", getFraction())
                .append("type", getType())
                .append("category", getCategory())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .append("userId", getUserId())
                .append("reserveField1", getReserveField1())
                .append("reserveField2", getReserveField2())
                .append("reserveField3", getReserveField3())
                .append("reserveField4", getReserveField4())
                .append("reserveField5", getReserveField5())
                .toString();
    }

}