package com.lazy.vm.domain.vo;

import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 题目分组对象 edu_group
 *
 * @author lazy
 * @date 2021-10-28
 */
public class GroupVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    private String id;

    /**
     * $column.columnComment
     */
    private Boolean itemRand;

    /**
     * 试卷Id
     */
    private String paperId;

    /**
     * 每题分数
     */
    private Long perScore;

    /**
     * 分组题数量
     */
    private Long quCount;

    /**
     * 题目类型
     */
    private String quType;


    private String totalScore;

    /**
     * 标题
     */
    private String title;

    private List<AnswerVo> quList;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setItemRand(Boolean itemRand) {
        this.itemRand = itemRand;
    }

    public Boolean getItemRand() {
        return itemRand;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPerScore(Long perScore) {
        this.perScore = perScore;
    }

    public Long getPerScore() {
        return perScore;
    }

    public void setQuCount(Long quCount) {
        this.quCount = quCount;
    }

    public Long getQuCount() {
        return quCount;
    }

    public void setQuType(String quType) {
        this.quType = quType;
    }

    public String getQuType() {
        return quType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<AnswerVo> getQuList() {
        return quList;
    }

    public void setQuList(List<AnswerVo> quList) {
        this.quList = quList;
    }
    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
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
