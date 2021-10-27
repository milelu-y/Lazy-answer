package com.lazy.vm.domain.vo;

public class AnswerTypeVo {

    /** 答案是否正确 */
    private String isRight;

    /** 题目 */
    private String content;

    /** 答案 */
    private String analysis;


    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
