package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lazy.common.annotation.Excel;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerVo {

    /**
     * 题目id
     */
    private String id;

    /**
     * 题目类型：1（single: 单选题）、2（multiple: 多选题）、3 （fill: 填空题）、4（aq: 问答题）、5（judgment: 判断题）
     */
    private Integer type;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 答案
     */
    private String answer;

    /**
     * 选项
     */
    private String options;

    /**
     * 题库id
     */
    private String taskId;

    /**
     * 整题解析
     */
    private String analysis;

    /**
     * 章节
     */
    private String chapterId;

    /**
     * 考生答案
     */
    private int userAnswer;

    /**
     * 题目难度
     */
    private int level;

    private String score;

    /**
     * 试卷id
     */
    private String testPaperId;

    private List<AnswerOptionsVo> answerList;

    public String getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(String testPaperId) {
        this.testPaperId = testPaperId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public List<AnswerOptionsVo> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerOptionsVo> answerList) {
        this.answerList = answerList;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
