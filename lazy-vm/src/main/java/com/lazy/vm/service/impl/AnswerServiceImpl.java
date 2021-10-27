package com.lazy.vm.service.impl;

import java.util.List;
import com.lazy.common.utils.DateUtils;
import com.lazy.vm.domain.vo.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.AnswerMapper;
import com.lazy.vm.domain.Answer;
import com.lazy.vm.service.IAnswerService;

/**
 * 作业题目Service业务层处理
 *
 * @author fgq
 * @date 2021-10-26
 */
@Service
public class AnswerServiceImpl implements IAnswerService
{
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 查询作业题目
     *
     * @param id 作业题目ID
     * @return 作业题目
     */
    @Override
    public Answer selectAnswerById(Long id)
    {
        return answerMapper.selectAnswerById(id);
    }

    /**
     * 查询作业题目列表
     *
     * @param answer 作业题目
     * @return 作业题目
     */
    @Override
    public List<Answer> selectAnswerList(Answer answer)
    {
        return answerMapper.selectAnswerList(answer);
    }

    /**
     * 新增作业题目
     *
     * @param answer 作业题目
     * @return 结果
     */
    @Override
    public int insertAnswer(Answer answer)
    {
        answer.setCreateTime(DateUtils.getNowDate());
        return answerMapper.insertAnswer(answer);
    }

    /**
     * 修改作业题目
     *
     * @param answer 作业题目
     * @return 结果
     */
    @Override
    public int updateAnswer(Answer answer)
    {
        answer.setUpdateTime(DateUtils.getNowDate());
        return answerMapper.updateAnswer(answer);
    }

    /**
     * 批量删除作业题目
     *
     * @param ids 需要删除的作业题目ID
     * @return 结果
     */
    @Override
    public int deleteAnswerByIds(Long[] ids)
    {
        return answerMapper.deleteAnswerByIds(ids);
    }

    /**
     * 删除作业题目信息
     *
     * @param id 作业题目ID
     * @return 结果
     */
    @Override
    public int deleteAnswerById(Long id)
    {
        return answerMapper.deleteAnswerById(id);
    }


    @Override
    public int testScore(List<AnswerVo> answerVo) {


        return 0;
    }
}