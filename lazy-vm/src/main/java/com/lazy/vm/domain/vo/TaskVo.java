package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Lazy
 * @date 2021/10/26 15:38
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskVo {
    /**
     * 作业id
     */
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 作业标题
     */
    private String title;

    /**
     * 题目类型
     */
    private String type;

    /**
     * 单选题数量
     */
    private Integer singleCount;

    /**
     * 多选题数量
     */
    private Integer multipleCount;

    /**
     * 判断题数量
     */
    private Integer judgment;

    /**
     * 填空题数量
     */
    private Integer fillCount;

    /**
     * 问答题数量
     */
    private Integer aqCount;

    private String remark;


    private List<ChapterVo> chapters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(Integer singleCount) {
        this.singleCount = singleCount;
    }

    public Integer getMultipleCount() {
        return multipleCount;
    }

    public void setMultipleCount(Integer multipleCount) {
        this.multipleCount = multipleCount;
    }

    public Integer getJudgment() {
        return judgment;
    }

    public void setJudgment(Integer judgment) {
        this.judgment = judgment;
    }

    public Integer getFillCount() {
        return fillCount;
    }

    public void setFillCount(Integer fillCount) {
        this.fillCount = fillCount;
    }

    public Integer getAqCount() {
        return aqCount;
    }

    public void setAqCount(Integer aqCount) {
        this.aqCount = aqCount;
    }

    public List<ChapterVo> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterVo> chapters) {
        this.chapters = chapters;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
