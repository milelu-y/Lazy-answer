package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.AnswerOptions;

/**
 * 答案选项Mapper接口
 *
 * @author lazy
 * @date 2021-11-04
 */
public interface AnswerOptionsMapper
{
    /**
     * 查询答案选项
     *
     * @param id 答案选项ID
     * @return 答案选项
     */
    public AnswerOptions selectAnswerOptionsById(String id);

    /**
     * 查询答案选项列表
     *
     * @param answerOptions 答案选项
     * @return 答案选项集合
     */
    public List<AnswerOptions> selectAnswerOptionsList(AnswerOptions answerOptions);

    /**
     * 新增答案选项
     *
     * @param answerOptions 答案选项
     * @return 结果
     */
    public int insertAnswerOptions(AnswerOptions answerOptions);

    /**
     * 修改答案选项
     *
     * @param answerOptions 答案选项
     * @return 结果
     */
    public int updateAnswerOptions(AnswerOptions answerOptions);

    /**
     * 删除答案选项
     *
     * @param id 答案选项ID
     * @return 结果
     */
    public int deleteAnswerOptionsById(String id);

    /**
     * 批量删除答案选项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnswerOptionsByIds(String[] ids);

    List<AnswerOptions> selectAnswerOptionsByAnswerId(String answerId);
}
