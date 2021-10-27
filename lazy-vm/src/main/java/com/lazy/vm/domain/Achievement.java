package com.lazy.vm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 学生考试成绩对象 edu_achievement
 *
 * @author fgq
 * @date 2021-10-27
 */
public class Achievement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩id */
    private String id;

    /** 章节id */
    @Excel(name = "章节id")
    private String chapterId;

    /** 学生id */
    @Excel(name = "学生id")
    private String uesrId;

    /** 成绩 */
    @Excel(name = "成绩")
    private String achievement;

    /** 添加日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setChapterId(String chapterId)
    {
        this.chapterId = chapterId;
    }

    public String getChapterId()
    {
        return chapterId;
    }
    public void setUesrId(String uesrId)
    {
        this.uesrId = uesrId;
    }

    public String getUesrId()
    {
        return uesrId;
    }
    public void setAchievement(String achievement)
    {
        this.achievement = achievement;
    }

    public String getAchievement()
    {
        return achievement;
    }
    public void setGmtCreate(Date gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate()
    {
        return gmtCreate;
    }
    public void setGmtModified(Date gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified()
    {
        return gmtModified;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chapterId", getChapterId())
            .append("uesrId", getUesrId())
            .append("achievement", getAchievement())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
