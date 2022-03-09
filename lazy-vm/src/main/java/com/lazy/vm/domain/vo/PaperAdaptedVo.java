package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * @author Lazy
 * @date 2021/11/3 19:10
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaperAdaptedVo {

    private String id;

    private String userId;
    private String nickName;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 分数
     */
    private Long totalScore;


    /**
     * 试卷总题数
     */
    private Integer quCount;

    /**
     * 考试分数
     */
    private Integer userScore;

    /**
     * 考试id
     */
    private String examId;

    /**
     * 及格分数
     */
    private Long qualifyScore;

    /**
     * 考试时长
     */
    private Long totalTime;

    /**
     * 限考次数
     */
    private String limitCount;

    /**
     * 是否限制考试时间
     */
    private Boolean limitTime;


    private Integer status;

    private String thanks;

    private Date createTime;

    private List<ExamGroupVo> groupList;

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Integer getQuCount() {
        return quCount;
    }

    public void setQuCount(Integer quCount) {
        this.quCount = quCount;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public Long getQualifyScore() {
        return qualifyScore;
    }

    public void setQualifyScore(Long qualifyScore) {
        this.qualifyScore = qualifyScore;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(String limitCount) {
        this.limitCount = limitCount;
    }

    public Boolean getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Boolean limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ExamGroupVo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<ExamGroupVo> groupList) {
        this.groupList = groupList;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
