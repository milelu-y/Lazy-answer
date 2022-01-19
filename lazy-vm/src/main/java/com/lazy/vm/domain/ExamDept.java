package com.lazy.vm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 考试部门对象 edu_exam_dept
 *
 * @author lazy
 * @date 2022-01-06
 */
public class ExamDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String examId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer deptId;

    public void setExamId(String examId)
    {
        this.examId = examId;
    }

    public String getExamId()
    {
        return examId;
    }
    public void setDeptId(Integer deptId)
    {
        this.deptId = deptId;
    }

    public Integer getDeptId()
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examId", getExamId())
            .append("deptId", getDeptId())
            .toString();
    }
}
