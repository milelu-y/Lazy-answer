package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Lazy
 * @date 2021/11/1 16:43
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FullAnswerVo {
    /**
     * 实际分数
     */
    private Integer actualScore;

    /**
     *题目（选项）
     */
    private List<AnswerOptionVo> answerList;

    /**
     * 有不有答对
     */
    private Boolean answered;

    /**
     * 标题
     */
    private String content;

    /**
     * 分组
     */
    private String groupId;


    private String id;

    /**
     * 试卷ID
     */
    private String paperId;

    /**
     * 题目ID
     */
    private String quId;

    /**
     * 题目分类
     */
    private String quType;

    /**
     * 题目分数
     */
    private String score;


    public Integer getActualScore() {
        return actualScore;
    }

    public void setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
    }

    public List<AnswerOptionVo> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerOptionVo> answerList) {
        this.answerList = answerList;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public String getQuType() {
        return quType;
    }

    public void setQuType(String quType) {
        this.quType = quType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
