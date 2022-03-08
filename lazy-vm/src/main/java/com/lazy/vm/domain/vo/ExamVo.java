package com.lazy.vm.domain.vo;

import com.lazy.common.core.domain.entity.SysUser;

import java.util.Date;
import java.util.List;

/**
 * @author Lazy
 * @date 2021/11/2 9:53
 */
public class ExamVo {
    /**
     * 考试id
     */
    private String id;

    /**
     * 试卷ID
     */
    private String paperId;

    /**
     * 考试名称
     */
    private String title;

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

    /**
     * 返回结果类型
     */
    private Integer resultType;

    /**
     * 注意事项
     */
    private String content;

    /**
     * 考后感谢语
     */
    private String thanks;

    /**
     * 开放权限
     */
    private Integer openType;

    /**
     * 密码
     */
    private String password;

    private String status;

    private Integer totalScore;

    private Integer examUser;

    private Integer unreadPaper;

    private Date startTime;

    private Date endTime;

    private List<SysUser> personList;

    private List<Integer> deptIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysUser> getPersonList() {
        return personList;
    }

    public void setPersonList(List<SysUser> personList) {
        this.personList = personList;
    }

    public List<Integer> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Integer> deptIds) {
        this.deptIds = deptIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getExamUser() {
        return examUser;
    }

    public void setExamUser(Integer examUser) {
        this.examUser = examUser;
    }

    public Integer getUnreadPaper() {
        return unreadPaper;
    }

    public void setUnreadPaper(Integer unreadPaper) {
        this.unreadPaper = unreadPaper;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
