package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazy.common.annotation.CreateTime;
import com.lazy.common.annotation.Excel;
import com.lazy.common.annotation.IDField;
import com.lazy.common.annotation.UpdateTime;
import com.lazy.vm.domain.ExamAnswerOptions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 考试作业题目对象 edu_exam_answer
 *
 * @author lazy
 * @date 2021-11-03
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamAnswerVo {
    private static final long serialVersionUID = 1L;

    /**
     * 作业id
     */
    private String id;

    /**
     * 题库id
     */
    private String quId;

    /**
     * 题目类型：1（single: 单选题）、2（multiple: 多选题）、3 （fill: 填空题）、4（aq: 问答题）、5（judgment: 判断题）
     */
    private Integer type;

    /**
     * 题目难度
     */
    private Integer level;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 章节ID
     */
    private String chapterId;

    /**
     * 整题解析
     */
    private String analysis;

    /**
     * 答案
     */
    private Boolean answerd;

    /**
     * 选项
     */
    private String options;

    /**
     * 考试id
     */
    private String paperId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否标记
     */
    private Boolean mark;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 是否正确
     */
    private Boolean isRight;

    /**
     * 分组id
     */
    private String groupId;




    private List<ExamAnswerOptions> answerList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Boolean getAnswerd() {
        return answerd;
    }

    public void setAnswerd(Boolean answerd) {
        this.answerd = answerd;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean right) {
        isRight = right;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<ExamAnswerOptions> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ExamAnswerOptions> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("quId", getQuId())
                .append("type", getType())
                .append("level", getLevel())
                .append("content", getContent())
                .append("chapterId", getChapterId())
                .append("analysis", getAnalysis())
                .append("answerd", getAnswerd())
                .append("options", getOptions())
                .append("paperId", getPaperId())
                .append("sort", getSort())
                .append("mark", getMark())
                .append("score", getScore())
                .append("isRight", getIsRight())
                .append("groupId", getGroupId())
                .toString();
    }
}
