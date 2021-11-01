package com.lazy.vm.service.impl;

import java.util.List;
import com.lazy.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.ExamMapper;
import com.lazy.vm.domain.Exam;
import com.lazy.vm.service.IExamService;

/**
 * 试卷添加Service业务层处理
 *
 * @author fgq
 * @date 2021-11-01
 */
@Service
public class ExamServiceImpl implements IExamService
{
    @Autowired
    private ExamMapper examMapper;

    /**
     * 查询试卷添加
     *
     * @param id 试卷添加ID
     * @return 试卷添加
     */
    @Override
    public Exam selectExamById(String id)
    {
        return examMapper.selectExamById(id);
    }

    /**
     * 查询试卷添加列表
     *
     * @param exam 试卷添加
     * @return 试卷添加
     */
    @Override
    public List<Exam> selectExamList(Exam exam)
    {
        return examMapper.selectExamList(exam);
    }

    /**
     * 新增试卷添加
     *
     * @param exam 试卷添加
     * @return 结果
     */
    @Override
    public int insertExam(Exam exam)
    {
        exam.setCreateTime(DateUtils.getNowDate());
        return examMapper.insertExam(exam);
    }

    /**
     * 修改试卷添加
     *
     * @param exam 试卷添加
     * @return 结果
     */
    @Override
    public int updateExam(Exam exam)
    {
        exam.setUpdateTime(DateUtils.getNowDate());
        return examMapper.updateExam(exam);
    }

    /**
     * 批量删除试卷添加
     *
     * @param ids 需要删除的试卷添加ID
     * @return 结果
     */
    @Override
    public int deleteExamByIds(String[] ids)
    {
        return examMapper.deleteExamByIds(ids);
    }

    /**
     * 删除试卷添加信息
     *
     * @param id 试卷添加ID
     * @return 结果
     */
    @Override
    public int deleteExamById(String id)
    {
        return examMapper.deleteExamById(id);
    }
}
