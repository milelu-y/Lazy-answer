package com.lazy.vm.service;

import java.util.List;
import com.lazy.vm.domain.Answer;
import com.lazy.vm.domain.vo.AnswerVo;

/**
 * 作业题目Service接口
 *
 * @author fgq
 * @date 2021-10-26
 */
public interface IAnswerService
{
    /**
     * 查询作业题目
     *
     * @param id 作业题目ID
     * @return 作业题目
     */
    public AnswerVo selectAnswerById(String id);

    /**
     * 查询作业题目列表
     *
     * @param answer 作业题目
     * @return 作业题目集合
     */
    public List<Answer> selectAnswerList(Answer answer);

    /**
     * 新增作业题目
     *
     * @param answer 作业题目
     * @return 结果
     */
    public int insertAnswer(AnswerVo answerVo);

    /**
     * 修改作业题目
     *
     * @param answer 作业题目
     * @return 结果
     */
    public int updateAnswer(Answer answer);

    /**
     * 批量删除作业题目
     *
     * @param ids 需要删除的作业题目ID
     * @return 结果
     */
    public int deleteAnswerByIds(Long[] ids);

    /**
     * 删除作业题目信息
     *
     * @param id 作业题目ID
     * @return 结果
     */
    public int deleteAnswerById(Long id);


    /**
     *考生答题分数
     * @param answerVo
     * @return
     */
    public int testScore(List<AnswerVo> answerVo);

    public List<Answer> getTestPaperAnswer(String testPaperId);
}
