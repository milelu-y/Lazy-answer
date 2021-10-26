package com.lazy.vm.service;

import java.util.List;
import com.lazy.vm.domain.Task;
import com.lazy.vm.domain.vo.TaskVo;

/**
 * 作业Service接口
 *
 * @author lazy
 * @date 2021-10-26
 */
public interface ITaskService
{
    /**
     * 查询作业
     *
     * @param id 作业ID
     * @return 作业
     */
    public TaskVo selectTaskById(String id);

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
    public int insertTask(TaskVo task);

    /**
     * 修改作业
     *
     * @param task 作业
     * @return 结果
     */
    public int updateTask(TaskVo taskVo);

    /**
     * 批量删除作业
     *
     * @param ids 需要删除的作业ID
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 删除作业信息
     *
     * @param id 作业ID
     * @return 结果
     */
    public int deleteTaskById(Long id);
}
