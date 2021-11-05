package com.lazy.vm.domain.vo;

import com.lazy.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Lazy
 * @date 2021/11/4 17:19
 */
public class AnswerOptionsVo {
    /** id */
    private String id;

    /** 是否正确 */
    private Boolean isRight;

    /** 答案内容 */
    private String content;

    /** 答案分析 */
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("isRight", getIsRight())
                .append("content", getContent())
                .append("analysis", getAnalysis())
                .toString();
    }
}
