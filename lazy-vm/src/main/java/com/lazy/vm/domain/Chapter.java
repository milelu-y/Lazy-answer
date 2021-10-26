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
 * 章节对象 edu_chapter
 *
 * @author lazy
 * @date 2021-10-26
 */
public class Chapter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    @IDField
    private String id;

    /**
     * 课程ID
     */
    @Excel(name = "课程ID")
    private String courseId;

    /**
     * 章节名称
     */
    @Excel(name = "章节名称")
    private String title;

    /**
     * 显示排序
     */
    @Excel(name = "显示排序")
    private Integer sort;

    /**
     * 题库Id
     */
    @Excel(name = "题库Id")
    private String taskId;

    /**
     * 预留字段2
     */
    @Excel(name = "预留字段2")
    private String reserveField2;

    /**
     * 预留字段3
     */
    @Excel(name = "预留字段3")
    private String reserveField3;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreateTime
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @UpdateTime
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setReserveField1(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReserveField1() {
        return taskId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseId", getCourseId())
                .append("title", getTitle())
                .append("sort", getSort())
                .append("reserveField1", getReserveField1())
                .append("reserveField2", getReserveField2())
                .append("reserveField3", getReserveField3())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .toString();
    }
}
