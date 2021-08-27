package com.lazy.vm.service;

import java.util.List;
import com.lazy.vm.domain.Teacher;

/**
 * 讲师Service接口
 *
 * @author lazy
 * @date 2021-08-03
 */
public interface ITeacherService
{
    /**
     * 查询讲师
     *
     * @param id 讲师ID
     * @return 讲师
     */
    public Teacher selectTeacherById(String id);

    /**
     * 查询讲师列表
     *
     * @param teacher 讲师
     * @return 讲师集合
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 新增讲师
     *
     * @param teacher 讲师
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改讲师
     *
     * @param teacher 讲师
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除讲师
     *
     * @param ids 需要删除的讲师ID
     * @return 结果
     */
    public int deleteTeacherByIds(String[] ids);

    /**
     * 删除讲师信息
     *
     * @param id 讲师ID
     * @return 结果
     */
    public int deleteTeacherById(String id);
}
