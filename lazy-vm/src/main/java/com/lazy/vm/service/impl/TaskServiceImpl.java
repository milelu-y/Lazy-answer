package com.lazy.vm.service.impl;

import java.util.List;
import com.lazy.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.TaskMapper;
import com.lazy.vm.domain.Task;
import com.lazy.vm.service.ITaskService;

/**
 * 作业Service业务层处理
 *
 * @author lazy
 * @date 2021-10-26
 */
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询作业
     *
     * @param id 作业ID
     * @return 作业
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询作业列表
     *
     * @param task 作业
     * @return 作业
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增作业
     *
     * @param task 作业
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask(task);
    }

    /**
     * 修改作业
     *
     * @param task 作业
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除作业
     *
     * @param ids 需要删除的作业ID
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除作业信息
     *
     * @param id 作业ID
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }
}
