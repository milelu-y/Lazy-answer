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
import com.lazy.vm.domain.vo.*;
import com.lazy.vm.mapper.*;
import com.lazy.vm.service.IExamPaperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private AnswerOptionsMapper answerOptionsMapper;

    @Autowired
    private ExamAnswerOptionsMapper examAnswerOptionsMapper;

    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    @Override
    public PaperAdaptedVo selectExamPaperById(String id) {
        //查询考试组装数据呢
        if (!"".equals(id)) {
            PaperAdaptedVo paper = examPaperMapper.selectExamAndPaper(id);
            //查询组跟题目’
            String paperId = paper.getId();
            ExamGroup examGroup = new ExamGroup();
            examGroup.setPaperId(paperId);
            List<ExamGroup> examGroups = examGroupMapper.selectExamGroupList(examGroup);
            List<ExamGroupVo> examGroupVos = new ArrayList<>();
            for (ExamGroup group : examGroups) {

                String groupId = group.getId();
                ExamAnswer examAnswer = new ExamAnswer();
                examAnswer.setGroupId(groupId);

                List<ExamAnswer> examAnswers = examAnswerMapper.selectExamAnswerList(examAnswer);
                List<ExamAnswerVo> examAnswerVos = new ArrayList<>();

                ExamGroupVo groupVo = new ExamGroupVo();
                BeanUtils.copyProperties(group, groupVo);

                for (ExamAnswer answer : examAnswers) {
                    ExamAnswerVo answerVo = new ExamAnswerVo();
                    BeanUtils.copyProperties(answer, answerVo);
                    answerVo.setOptions(null);
                    System.out.println();
                    examAnswerVos.add(answerVo);
                }
                groupVo.setQuList(examAnswerVos);
                examGroupVos.add(groupVo);
            }
            System.out.println();
            paper.setGroupList(examGroupVos);
            return paper;
        }


        return null;
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
        if (exam.getOpenType() == 1) {

            if (!paperCreateVo.getPassword().equals(exam.getPassword())) {
                throw new CustomException("密码不正确！", 400);
            }
        }

        //判断次数
        String userId = SecurityUtils.getLoginUser().getUser().getUserId().toString();
        //查询该用户答题次数
        if (!"0".equals(exam.getLimitCount())) {
            int examCount = examPaperMapper.selectExamCount(examId, userId);
            if (examCount >= Integer.valueOf(exam.getLimitCount())) {
                throw new CustomException("做题次数已达上限！", 400);
            }
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
            examGroup.setTotalScore(group.getTotalScore());
            examGroups.add(examGroup);
            for (GroupAnswer groupAnswer : groupAnswers) {
                String answerId = groupAnswer.getAnswerId();
                Answer answer = answerMapper.selectAnswerById(answerId);
                ExamAnswer examAnswer = new ExamAnswer();
                examAnswer.setQuId(answer.getId());//设置题库ID
                examAnswer.setPaperId(examPaperId);//设置新的考试试卷ID
                examAnswer.setGroupId(groupId);
                if (examGroup.getPerScore() != null) {
                    examAnswer.setScore(examGroup.getPerScore().intValue());
                }
                examAnswer.setScore(Integer.valueOf(groupAnswer.getScore()));
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
            answerVo.setId(SnowflakeIdWorker.getId());
            List<AnswerOptions> answerOptionsList = answerOptionsMapper.selectAnswerOptionsByAnswerId(answerVo.getQuId());
            for (AnswerOptions answerOptions : answerOptionsList) {
                AnswerOptionsVo answerOptionsVo = new AnswerOptionsVo();
                BeanUtils.copyProperties(answerOptions, answerOptionsVo);
                ExamAnswerOptions examAnswerOptions = new ExamAnswerOptions();
                examAnswerOptions.setAnswerId(answerOptions.getId());
                examAnswerOptions.setPaperId(examPaperId);
                examAnswerOptions.setChecked(false);
                examAnswerOptions.setQuId(answerVo.getQuId());
                examAnswerOptions.setContent(answerOptionsVo.getContent());
                examAnswerOptionsMapper.insertExamAnswerOptions(examAnswerOptions);
            }
            answerVo.setIsRight(false);
            answerVo.setMark(false);
            examAnswerMapper.insertExamAnswer(answerVo);
        }
        //保存考试题目
        ExamPaper examPaperResult = examPaperMapper.selectExamPaperById(examPaperId);
        Map<String, Object> data = new HashMap<>();
        data.put("id", examPaperResult.getId());
        return AjaxResult.success("操作成功", data);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> fullAnswer(ExamAnswerVo fullAnswerVo) {
        List<ExamAnswerOptions> answerList = fullAnswerVo.getAnswerList();
        boolean flag = false;
        for (ExamAnswerOptions examAnswerOptions : answerList) {
            if (examAnswerOptions.getChecked()) {
                flag = true;
            }
            examAnswerOptionsMapper.updateExamAnswerOptions(examAnswerOptions);
        }

        ExamAnswer examAnswer = new ExamAnswer();

        BeanUtils.copyProperties(fullAnswerVo, examAnswer);
        if (examAnswer.getAnswer()!=null&&!"".equals(examAnswer.getAnswer())) {//判断是否回答
            flag = true;
        }
        examAnswerMapper.updateExamAnswer(examAnswer);
        Map<String, Object> map = new HashMap<>();
        map.put("fill", flag);
        return map;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult saveExam(String id) {
        if (!id.equals("")) {
            ExamAnswer examAnswer = new ExamAnswer();
            examAnswer.setPaperId(id);
            List<ExamAnswer> examAnswers = examAnswerMapper.selectExamAnswerList(examAnswer);
            int unt = 0;
            for (ExamAnswer ex : examAnswers) {

                ExamAnswerVo examAnswerVo = new ExamAnswerVo();

                BeanUtils.copyProperties(ex, examAnswerVo);

                List<AnswerOptions> answerOptionsList = answerOptionsMapper.selectAnswerOptionsByAnswerId(ex.getQuId());

                List<ExamAnswerOptions> answerOptions =
                        examAnswerOptionsMapper.selectExamAnswerOptionsByQuId(examAnswerVo.getPaperId(), examAnswerVo.getQuId());

                for (ExamAnswerOptions answerOption : answerOptions) {

                    for (AnswerOptions option : answerOptionsList) {
                        if (answerOption.getAnswerId().equals(option.getId())) {
                            if (answerOption.getChecked() && option.getIsRight()) {
                                ex.setIsRight(true);
                                Integer score = ex.getScore();
                                unt = unt + score;
                                break;
                            }
                        }
                    }
                }
                examAnswerMapper.updateExamAnswer(ex);
            }


            Map<String, Object> data = new HashMap<>();
            ExamPaper examPaper = examPaperMapper.selectExamPaperById(id);
            examPaper.setUserScore(unt);
            examPaper.setStatus("1");
            examPaperMapper.updateExamPaper(examPaper);

            Exam exam = examMapper.selectExamById(examPaper.getExamId());

            if (exam.getResultType() == 0) {
                data.put("resultType", exam.getThanks());
            } else if (exam.getResultType() == 1) {
                data.put("resultType", exam.getThanks());
                data.put("result", unt);
            }
            return AjaxResult.success("操作成功", data);
        }
        return AjaxResult.error();
    }

    @Override
    public AjaxResult quDetail(@RequestBody PaperQuQueryDTO paperQu) {
        String paperId = paperQu.getPaperId();

        String quId = paperQu.getQuId();

        Answer answer = answerMapper.selectAnswerById(quId);


        ExamAnswerVo examAnswerVo = examAnswerMapper.selectExamAnswerByQuId(paperId, quId);
        String temp = examAnswerVo.getAnswer();
        String answerVoId = examAnswerVo.getId();
        BeanUtils.copyProperties(answer, examAnswerVo);
        examAnswerVo.setId(answerVoId);
        examAnswerVo.setQuId(quId);
        examAnswerVo.setPaperId(paperId);
        examAnswerVo.setAnswer(temp);
        List<ExamAnswerOptions> examAnswerOptions = examAnswerOptionsMapper.selectExamAnswerOptionsByQuId(paperId, quId);
        examAnswerVo.setAnswerList(examAnswerOptions);
        return AjaxResult.success(examAnswerVo);
    }

    @Override
    public AjaxResult paperResult(String id) {
        PaperAdaptedVo paper = examPaperMapper.selectExamAndPaper(id);
        if (paper != null) {
            return AjaxResult.success(paper);
        }
        return AjaxResult.error();
    }
}
