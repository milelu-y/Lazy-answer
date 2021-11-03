package com.lazy.vm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lazy.common.core.domain.AjaxResult;
import com.lazy.common.enums.ExamState;
import com.lazy.common.exception.CustomException;
import com.lazy.common.utils.DateUtils;
import com.lazy.common.utils.SecurityUtils;
import com.lazy.common.utils.uuid.SnowflakeIdWorker;
import com.lazy.vm.domain.*;
import com.lazy.vm.domain.vo.AnswerVo;
import com.lazy.vm.domain.vo.PaperCreateVo;
import com.lazy.vm.mapper.*;
import com.lazy.vm.service.IExamPaperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷Service业务层处理
 *
 * @author lazy
 * @date 2021-11-03
 */
@Service
public class ExamPaperServiceImpl implements IExamPaperService {
    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Autowired
    private ExamGroupMapper examGroupMapper;

    @Autowired
    private ExamAnswerMapper examAnswerMapper;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private TestPaperMapper testPaperMapper;


    @Autowired
    private GroupAnswerMapper groupAnswerMapper;

    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    @Override
    public ExamPaper selectExamPaperById(String id) {
        return examPaperMapper.selectExamPaperById(id);
    }

    /**
     * 查询试卷列表
     *
     * @param examPaper 试卷
     * @return 试卷
     */
    @Override
    public List<ExamPaper> selectExamPaperList(ExamPaper examPaper) {
        return examPaperMapper.selectExamPaperList(examPaper);
    }

    /**
     * 新增试卷
     *
     * @param examPaper 试卷
     * @return 结果
     */
    @Override
    public int insertExamPaper(ExamPaper examPaper) {
        examPaper.setCreateTime(DateUtils.getNowDate());
        return examPaperMapper.insertExamPaper(examPaper);
    }

    /**
     * 修改试卷
     *
     * @param examPaper 试卷
     * @return 结果
     */
    @Override
    public int updateExamPaper(ExamPaper examPaper) {
        examPaper.setUpdateTime(DateUtils.getNowDate());
        return examPaperMapper.updateExamPaper(examPaper);
    }

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的试卷ID
     * @return 结果
     */
    @Override
    public int deleteExamPaperByIds(String[] ids) {
        return examPaperMapper.deleteExamPaperByIds(ids);
    }

    /**
     * 删除试卷信息
     *
     * @param id 试卷ID
     * @return 结果
     */
    @Override
    public int deleteExamPaperById(String id) {
        return examPaperMapper.deleteExamPaperById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult createPaper(PaperCreateVo paperCreateVo) {
        String examId = paperCreateVo.getExamId();
        Exam exam = examMapper.selectExamById(examId);
        if (exam == null) {
            throw new CustomException("作业不存在！", 400);
        }

        if (!ExamState.ENABLE.equals(exam.getStatus())) {
            throw new CustomException("作业状态不正确！", 400);
        }

        //判断密码是否正确
        if (!paperCreateVo.getPassword().equals(exam.getPassword())) {
            throw new CustomException("密码不正确！", 400);
        }

        //查找到试卷对应的题目
        String paperId = exam.getPaperId();
        TestPaper testPaper = testPaperMapper.selectTestPaperById(paperId);

        String examPaperId = SnowflakeIdWorker.getId();

        List<Group> groups = groupMapper.selectGroupByPaperId(paperId);

        List<ExamGroup> examGroups = new ArrayList<>();

        List<ExamAnswer> answerVos = new ArrayList<>();

        for (Group group : groups) {
            String groupId = SnowflakeIdWorker.getId();
            List<GroupAnswer> groupAnswers = groupAnswerMapper.selectGroupAnswerByGroupId(group.getId());
            ExamGroup examGroup = new ExamGroup();
            BeanUtils.copyProperties(group, examGroup);
            examGroup.setPaperId(examPaperId);
            examGroup.setId(groupId);
            examGroups.add(examGroup);
            for (GroupAnswer groupAnswer : groupAnswers) {
                String answerId = groupAnswer.getAnswerId();
                Answer answer = answerMapper.selectAnswerById(answerId);
                ExamAnswer examAnswer = new ExamAnswer();
                examAnswer.setQuId(answer.getTaskId());//设置题库ID
                examAnswer.setPaperId(examPaperId);//设置新的考试试卷ID
                examAnswer.setGroupId(groupId);
                if (examGroup.getPerScore() != null) {
                    examAnswer.setScore(examGroup.getPerScore().intValue());
                }
                BeanUtils.copyProperties(answer, examAnswer);
                answerVos.add(examAnswer);
            }
        }
        //TODO：创建试卷、把新的试卷管理到分组
        ExamPaper examPaper = new ExamPaper();
        BeanUtils.copyProperties(testPaper, examPaper);
        examPaper.setId(examPaperId);
        examPaper.setExamId(examId);
        examPaper.setUserId(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        //保存试卷
        examPaperMapper.insertExamPaper(examPaper);
        //保存分组
        for (ExamGroup examGroup : examGroups) {
            examGroupMapper.insertExamGroup(examGroup);
        }
        for (ExamAnswer answerVo : answerVos) {
            examAnswerMapper.insertExamAnswer(answerVo);
        }
        //保存考试题目
        ExamPaper examPaperResult = examPaperMapper.selectExamPaperById(examPaperId);
        Map<String, Object> data = new HashMap<>();
        data.put("id", examPaperResult.getId());
        return AjaxResult.success("操作成功", data);
    }
}
