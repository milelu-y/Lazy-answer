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
     *
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
}
