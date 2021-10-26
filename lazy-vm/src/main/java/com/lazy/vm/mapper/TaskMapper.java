package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Task;

/**
 * 作业Mapper接口
 *
 * @author lazy
 * @date 2021-10-26
 */
public interface TaskMapper
{
    /**
     * 查询作业
     *
     * @param id 作业ID
     * @return 作业
     */
    public Task selectTaskById(Long id);

    /**
     * 查询作业列表
     *
     * @param task 作业
     * @return 作业集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增作业
     *
     * @param task 作业
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改作业
     *
     * @param task 作业
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除作业
     *
     * @param id 作业ID
     * @return 结果
     */
    public int deleteTaskById(Long id);

    /**
     * 批量删除作业
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);
}
