package com.lazy.vm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 讲师对象 edu_teacher
 *
 * @author lazy
 * @date 2021-08-03
 */
public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 讲师ID */
    private String id;

    /** 讲师姓名 */
    @Excel(name = "讲师姓名")
    private String name;

    /** 讲师简介 */
    @Excel(name = "讲师简介")
    private String intro;

    /** 讲师资历,一句话说明讲师 */
    @Excel(name = "讲师资历,一句话说明讲师")
    private String career;

    /** 头衔 1高级讲师 2首席讲师 */
    @Excel(name = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    /** 讲师头像 */
    @Excel(name = "讲师头像")
    private String avatar;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    @Excel(name = "逻辑删除 1", readConverterExp = "t=rue")
    private Integer isDeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getIntro()
    {
        return intro;
    }
    public void setCareer(String career)
    {
        this.career = career;
    }

    public String getCareer()
    {
        return career;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort()
    {
        return sort;
    }
    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
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
            .append("name", getName())
            .append("intro", getIntro())
            .append("career", getCareer())
            .append("level", getLevel())
            .append("avatar", getAvatar())
            .append("sort", getSort())
            .append("isDeleted", getIsDeleted())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
