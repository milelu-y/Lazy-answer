package com.lazy.vm.mapper;

import com.lazy.vm.domain.ExamPaper;

import java.util.List;

/**
 * 试卷Mapper接口
 *
 * @author lazy
 * @date 2021-11-03
 */
public interface ExamPaperMapper
{
    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    public ExamPaper selectExamPaperById(String id);

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
     * 删除试卷
     *
     * @param id 试卷ID
     * @return 结果
     */
    public int deleteExamPaperById(String id);

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamPaperByIds(String[] ids);
}
