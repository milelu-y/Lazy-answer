package com.lazy.vm.service.impl;

import com.lazy.vm.domain.ExamPaper;
import com.lazy.vm.domain.vo.UserTaskVo;
import com.lazy.vm.mapper.ExamPaperMapper;
import com.lazy.vm.service.ReportFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportFormServiceImpl implements ReportFormService {


    @Autowired
   private ExamPaperMapper examPaperMapper;


    @Override
    public List<ExamPaper> userListAchievement(String userId) {
        List<ExamPaper> examPapers = examPaperMapper.selectUserExamPaperByUserId(userId);
        if (examPapers.size()>0){
            return  examPapers;
        }
        return null;
    }


    @Override
    public ExamPaper userAchievement(ExamPaper examPaper) {
        List<ExamPaper> examPapers = examPaperMapper.selectExamPaperList(examPaper);
        if (examPapers.size()>0){
            return  examPapers.get(0);
        }
        return null;
    }

    @Override
    public  Map<String, Object> userExamAverage(UserTaskVo userTaskVo) {

        ExamPaper examPaper = new ExamPaper();

        int count = 0;
        int a = userTaskVo.getUserId().size();
        for (int i = 0; i < a ; i++) {
            examPaper.setUserId(String.valueOf(userTaskVo.getUserId().get(i)));
            examPaper.setExamId(userTaskVo.getExamId());
            count =  count + Integer.valueOf(examPaperMapper.selectExamPaperList(examPaper).get(0).getUserScore());
        }
        double average  = count/a;
        Map<String, Object> data = new HashMap<>();
        data.put("count",count);
        data.put("average",average);
        return data;
    }


}
