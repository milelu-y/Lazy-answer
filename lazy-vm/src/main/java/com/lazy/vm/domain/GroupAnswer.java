package com.lazy.vm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 分组题目对象 edu_group_answer
 *
 * @author lazy
 * @date 2021-10-29
 */
public class GroupAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String groupId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String answerId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String score;

    public GroupAnswer(String groupId, String answerId,String score) {
        this.groupId = groupId;
        this.answerId=answerId;
        this.score = score;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return groupId;
    }
    public void setAnswerId(String answerId)
    {
        this.answerId = answerId;
    }

    public String getAnswerId()
    {
        return answerId;
    }
    public void setScore(String score)
    {
        this.score = score;
    }

    public String getScore()
    {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("groupId", getGroupId())
                .append("answerId", getAnswerId())
                .append("score", getScore())
                .toString();
    }
}
