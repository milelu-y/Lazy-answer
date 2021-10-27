package com.lazy.vm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 作业题目对象 edu_answer
 *
 * @author fgq
 * @date 2021-10-26
 */
public class Answer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业id */
    private Long id;

    /** 题目类型：1（single: 单选题）、2（multiple: 多选题）、3 （fill: 填空题）、4（aq: 问答题）、5（judgment: 判断题） */
    @Excel(name = "题目类型：1", readConverterExp = "s=ingle:,单=选题")
    private Integer type;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String content;

    /** 答案 */
    @Excel(name = "答案")
    private String answer;

    /** 题目难度 */
    @Excel(name = "题目难度")
    private Integer level;

    /** 选项 */
    @Excel(name = "选项")
    private String options;

    /** 章节ID */
    @Excel(name = "章节ID")
    private String chapterId;

    /** 整题解析 */
    @Excel(name = "整题解析")
    private String analysis;

    /** 题库id */
    @Excel(name = "题库id")
    private String taskId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnswer()
    {
        return answer;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getOptions()
    {
        return options;
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
    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getTaskId()
    {
        return taskId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("content", getContent())
                .append("answer", getAnswer())
                .append("level", getLevel())
                .append("options", getOptions())
                .append("createTime", getCreateTime())
                .append("chapterId", getChapterId())
                .append("updateTime", getUpdateTime())
                .append("analysis", getAnalysis())
                .append("taskId", getTaskId())
                .toString();
    }
}
