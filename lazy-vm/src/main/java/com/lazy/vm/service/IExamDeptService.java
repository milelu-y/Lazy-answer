package com.lazy.vm.service;

import java.util.List;
import com.lazy.vm.domain.ExamDept;

/**
 * 考试部门Service接口
 *
 * @author lazy
 * @date 2022-01-06
 */
public interface IExamDeptService
{
    /**
     * 查询考试部门
     *
     * @param examId 考试部门ID
     * @return 考试部门
     */
//    public ExamDept selectExamDeptById(String examId);

    /**
     * 查询考试部门列表
     *
     * @param examDept 考试部门
     * @return 考试部门集合
     */
    public List<ExamDept> selectExamDeptList(ExamDept examDept);

    /**
     * 新增考试部门
     *
     * @param examDept 考试部门
     * @return 结果
     */
    public int insertExamDept(ExamDept examDept);

    /**
     * 修改考试部门
     *
     * @param examDept 考试部门
     * @return 结果
     */
    public int updateExamDept(ExamDept examDept);

    /**
     * 批量删除考试部门
     *
     * @param examIds 需要删除的考试部门ID
     * @return 结果
     */
    public int deleteExamDeptByIds(String[] examIds);

    /**
     * 删除考试部门信息
     *
     * @param examId 考试部门ID
     * @return 结果
     */
    public int deleteExamDeptById(String examId);
}
