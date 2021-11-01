package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Exam;

/**
 * 试卷添加Mapper接口
 * 
 * @author fgq
 * @date 2021-11-01
 */
public interface ExamMapper 
{
    /**
     * 查询试卷添加
     * 
     * @param id 试卷添加ID
     * @return 试卷添加
     */
    public Exam selectExamById(String id);

    /**
     * 查询试卷添加列表
     * 
     * @param exam 试卷添加
     * @return 试卷添加集合
     */
    public List<Exam> selectExamList(Exam exam);

    /**
     * 新增试卷添加
     * 
     * @param exam 试卷添加
     * @return 结果
     */
    public int insertExam(Exam exam);

    /**
     * 修改试卷添加
     * 
     * @param exam 试卷添加
     * @return 结果
     */
    public int updateExam(Exam exam);

    /**
     * 删除试卷添加
     * 
     * @param id 试卷添加ID
     * @return 结果
     */
    public int deleteExamById(String id);

    /**
     * 批量删除试卷添加
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamByIds(String[] ids);
}
