package com.lazy.vm.domain;

import com.lazy.common.annotation.IDField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 题目分组对象 edu_group
 *
 * @author lazy
 * @date 2021-10-28
 */
public class Group extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分组id */
//    @IDField
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer itemRand;

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
    private String totalScore;

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

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemRand", getItemRand())
            .append("paperId", getPaperId())
            .append("perScore", getPerScore())
            .append("quCount", getQuCount())
            .append("quType", getQuType())
            .append("title", getTitle())
            .toString();
    }
}
