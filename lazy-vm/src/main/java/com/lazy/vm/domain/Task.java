package com.lazy.vm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.UpdateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 题库对象 edu_task
 *
 * @author lazy
 * @date 2021-10-26
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题库id */
    private String id;

    /** 课程id */
    @Excel(name = "课程id")
    private String courseId;

    /** 题目类型 */
    @Excel(name = "题目类型")
    private String type;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String title;

    /** 单选题数量 */
    @Excel(name = "单选题数量")
    private Integer singleCount;

    /** 多选题数量 */
    @Excel(name = "多选题数量")
    private Integer multipleCount;

    /** 判断题数量 */
    @Excel(name = "判断题数量")
    private Integer judgment;

    /** 填空题数量 */
    @Excel(name = "填空题数量")
    private Integer fillCount;

    /** 问答题数量 */
    @Excel(name = "问答题数量")
    private Integer aqCount;

    /** 创建时间 */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 更新时间 */
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 预留 */
    @Excel(name = "预留")
    private String reserveField1;

    /** 预留 */
    @Excel(name = "预留")
    private String reserveField2;

    /** 预留 */
    @Excel(name = "预留")
    private String reserveField3;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public String getCourseId()
    {
        return courseId;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setSingleCount(Integer singleCount)
    {
        this.singleCount = singleCount;
    }

    public Integer getSingleCount()
    {
        return singleCount;
    }
    public void setMultipleCount(Integer multipleCount)
    {
        this.multipleCount = multipleCount;
    }

    public Integer getMultipleCount()
    {
        return multipleCount;
    }
    public void setJudgment(Integer judgment)
    {
        this.judgment = judgment;
    }

    public Integer getJudgment()
    {
        return judgment;
    }
    public void setFillCount(Integer fillCount)
    {
        this.fillCount = fillCount;
    }

    public Integer getFillCount()
    {
        return fillCount;
    }
    public void setAqCount(Integer aqCount)
    {
        this.aqCount = aqCount;
    }

    public Integer getAqCount()
    {
        return aqCount;
    }
    public void setGmtCreate(Date gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate()
    {
        return gmtCreate;
    }
    public void setGmtModified(Date gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified()
    {
        return gmtModified;
    }
    public void setReserveField1(String reserveField1)
    {
        this.reserveField1 = reserveField1;
    }

    public String getReserveField1()
    {
        return reserveField1;
    }
    public void setReserveField2(String reserveField2)
    {
        this.reserveField2 = reserveField2;
    }

    public String getReserveField2()
    {
        return reserveField2;
    }
    public void setReserveField3(String reserveField3)
    {
        this.reserveField3 = reserveField3;
    }

    public String getReserveField3()
    {
        return reserveField3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseId", getCourseId())
                .append("type", getType())
                .append("title", getTitle())
                .append("singleCount", getSingleCount())
                .append("multipleCount", getMultipleCount())
                .append("judgment", getJudgment())
                .append("fillCount", getFillCount())
                .append("aqCount", getAqCount())
                .append("remark", getRemark())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .append("reserveField1", getReserveField1())
                .append("reserveField2", getReserveField2())
                .append("reserveField3", getReserveField3())
                .toString();
    }
}
