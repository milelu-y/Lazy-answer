package com.lazy.vm.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Lazy
 * @date 2021/10/28 18:22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerOptionVo {
    /**
     * 真题解析
     */
    private String analysis;
    private String answerId;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否正确
     */
    private Boolean isRight;
    private Boolean checked;
    private String paperId;
    private Integer pathScore;
    private String quId;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

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
}
