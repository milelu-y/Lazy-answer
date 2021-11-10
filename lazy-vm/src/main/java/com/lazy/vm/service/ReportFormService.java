package com.lazy.vm.service;

import com.lazy.vm.domain.ExamPaper;
import com.lazy.vm.domain.vo.UserTaskVo;

import java.util.List;
import java.util.Map;

public interface ReportFormService {


    /**
     * 根据用户获取所有作业的得分情况
     * @param userId
     * @return
     */
    List<ExamPaper> userListAchievement(String userId);


    /**
     * 根据用户及作业id获取该作业成绩
     * @param examPaper
     * @return
     */
    ExamPaper userAchievement(ExamPaper examPaper);


    /**
     * 计本次作业所选用户的总分、平均分
     * @param userTaskVo
     * @return
     */
    Map<String, Object> userExamAverage(UserTaskVo userTaskVo);



}
