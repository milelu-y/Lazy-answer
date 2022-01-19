package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazy.vm.domain.Answer;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Long totalScore;

    /**
     * 0 系统出题   1  老师选题
     */
    private Long type;

    /**
     * 试卷类别（0：理工类   1：文工类）
     */
    private String category;

    /**
     * 选择老师
     */
    private String userId;

    /**
     * 预留
     */
    private List<GroupVo> groupList;

    /**
     * 预留
     */
    private Integer quCount;

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


    private List<Answer> answers;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtCreate;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

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

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuCount() {
        return quCount;
    }

    public void setQuCount(Integer quCount) {
        this.quCount = quCount;
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

    public List<GroupVo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupVo> groupList) {
        this.groupList = groupList;
    }
}
