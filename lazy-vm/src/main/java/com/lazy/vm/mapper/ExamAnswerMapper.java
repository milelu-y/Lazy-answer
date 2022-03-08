package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.ExamAnswer;
import com.lazy.vm.domain.vo.ExamAnswerVo;
import org.apache.ibatis.annotations.Param;

/**
 * 考试作业题目Mapper接口
 *
 * @author lazy
 * @date 2021-11-03
 */
public interface ExamAnswerMapper
{
    /**
     * 查询考试作业题目
     *
     * @param id 考试作业题目ID
     * @return 考试作业题目
     */
    public ExamAnswer selectExamAnswerById(String id);

    /**
     * 查询考试作业题目列表
     *
     * @param examAnswer 考试作业题目
     * @return 考试作业题目集合
     */
    public List<ExamAnswer> selectExamAnswerList(ExamAnswer examAnswer);

    /**
     * 新增考试作业题目
     *
     * @param examAnswer 考试作业题目
     * @return 结果
     */
    public int insertExamAnswer(ExamAnswer examAnswer);

    /**
     * 修改考试作业题目
     *
     * @param examAnswer 考试作业题目
     * @return 结果
     */
    public int updateExamAnswer(ExamAnswer examAnswer);

    /**
     * 删除考试作业题目
     *
     * @param id 考试作业题目ID
     * @return 结果
     */
    public int deleteExamAnswerById(String id);

    /**
     * 批量删除考试作业题目
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamAnswerByIds(String[] ids);

    ExamAnswerVo selectExamAnswerByQuId(@Param("paperId") String paperId ,@Param("quId") String quId);

    /**
     * 根据题库id删除
     * @param toString
     */
    void deleteExamAnswerByQuId(String quId);

    int getExamAnswerByQuId(String quId);

    /**
     * 根据分组id查询已经答题的题目
     * @param groupId
     * @return
     */
    List<ExamAnswerVo> selectExamAnswerByGroupId(@Param("paperId") String paperId,@Param("groupId") String groupId);
    List<ExamAnswerVo> selectExamAnswerByGroupIdType(@Param("paperId") String paperId,@Param("groupId") String groupId);

    /**
     * 批量修改答题后的题目
     * @param quList
     * @return
     */
    int updateBatchExamAnswer(List<ExamAnswerVo> quList);
}
