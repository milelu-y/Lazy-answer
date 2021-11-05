package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.ExamAnswerOptions;
import org.apache.ibatis.annotations.Param;

/**
 * 答题中的题目选项Mapper接口
 *
 * @author lazy
 * @date 2021-11-04
 */
public interface ExamAnswerOptionsMapper
{
    /**
     * 查询答题中的题目选项
     *
     * @param id 答题中的题目选项ID
     * @return 答题中的题目选项
     */
    public ExamAnswerOptions selectExamAnswerOptionsById(String id);

    /**
     * 查询答题中的题目选项列表
     *
     * @param examAnswerOptions 答题中的题目选项
     * @return 答题中的题目选项集合
     */
    public List<ExamAnswerOptions> selectExamAnswerOptionsList(ExamAnswerOptions examAnswerOptions);

    /**
     * 新增答题中的题目选项
     *
     * @param examAnswerOptions 答题中的题目选项
     * @return 结果
     */
    public int insertExamAnswerOptions(ExamAnswerOptions examAnswerOptions);

    /**
     * 修改答题中的题目选项
     *
     * @param examAnswerOptions 答题中的题目选项
     * @return 结果
     */
    public int updateExamAnswerOptions(ExamAnswerOptions examAnswerOptions);

    /**
     * 删除答题中的题目选项
     *
     * @param id 答题中的题目选项ID
     * @return 结果
     */
    public int deleteExamAnswerOptionsById(String id);

    /**
     * 批量删除答题中的题目选项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamAnswerOptionsByIds(String[] ids);

    List<ExamAnswerOptions> selectExamAnswerOptionsByQuId(@Param("paperId") String paperId, @Param("quId") String quId);
}
