package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.ExamGroup;

/**
 * 题目分组Mapper接口
 *
 * @author lazy
 * @date 2021-11-03
 */
public interface ExamGroupMapper
{
    /**
     * 查询题目分组
     *
     * @param id 题目分组ID
     * @return 题目分组
     */
    public ExamGroup selectExamGroupById(String id);

    /**
     * 查询题目分组列表
     *
     * @param examGroup 题目分组
     * @return 题目分组集合
     */
    public List<ExamGroup> selectExamGroupList(ExamGroup examGroup);

    /**
     * 新增题目分组
     *
     * @param examGroup 题目分组
     * @return 结果
     */
    public int insertExamGroup(ExamGroup examGroup);

    /**
     * 修改题目分组
     *
     * @param examGroup 题目分组
     * @return 结果
     */
    public int updateExamGroup(ExamGroup examGroup);

    /**
     * 删除题目分组
     *
     * @param id 题目分组ID
     * @return 结果
     */
    public int deleteExamGroupById(String id);

    /**
     * 批量删除题目分组
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamGroupByIds(String[] ids);
}
