package com.lazy.vm.mapper;

import com.lazy.vm.domain.GroupAnswer;

import java.util.List;

/**
 * 分组题目Mapper接口
 *
 * @author lazy
 * @date 2021-10-29
 */
public interface GroupAnswerMapper
{
    /**
     * 查询分组题目
     *
     * @param groupId 分组题目ID
     * @return 分组题目
     */
    public GroupAnswer selectGroupAnswerById(String groupId);

    /**
     * 查询分组题目列表
     *
     * @param groupAnswer 分组题目
     * @return 分组题目集合
     */
    public List<GroupAnswer> selectGroupAnswerList(GroupAnswer groupAnswer);

    /**
     * 新增分组题目
     *
     * @param groupAnswer 分组题目
     * @return 结果
     */
    public int insertGroupAnswer(GroupAnswer groupAnswer);

    /**
     * 修改分组题目
     *
     * @param groupAnswer 分组题目
     * @return 结果
     */
    public int updateGroupAnswer(GroupAnswer groupAnswer);

    /**
     * 删除分组题目
     *
     * @param groupId 分组题目ID
     * @return 结果
     */
    public int deleteGroupAnswerById(String groupId);

    /**
     * 批量删除分组题目
     *
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGroupAnswerByIds(String[] groupIds);

    List<GroupAnswer> selectGroupAnswerByGroupId(String id);

    Integer selectScoreByAnswerId(String quId);

    /**
     * 批量插入
     * @param groupAnswers
     */
    void insertGroupAnswers(List<GroupAnswer> groupAnswers);
}
