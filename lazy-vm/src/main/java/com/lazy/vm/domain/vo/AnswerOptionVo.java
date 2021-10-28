package com.lazy.vm.domain.vo;

/**
 * @author Lazy
 * @date 2021/10/28 18:22
 */
public class AnswerOptionVo {
    private String analysis;
    private String answerId;
    private String content;
    private Boolean isRight;
    private Integer pathScore;

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public Integer getPathScore() {
        return pathScore;
    }

    public void setPathScore(Integer pathScore) {
        this.pathScore = pathScore;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
