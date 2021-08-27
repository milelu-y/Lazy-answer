package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Course;

/**
 * 课程Mapper接口
 *
 * @author lazy
 * @date 2021-08-03
 */
public interface CourseMapper
{
    /**
     * 查询课程
     *
     * @param id 课程ID
     * @return 课程
     */
    public Course selectCourseById(String id);

    /**
     * 查询课程列表
     *
     * @param course 课程
     * @return 课程集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增课程
     *
     * @param course 课程
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程
     *
     * @param course 课程
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 删除课程
     *
     * @param id 课程ID
     * @return 结果
     */
    public int deleteCourseById(String id);

    /**
     * 批量删除课程
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseByIds(String[] ids);
}
