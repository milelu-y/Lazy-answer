package com.lazy.web.controller.vm;


import com.lazy.common.core.domain.AjaxResult;
import com.lazy.vm.domain.ExamPaper;
import com.lazy.vm.domain.vo.UserTaskVo;
import com.lazy.vm.service.ReportFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * fgq
 *  2021/11/10
 */
@RestController
@RequestMapping("/vm/reportForm")
public class ReportFormController {

    @Autowired
    private ReportFormService reportFormService;

    /**
     * 统计某用户所有作业的得分
     * @param userId  用户id
     * @return
     */
    @GetMapping("/userListAchievement/{userId}")
    public AjaxResult userListAchievement(@PathVariable("userId") String userId){
        return AjaxResult.success(reportFormService.userListAchievement(userId));
    }

    /**
     * 用户的本次作业
     * @param examPaper
     * @return
     */
    @PostMapping("/userAchievement")
    public AjaxResult userAchievement(@RequestBody  ExamPaper examPaper){
        return AjaxResult.success(reportFormService.userAchievement(examPaper));
    }


    /**
     * 统计本次作业所选用户的总分、平均分
     * @param userTaskVo
     * @return
     */
    @GetMapping("/userExamAverage")
    public AjaxResult userExamAverage(@RequestBody UserTaskVo userTaskVo){
        return AjaxResult.success(reportFormService.userExamAverage(userTaskVo));
    }







}
