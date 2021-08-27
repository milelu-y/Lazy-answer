package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Subject;

/**
 * 课程科目Mapper接口
 *
 * @author lazy
 * @date 2021-08-04
 */
public interface SubjectMapper
{
    /**
     * 查询课程科目
     *
     * @param id 课程科目ID
     * @return 课程科目
     */
    public Subject selectSubjectById(String id);

    /**
     * 查询课程科目列表
     *
     * @param subject 课程科目
     * @return 课程科目集合
     */
    public List<Subject> selectSubjectList(Subject subject);

    /**
     * 新增课程科目
     *
     * @param subject 课程科目
     * @return 结果
     */
    public int insertSubject(Subject subject);

    /**
     * 修改课程科目
     *
     * @param subject 课程科目
     * @return 结果
     */
    public int updateSubject(Subject subject);

    /**
     * 删除课程科目
     *
     * @param id 课程科目ID
     * @return 结果
     */
    public int deleteSubjectById(String id);

    /**
     * 批量删除课程科目
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSubjectByIds(String[] ids);
}
