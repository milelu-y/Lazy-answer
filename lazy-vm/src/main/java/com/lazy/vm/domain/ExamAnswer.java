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
 * 考试作业题目对象 edu_exam_answer
 *
 * @author lazy
 * @date 2021-11-03
 */
public class ExamAnswer
{
    private static final long serialVersionUID = 1L;

    /** 作业id */
    @IDField
    private String id;

    /** 题库id */
    @Excel(name = "题库id")
    private String quId;

    /** 题目类型：1（single: 单选题）、2（multiple: 多选题）、3 （fill: 填空题）、4（aq: 问答题）、5（judgment: 判断题） */
    @Excel(name = "题目类型：1", readConverterExp = "s=ingle:,单=选题")
    private Integer type;

    /** 题目难度 */
    @Excel(name = "题目难度")
    private Integer level;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String content;

    /** 章节ID */
    @Excel(name = "章节ID")
    private String chapterId;

    /** 整题解析 */
    @Excel(name = "整题解析")
    private String analysis;

    /** 答案 */
    @Excel(name = "答案")
    private Integer answerd;

    /** 选项 */
    @Excel(name = "选项")
    private String options;

    /** 考试id */
    @Excel(name = "考试id")
    private String paperId;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 是否标记 */
    @Excel(name = "是否标记")
    private Integer mark;

    /** 分数 */
    @Excel(name = "分数")
    private Integer score;

    /** 是否正确 */
    @Excel(name = "是否正确")
    private Integer isRight;

    /** 分组id */
    @Excel(name = "分组id")
    private String groupId;
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
    public void setQuId(String quId)
    {
        this.quId = quId;
    }

    public String getQuId()
    {
        return quId;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setChapterId(String chapterId)
    {
        this.chapterId = chapterId;
    }

    public String getChapterId()
    {
        return chapterId;
    }
    public void setAnalysis(String analysis)
    {
        this.analysis = analysis;
    }

    public String getAnalysis()
    {
        return analysis;
    }
    public void setAnswerd(Integer answerd)
    {
        this.answerd = answerd;
    }

    public Integer getAnswerd()
    {
        return answerd;
    }
    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getOptions()
    {
        return options;
    }
    public void setPaperId(String paperId)
    {
        this.paperId = paperId;
    }

    public String getPaperId()
    {
        return paperId;
    }
    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort()
    {
        return sort;
    }
    public void setMark(Integer mark)
    {
        this.mark = mark;
    }

    public Integer getMark()
    {
        return mark;
    }
    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Integer getScore()
    {
        return score;
    }
    public void setIsRight(Integer isRight)
    {
        this.isRight = isRight;
    }

    public Integer getIsRight()
    {
        return isRight;
    }
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return groupId;
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
            .append("quId", getQuId())
            .append("type", getType())
            .append("level", getLevel())
            .append("content", getContent())
            .append("chapterId", getChapterId())
            .append("analysis", getAnalysis())
            .append("answerd", getAnswerd())
            .append("options", getOptions())
            .append("paperId", getPaperId())
            .append("sort", getSort())
            .append("mark", getMark())
            .append("score", getScore())
            .append("isRight", getIsRight())
            .append("groupId", getGroupId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
