package com.lazy.vm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import com.lazy.common.enums.ExamState;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 试卷添加对象 edu_exam
 *
 * @author fgq
 * @date 2021-11-01
 */
public class Exam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试id */
    @IDField
    private String id;

    /** 试卷ID */
    @Excel(name = "试卷ID")
    private String paperId;

    /** 考试名称 */
    @Excel(name = "考试名称")
    private String title;

    /** 及格分数 */
    @Excel(name = "及格分数")
    private Long qualifyScore;

    /** 考试时长 */
    @Excel(name = "考试时长")
    private Long totalTime;

    /** 考试总分 */
    @Excel(name = "考试总分")
    private Integer totalScore;

    /** 限考次数 */
    @Excel(name = "限考次数")
    private String limitCount;

    /** 是否限制考试时间*/
    @Excel(name = "是否限制考试时间")
    private Boolean limitTime;

    /** 返回结果类型 */
    @Excel(name = "返回结果类型")
    private Integer resultType;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String content;

    /** 考后感谢语 */
    @Excel(name = "考后感谢语")
    private String thanks;

    /** 开放权限 */
    @Excel(name = "开放权限")
    private Integer openType;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

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
    public void setPaperId(String paperId)
    {
        this.paperId = paperId;
    }

    public String getPaperId()
    {
        return paperId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setQualifyScore(Long qualifyScore)
    {
        this.qualifyScore = qualifyScore;
    }

    public Long getQualifyScore()
    {
        return qualifyScore;
    }
    public void setTotalTime(Long totalTime)
    {
        this.totalTime = totalTime;
    }

    public Long getTotalTime()
    {
        return totalTime;
    }
    public void setLimitCount(String limitCount)
    {
        this.limitCount = limitCount;
    }

    public String getLimitCount()
    {
        return limitCount;
    }
    public void setLimitTime(Boolean limitTime)
    {
        this.limitTime = limitTime;
    }

    public Boolean getLimitTime()
    {
        return limitTime;
    }
    public void setResultType(Integer resultType)
    {
        this.resultType = resultType;
    }

    public Integer getResultType()
    {
        return resultType;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setThanks(String thanks)
    {
        this.thanks = thanks;
    }

    public String getThanks()
    {
        return thanks;
    }
    public void setOpenType(Integer openType)
    {
        this.openType = openType;
    }

    public Integer getOpenType()
    {
        return openType;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
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
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 是否结束
     * @return
     */
    public Integer getStatus(){

        if(this.limitTime!=null && this.limitTime){

//            if(System.currentTimeMillis() < startTime.getTime() ){
//                return ExamState.READY_START;
//            }
//
//            if(System.currentTimeMillis() > endTime.getTime()){
//                return ExamState.OVERDUE;
//            }
//
//            if(System.currentTimeMillis() > startTime.getTime()
//                    && System.currentTimeMillis() < endTime.getTime()
//                    && !ExamState.DISABLED.equals(this.status)){
//                return ExamState.ENABLE;
//            }

        }

        return this.status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paperId", getPaperId())
            .append("title", getTitle())
            .append("qualifyScore", getQualifyScore())
            .append("totalTime", getTotalTime())
            .append("limitCount", getLimitCount())
            .append("limitTime", getLimitTime())
            .append("resultType", getResultType())
            .append("content", getContent())
            .append("thanks", getThanks())
            .append("openType", getOpenType())
            .append("password", getPassword())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
