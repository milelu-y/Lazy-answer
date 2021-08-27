package com.lazy.vm.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import com.lazy.common.core.domain.entity.SysResource;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Size;

/**
 * 课程对象 edu_course
 *
 * @author lazy
 * @date 2021-08-03
 */

public class Course extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @IDField
    private String id;

    /**
     * 课程讲师ID
     */
    @Excel(name = "课程讲师ID")
    private String teacherId;

    /**
     * 课程专业ID
     */
    @Excel(name = "课程专业ID")
    private String subjectId;

    /**
     * 课程专业父级ID
     */
    @Excel(name = "课程专业父级ID")
    private String subjectParentId;

    /**
     * 作业ID
     */
    @Excel(name = "作业ID")
    private Long task;

    /**
     * 课程标题
     */
    @Excel(name = "课程标题")
    private String title;

    /**
     * 副标题
     */
    @Excel(name = "副标题")
    private String subtitle;

    /**
     * 课程销售价格，设置为0则可免费观看
     */
    @Excel(name = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    /**
     * 总课时
     */
    @Excel(name = "总课时")
    private Integer lessonNum;

    /**
     * 课程封面图片路径
     */
    @Excel(name = "课程封面图片路径")
    private String cover;

    /**
     * 销售数量
     */
    @Excel(name = "销售数量")
    private Integer buyCount;

    /**
     * 浏览数量
     */
    @Excel(name = "浏览数量")
    private Integer viewCount;

    /**
     * 乐观锁
     */
    @Excel(name = "乐观锁")
    private Long version;

    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    @Excel(name = "课程状态 Draft未发布  Normal已发布")
    private String status;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @Excel(name = "逻辑删除 1", readConverterExp = "t=rue")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @UpdateTime
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /**
     * 授课教师
     */
    private Teacher teacher;

    /**
     * 课程科目、类别
     */
    private List<Subject> subjects;
    /**
     * 封面资源
     */
    private SysResource resource;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setTask(Long task) {
        this.task = task;
    }

    public Long getTask() {
        return task;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 0, max = 50, message = "课程标题长度不能超过50个字符")
    public String getTitle() {
        return title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public SysResource getResource() {
        return resource;
    }

    public void setResource(SysResource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", subjectParentId='" + subjectParentId + '\'' +
                ", task=" + task +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", price=" + price +
                ", lessonNum=" + lessonNum +
                ", cover='" + cover + '\'' +
                ", buyCount=" + buyCount +
                ", viewCount=" + viewCount +
                ", version=" + version +
                ", status='" + status + '\'' +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", teacher=" + teacher +
                ", subjects=" + subjects +
                ", resource=" + resource +
                "} " + super.toString();
    }
}
