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
//    @IDField
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
    private Long totalScore;

    /**
     * 0 系统出题   1  老师选题
     */
    @Excel(name = "0 系统出题   1  老师选题")
    private Long type;

    /**
     * 试卷类别（0：理工类   1：文工类）
     */
    @Excel(name = "试卷类别", readConverterExp = "0=：理工类,1=：文工类")
    private String category;

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
     * 总题数
     */
    @Excel(name = "总题数")
    private Integer quCount;

    /**
     * 预留
     */
    @Excel(name = "客观分数")
    private Integer objScore;

    /**
     * 预留
     */
    @Excel(name = "主观分数")
    private Integer subjScore;

    /**
     * 预留
     */
    @Excel(name = "是否包含简答题")
    private Boolean hasSaq;

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

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
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

    public Integer getQuCount() {
        return quCount;
    }

    public void setQuCount(Integer quCount) {
        this.quCount = quCount;
    }

    public Integer getObjScore() {
        return objScore;
    }

    public void setObjScore(Integer objScore) {
        this.objScore = objScore;
    }

    public Integer getSubjScore() {
        return subjScore;
    }

    public void setSubjScore(Integer subjScore) {
        this.subjScore = subjScore;
    }

    public Boolean getHasSaq() {
        return hasSaq;
    }

    public void setHasSaq(Boolean hasSaq) {
        this.hasSaq = hasSaq;
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
                .append("fraction", getTotalScore())
                .append("type", getType())
                .append("category", getCategory())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .append("userId", getUserId())
                .append("quCount", getQuCount())
                .append("reserveField5", getReserveField5())
                .toString();
    }

}
