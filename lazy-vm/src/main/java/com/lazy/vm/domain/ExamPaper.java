package com.lazy.vm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 试卷对象 edu_exam_paper
 *
 * @author lazy
 * @date 2021-11-03
 */
public class ExamPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 选择老师 */
    @Excel(name = "选择老师")
    private String userId;

    /** 试卷标题 */
    @Excel(name = "试卷标题")
    private String title;

    /** 分数 */
    @Excel(name = "分数")
    private Long totalScore;

    /** 0 系统出题   1  老师选题 */
    @Excel(name = "0 系统出题   1  老师选题")
    private Long type;

    /** 试卷类别（0：理工类   1：文工类） */
    @Excel(name = "试卷类别", readConverterExp = "0=：理工类,1=：文工类")
    private String category;

    /** 试卷总题数 */
    @Excel(name = "试卷总题数")
    private Integer quCount;

    /** 考试id */
    @Excel(name = "考试id")
    private String examId;

    /** 预留 */
    @Excel(name = "预留")
    private String reserveField3;

    /** 预留 */
    @Excel(name = "预留")
    private String reserveField4;

    /** 预留 */
    @Excel(name = "预留")
    private String reserveField5;

    /** 创建时间 */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTotalScore(Long totalScore)
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore()
    {
        return totalScore;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setQuCount(Integer quCount)
    {
        this.quCount = quCount;
    }

    public Integer getQuCount()
    {
        return quCount;
    }
    public void setExamId(String examId)
    {
        this.examId = examId;
    }

    public String getExamId()
    {
        return examId;
    }
    public void setReserveField3(String reserveField3)
    {
        this.reserveField3 = reserveField3;
    }

    public String getReserveField3()
    {
        return reserveField3;
    }
    public void setReserveField4(String reserveField4)
    {
        this.reserveField4 = reserveField4;
    }

    public String getReserveField4()
    {
        return reserveField4;
    }
    public void setReserveField5(String reserveField5)
    {
        this.reserveField5 = reserveField5;
    }

    public String getReserveField5()
    {
        return reserveField5;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("title", getTitle())
            .append("totalScore", getTotalScore())
            .append("type", getType())
            .append("category", getCategory())
            .append("quCount", getQuCount())
            .append("examId", getExamId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("reserveField3", getReserveField3())
            .append("reserveField4", getReserveField4())
            .append("reserveField5", getReserveField5())
            .toString();
    }
}
