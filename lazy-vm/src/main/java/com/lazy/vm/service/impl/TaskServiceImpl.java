package com.lazy.vm.service.impl;

import java.util.List;

import com.lazy.common.utils.DateUtils;
import com.lazy.common.utils.uuid.SnowflakeIdWorker;
import com.lazy.vm.domain.Chapter;
import com.lazy.vm.domain.vo.ChapterVo;
import com.lazy.vm.domain.vo.TaskVo;
import com.lazy.vm.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.TaskMapper;
import com.lazy.vm.domain.Task;
import com.lazy.vm.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作业Service业务层处理
 *
 * @author lazy
 * @date 2021-10-26
 */
@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询作业
     *
     * @param id 作业ID
     * @return 作业
     */
    @Override
    public TaskVo selectTaskById(String id) {
        List<ChapterVo> chapterVos = chapterMapper.selectChapterByTaskId(id);
        Task task = taskMapper.selectTaskById(id);
        TaskVo taskVo = new TaskVo();
        BeanUtils.copyProperties(task, taskVo);
        taskVo.setChapters(chapterVos);
        return taskVo;
    }

    /**
     * 查询作业列表
     *
     * @param task 作业
     * @return 作业
     */
    @Override
    public List<Task> selectTaskList(Task task) {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增作业
     *
     * @param task 作业
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTask(TaskVo taskVo) {
        //生成题库ID
        String quId = SnowflakeIdWorker.getId();
        taskVo.setId(quId);
        System.out.println(quId);
        for (ChapterVo chapterVo : taskVo.getChapters()) {
            chapterVo.setTaskId(quId);
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(chapterVo, chapter);
            //TODO:插入章节
            chapterMapper.insertChapter(chapter);
            System.out.println(chapter);
        }
        Task task = new Task();
        task.setCourseId(task.getType());
        BeanUtils.copyProperties(taskVo, task);
        System.out.println(task);
        return taskMapper.insertTask(task);
    }

    /**
     * 修改作业
     *
     * @param taskVo 作业
     * @return 结果
     */
    @Override
    public int updateTask(TaskVo taskVo) {
        List<ChapterVo> chapters = taskVo.getChapters();
        for (ChapterVo chapter : chapters) {
            if ("".equals(chapter.getId())) {
                    //TODO:空的，插入
            }else {
                //修改
            }
        }
        //return taskMapper.updateTask();
        return 0;
    }

    /**
     * 批量删除作业
     *
     * @param ids 需要删除的作业ID
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids) {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除作业信息
     *
     * @param id 作业ID
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id) {
        return taskMapper.deleteTaskById(id);
    }
}
