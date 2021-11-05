package com.lazy.vm.domain;

import com.lazy.common.annotation.IDField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 edu_answer_options
 *
 * @author lazy
 * @date 2021-11-04
 */
public class AnswerOptions
{
    private static final long serialVersionUID = 1L;

    /** id */
    @IDField
    private String id;

    /** 是否正确 */
    @Excel(name = "题目Id")
    private String quId;

    /** 是否正确 */
    @Excel(name = "是否正确")
    private Boolean isRight;

    /** 答案内容 */
    @Excel(name = "答案内容")
    private String content;

    /** 答案分析 */
    @Excel(name = "答案分析")
    private String analysis;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setIsRight(Boolean isRight)
    {
        this.isRight = isRight;
    }

    public Boolean getIsRight()
    {
        return isRight;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setAnalysis(String analysis)
    {
        this.analysis = analysis;
    }

    public String getAnalysis()
    {
        return analysis;
    }

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("isRight", getIsRight())
            .append("content", getContent())
            .append("analysis", getAnalysis())
            .toString();
    }
}
