package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Group;

/**
 * 题目分组Mapper接口
 *
 * @author lazy
 * @date 2021-10-29
 */
public interface GroupMapper
{
    /**
     * 查询题目分组
     *
     * @param id 题目分组ID
     * @return 题目分组
     */
    public Group selectGroupById(String id);

    /**
     * 查询题目分组列表
     *
     * @param group 题目分组
     * @return 题目分组集合
     */
    public List<Group> selectGroupList(Group group);

    /**
     * 新增题目分组
     *
     * @param group 题目分组
     * @return 结果
     */
    public int insertGroup(Group group);

    /**
     * 修改题目分组
     *
     * @param group 题目分组
     * @return 结果
     */
    public int updateGroup(Group group);

    /**
     * 删除题目分组
     *
     * @param id 题目分组ID
     * @return 结果
     */
    public int deleteGroupById(String id);

    /**
     * 批量删除题目分组
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGroupByIds(String[] ids);

    /**
     * 通过试卷表查分组
     *
     * @param paperId 需要删除的数据ID
     * @return 结果
     */
    List<Group> selectGroupByPaperId(String paperId);

    /**
     * 根据试卷id删除
     * @param paperId
     */
    void deleteGroupByPaperId(String paperId);

    /**
     * 批量插入
     * @param groupTempList
     */
    void insertGroups(List<Group> groupTempList);
}
