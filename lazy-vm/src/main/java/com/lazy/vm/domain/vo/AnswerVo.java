package com.lazy.vm.domain.vo;

import com.lazy.common.annotation.Excel;

public class AnswerVo {

    /** 题目id */
    private String id;

    /** 题目类型：1（single: 单选题）、2（multiple: 多选题）、3 （fill: 填空题）、4（aq: 问答题）、5（judgment: 判断题） */
    private Integer type;

    /** 题目内容 */
    private String content;

    /** 答案 */
    private String answer;

    /** 选项 */
    private String options;

    /** 题库id */
    private String taskId;


    /** 考生答案 */
    private String userAnswer;

    /** 题目难度 */
    private int level;


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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
