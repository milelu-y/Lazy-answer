package com.lazy.vm.domain.vo;

/**
 * @author Lazy
 * @date 2022/3/9 14:46
 */
public class ErrorStatVo {

    private String examId;

    private String content;

    private Integer type;

    private Integer count;

    private Integer errorCount;

    private String errorPro;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getErrorPro() {
        return errorPro;
    }

    public void setErrorPro(String errorPro) {
        this.errorPro = errorPro;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
