package com.lazy.vm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import com.lazy.common.core.domain.entity.SysResource;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 实验模块对象 edu_experiment
 *
 * @author fgq
 * @date 2021-12-13
 */
public class Experiment
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @IDField
    private String id;

    /** 课程id */
    @Excel(name = "课程id")
    private String courseId;

    /** 实验名称 */
    @Excel(name = "实验名称")
    private String title;

    /** 实验介绍 */
    @Excel(name = "实验介绍")
    private String intro;

    /** 实验地址 */
    @Excel(name = "实验地址")
    private String url;

    /** 流程 */
    @Excel(name = "流程")
    private String process;
    /** 创建时间 */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 实验大纲 */
    @Excel(name = "实验大纲")
    private String outline;


    private SysResource resource;

    /** 预留3 */
    @Excel(name = "预留3")
    private String reserveField3;

    /** 预留4 */
    @Excel(name = "预留4")
    private String reserveField4;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public String getCourseId()
    {
        return courseId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getIntro()
    {
        return intro;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }

    public String getProcess()
    {
        return process;
    }
    public void setOutline(String outline)
    {
        this.outline = outline;
    }

    public String getOutline()
    {
        return outline;
    }
    public void setReserveField3(String reserveField3)
    {
        this.reserveField3 = reserveField3;
    }

    public String getReserveField3()
    {
        return reserveField3;
    }
    public void setReserveField4(String reserveField4)
    {
        this.reserveField4 = reserveField4;
    }

    public String getReserveField4()
    {
        return reserveField4;
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

    public SysResource getResource() {
        return resource;
    }

    public void setResource(SysResource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseId", getCourseId())
                .append("title", getTitle())
                .append("intro", getIntro())
                .append("url", getUrl())
                .append("process", getProcess())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("outline", getOutline())
                .append("reserveField3", getReserveField3())
                .append("reserveField4", getReserveField4())
                .toString();
    }
}
