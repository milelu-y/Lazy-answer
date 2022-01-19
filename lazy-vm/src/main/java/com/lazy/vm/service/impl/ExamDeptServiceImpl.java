package com.lazy.vm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.ExamDeptMapper;
import com.lazy.vm.domain.ExamDept;
import com.lazy.vm.service.IExamDeptService;

/**
 * 考试部门Service业务层处理
 *
 * @author lazy
 * @date 2022-01-06
 */
@Service
public class ExamDeptServiceImpl implements IExamDeptService
{
    @Autowired
    private ExamDeptMapper examDeptMapper;

    /**
     * 查询考试部门
     *
     * @param examId 考试部门ID
     * @return 考试部门
     */
//    @Override
//    public ExamDept selectExamDeptById(String examId)
//    {
//        return examDeptMapper.selectExamDeptById(examId);
//    }

    /**
     * 查询考试部门列表
     *
     * @param examDept 考试部门
     * @return 考试部门
     */
    @Override
    public List<ExamDept> selectExamDeptList(ExamDept examDept)
    {
        return examDeptMapper.selectExamDeptList(examDept);
    }

    /**
     * 新增考试部门
     *
     * @param examDept 考试部门
     * @return 结果
     */
    @Override
    public int insertExamDept(ExamDept examDept)
    {
        return examDeptMapper.insertExamDept(examDept);
    }

    /**
     * 修改考试部门
     *
     * @param examDept 考试部门
     * @return 结果
     */
    @Override
    public int updateExamDept(ExamDept examDept)
    {
        return examDeptMapper.updateExamDept(examDept);
    }

    /**
     * 批量删除考试部门
     *
     * @param examIds 需要删除的考试部门ID
     * @return 结果
     */
    @Override
    public int deleteExamDeptByIds(String[] examIds)
    {
        return examDeptMapper.deleteExamDeptByIds(examIds);
    }

    /**
     * 删除考试部门信息
     *
     * @param examId 考试部门ID
     * @return 结果
     */
    @Override
    public int deleteExamDeptById(String examId)
    {
        return examDeptMapper.deleteExamDeptById(examId);
    }
}
