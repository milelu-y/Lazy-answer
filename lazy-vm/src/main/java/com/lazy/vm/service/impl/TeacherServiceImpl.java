package com.lazy.vm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.TeacherMapper;
import com.lazy.vm.domain.Teacher;
import com.lazy.vm.service.ITeacherService;

/**
 * 讲师Service业务层处理
 *
 * @author lazy
 * @date 2021-08-03
 */
@Service
public class TeacherServiceImpl implements ITeacherService
{
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询讲师
     *
     * @param id 讲师ID
     * @return 讲师
     */
    @Override
    public Teacher selectTeacherById(String id)
    {
        return teacherMapper.selectTeacherById(id);
    }

    /**
     * 查询讲师列表
     *
     * @param teacher 讲师
     * @return 讲师
     */
    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        return teacherMapper.selectTeacherList(teacher);
    }

    /**
     * 新增讲师
     *
     * @param teacher 讲师
     * @return 结果
     */
    @Override
    public int insertTeacher(Teacher teacher)
    {
        return teacherMapper.insertTeacher(teacher);
    }

    /**
     * 修改讲师
     *
     * @param teacher 讲师
     * @return 结果
     */
    @Override
    public int updateTeacher(Teacher teacher)
    {
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 批量删除讲师
     *
     * @param ids 需要删除的讲师ID
     * @return 结果
     */
    @Override
    public int deleteTeacherByIds(String[] ids)
    {
        return teacherMapper.deleteTeacherByIds(ids);
    }

    /**
     * 删除讲师信息
     *
     * @param id 讲师ID
     * @return 结果
     */
    @Override
    public int deleteTeacherById(String id)
    {
        return teacherMapper.deleteTeacherById(id);
    }
}
