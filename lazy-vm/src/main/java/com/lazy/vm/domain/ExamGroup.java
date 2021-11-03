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
 * 题目分组对象 edu_exam_group
 *
 * @author lazy
 * @date 2021-11-03
 */
public class ExamGroup
{
    private static final long serialVersionUID = 1L;

    /** 分组id */
    private String id;

    /** 乱序 */
    @Excel(name = "乱序")
    private Integer itemRand;

    /** 随机 */
    @Excel(name = "随机")
    private Integer quRand;

    /** 试卷Id */
    @Excel(name = "试卷Id")
    private String paperId;

    /** 每题分数 */
    @Excel(name = "每题分数")
    private Long perScore;

    /** 分组题数量 */
    @Excel(name = "分组题数量")
    private Long quCount;

    /** 题目类型 */
    @Excel(name = "题目类型")
    private String quType;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 分组总题数 */
    @Excel(name = "分组总题数")
    private Integer totalScore;

    /** 创建时间 */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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
    public void setItemRand(Integer itemRand)
    {
        this.itemRand = itemRand;
    }

    public Integer getItemRand()
    {
        return itemRand;
    }
    public void setQuRand(Integer quRand)
    {
        this.quRand = quRand;
    }

    public Integer getQuRand()
    {
        return quRand;
    }
    public void setPaperId(String paperId)
    {
        this.paperId = paperId;
    }

    public String getPaperId()
    {
        return paperId;
    }
    public void setPerScore(Long perScore)
    {
        this.perScore = perScore;
    }

    public Long getPerScore()
    {
        return perScore;
    }
    public void setQuCount(Long quCount)
    {
        this.quCount = quCount;
    }

    public Long getQuCount()
    {
        return quCount;
    }
    public void setQuType(String quType)
    {
        this.quType = quType;
    }

    public String getQuType()
    {
        return quType;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTotalScore(Integer totalScore)
    {
        this.totalScore = totalScore;
    }

    public Integer getTotalScore()
    {
        return totalScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemRand", getItemRand())
            .append("quRand", getQuRand())
            .append("paperId", getPaperId())
            .append("perScore", getPerScore())
            .append("quCount", getQuCount())
            .append("quType", getQuType())
            .append("title", getTitle())
            .append("totalScore", getTotalScore())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

}
