package com.lazy.vm.service;

import com.lazy.common.core.domain.AjaxResult;
import com.lazy.vm.domain.ExamPaper;
import com.lazy.vm.domain.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 试卷Service接口
 *
 * @author lazy
 * @date 2021-11-03
 */
public interface IExamPaperService
{
    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    public PaperAdaptedVo selectExamPaperById(String id);

    /**
     * 查询试卷列表
     *
     * @param examPaper 试卷
     * @return 试卷集合
     */
    public List<ExamPaper> selectExamPaperList(ExamPaper examPaper);

    /**
     * 新增试卷
     *
     * @param examPaper 试卷
     * @return 结果
     */
    public int insertExamPaper(ExamPaper examPaper);

    /**
     * 修改试卷
     *
     * @param examPaper 试卷
     * @return 结果
     */
    public int updateExamPaper(ExamPaper examPaper);

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的试卷ID
     * @return 结果
     */
    public int deleteExamPaperByIds(String[] ids);

    /**
     * 删除试卷信息
     *
     * @param id 试卷ID
     * @return 结果
     */
    public int deleteExamPaperById(String id);

    /**
     * 创建试卷，待优化，很慢
     * @param paperCreateVo
     * @return
     */
    AjaxResult createPaper(PaperCreateVo paperCreateVo);

    /**
     * 填充选项
     * @param fullAnswerVo
     * @return
     */
    Map fullAnswer(ExamAnswerVo fullAnswerVo);

    /**
     *
     * @param id
     * @return
     */
    AjaxResult saveExam(String id);

    /**
     * 查询题目详情
     * @param paperQuQueryDTO
     * @return
     */
    AjaxResult quDetail(PaperQuQueryDTO paperQuQueryDTO);

    /**
     * 返回结果，计算分数
     * @param id
     * @return
     */
    AjaxResult paperResult(String id);
}
