package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupAnswerVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String groupId;

    private String answerId;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("answerId", getAnswerId())
            .toString();
    }
}
