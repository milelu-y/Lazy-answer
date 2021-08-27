package com.lazy.vm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.SubjectMapper;
import com.lazy.vm.domain.Subject;
import com.lazy.vm.service.ISubjectService;

/**
 * 课程科目Service业务层处理
 *
 * @author lazy
 * @date 2021-08-04
 */
@Service
public class SubjectServiceImpl implements ISubjectService
{
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 查询课程科目
     *
     * @param id 课程科目ID
     * @return 课程科目
     */
    @Override
    public Subject selectSubjectById(String id)
    {
        return subjectMapper.selectSubjectById(id);
    }

    /**
     * 查询课程科目列表
     *
     * @param subject 课程科目
     * @return 课程科目
     */
    @Override
    public List<Subject> selectSubjectList(Subject subject)
    {
        return subjectMapper.selectSubjectList(subject);
    }

    /**
     * 新增课程科目
     *
     * @param subject 课程科目
     * @return 结果
     */
    @Override
    public int insertSubject(Subject subject)
    {
        return subjectMapper.insertSubject(subject);
    }

    /**
     * 修改课程科目
     *
     * @param subject 课程科目
     * @return 结果
     */
    @Override
    public int updateSubject(Subject subject)
    {
        return subjectMapper.updateSubject(subject);
    }

    /**
     * 批量删除课程科目
     *
     * @param ids 需要删除的课程科目ID
     * @return 结果
     */
    @Override
    public int deleteSubjectByIds(String[] ids)
    {
        return subjectMapper.deleteSubjectByIds(ids);
    }

    /**
     * 删除课程科目信息
     *
     * @param id 课程科目ID
     * @return 结果
     */
    @Override
    public int deleteSubjectById(String id)
    {
        return subjectMapper.deleteSubjectById(id);
    }
}
