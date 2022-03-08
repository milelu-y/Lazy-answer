package com.lazy.vm.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazy.common.annotation.IDField;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Id;

/**
 * 答题中的题目选项对象 edu_exam_answer_options
 *
 * @author lazy
 * @date 2021-11-04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamAnswerOptions
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @IDField
    private String id;

    /** $column.columnComment */
    @Excel(name = "试卷id", readConverterExp = "$column.readConverterExp()")
    private String paperId;

    /** $column.columnComment */
    @Excel(name = "组", readConverterExp = "$column.readConverterExp()")
    private String groupId;

    /** $column.columnComment */
    @Excel(name = "内容", readConverterExp = "$column.readConverterExp()")
    private String content;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer pathScore;

    /** $column.columnComment */
    @Excel(name = "选中", readConverterExp = "$column.readConverterExp()")
    private Boolean checked;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String answerId;

    /** $column.columnComment */
    @Excel(name = "abc", readConverterExp = "$column.readConverterExp()")
    private String abc;

    /** $column.columnComment */
    @Excel(name = "答案", readConverterExp = "$column.readConverterExp()")
    private String answer;

    /** $column.columnComment */
    @Excel(name = "{题目ID", readConverterExp = "$column.readConverterExp()")
    private String quId;
    @Excel(name = "是否为正确答案")
    private Boolean isRight;
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
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return groupId;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setPathScore(Integer pathScore)
    {
        this.pathScore = pathScore;
    }

    public Integer getPathScore()
    {
        return pathScore;
    }
    public void setChecked(Boolean checked)
    {
        this.checked = checked;
    }

    public Boolean getChecked()
    {
        return checked;
    }
    public void setAnswerId(String answerId)
    {
        this.answerId = answerId;
    }

    public String getAnswerId()
    {
        return answerId;
    }
    public void setAbc(String abc)
    {
        this.abc = abc;
    }

    public String getAbc()
    {
        return abc;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnswer()
    {
        return answer;
    }
    public void setQuId(String quId)
    {
        this.quId = quId;
    }

    public String getQuId()
    {
        return quId;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean right) {
        isRight = right;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paperId", getPaperId())
            .append("groupId", getGroupId())
            .append("content", getContent())
            .append("pathScore", getPathScore())
            .append("checked", getChecked())
            .append("answerId", getAnswerId())
            .append("abc", getAbc())
            .append("answer", getAnswer())
            .append("quId", getQuId())
            .toString();
    }
}
