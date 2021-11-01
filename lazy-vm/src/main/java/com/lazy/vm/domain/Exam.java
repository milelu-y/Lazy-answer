package com.lazy.vm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

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
    private Long totaltime;

    /** 限考次数 */
    @Excel(name = "限考次数")
    private String limitCount;

    /** 是否限制考试时间
 */
    @Excel(name = "是否限制考试时间
")
    private Integer limitTime;

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
    private Integer password;

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
    public void setTotaltime(Long totaltime)
    {
        this.totaltime = totaltime;
    }

    public Long getTotaltime()
    {
        return totaltime;
    }
    public void setLimitCount(String limitCount)
    {
        this.limitCount = limitCount;
    }

    public String getLimitCount()
    {
        return limitCount;
    }
    public void setLimitTime(Integer limitTime)
    {
        this.limitTime = limitTime;
    }

    public Integer getLimitTime()
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
    public void setPassword(Integer password)
    {
        this.password = password;
    }

    public Integer getPassword()
    {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paperId", getPaperId())
            .append("title", getTitle())
            .append("qualifyScore", getQualifyScore())
            .append("totaltime", getTotaltime())
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
