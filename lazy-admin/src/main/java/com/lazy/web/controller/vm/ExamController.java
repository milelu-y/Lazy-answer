package com.lazy.web.controller.vm;

import java.util.List;

import com.lazy.vm.domain.vo.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lazy.common.annotation.Log;
import com.lazy.common.core.controller.BaseController;
import com.lazy.common.core.domain.AjaxResult;
import com.lazy.common.enums.BusinessType;
import com.lazy.vm.domain.Exam;
import com.lazy.vm.service.IExamService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 试卷添加Controller
 *
 * @author fgq
 * @date 2021-11-01
 */
@RestController
@RequestMapping("/vm/exam")
public class ExamController extends BaseController
{
    @Autowired
    private IExamService examService;

    /**
     * 查询试卷添加列表
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:list')")
    @GetMapping("/list")
    public TableDataInfo list(Exam exam)
    {
        startPage();
        List<Exam> list = examService.selectExamList(exam);
        return getDataTable(list);
    }

    /**
     * 查询作业批阅信息
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:list')")
    @GetMapping("/review")
    public TableDataInfo reviewList(Exam exam){
        startPage();
        List<Exam> list =examService.selectExamReviewList(exam);
        return getDataTable(list);
    }

    /**
     * 用户查询试卷添加列表
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:list')")
    @GetMapping("/userExamList")
    public TableDataInfo userExamList(Exam exam)
    {
        startPage();
        List<Exam> list = examService.selectUserExamList(exam);
        return getDataTable(list);
    }

    /**
     * 查询试卷添加列表
     */
    @GetMapping("/listExamJoinPaper")
    public TableDataInfo listExamJoinPaper(ExamCourseDto examCourseDto)
    {
        startPage();
        List<Exam> list = examService.selectExamJoinPaperList(examCourseDto);
        return getDataTable(list);
    }

    /**
     * 导出试卷添加列表
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:export')")
    @Log(title = "试卷添加", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Exam exam)
    {
        List<Exam> list = examService.selectExamList(exam);
        ExcelUtil<Exam> util = new ExcelUtil<Exam>(Exam.class);
        return util.exportExcel(list, "试卷添加数据");
    }

    /**
     * 获取试卷添加详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(examService.selectExamById(id));
    }

    /**
     * 新增试卷添加
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:add')")
    @Log(title = "试卷添加", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamVo examVo)
    {
        return toAjax(examService.insertExam(examVo));
    }

    /**
     * 修改试卷添加
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:edit')")
    @Log(title = "试卷添加", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamVo exam)
    {
        return toAjax(examService.updateExam(exam));
    }

    /**
     * 删除试卷添加
     */
    @PreAuthorize("@ss.hasPermi('vm:exam:remove')")
    @Log(title = "试卷添加", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(examService.deleteExamByIds(ids));
    }


    @GetMapping("/userStat")
    public TableDataInfo userStat(Stat stat){
        startPage();
        List<Stat> list = examService.userStat(stat);
        return getDataTable(list);
    }

    @GetMapping("/errorStat")
    public TableDataInfo errorStat(ErrorStatVo error){
        startPage();
        List<ErrorStatVo> list = examService.errorStat(error);
        return getDataTable(list);
    }

    @GetMapping("/paging")
    public List<PaperAdaptedVo> paging(Stat stat){
        List<PaperAdaptedVo> list = examService.paging(stat);
        return list;
    }

    @GetMapping("/avgMaxMin")
    public List<AvgMaxMinStatVo> avgMaxMin(AvgMaxMinStatVo stat){
        List<AvgMaxMinStatVo> list = examService.avgMaxMin(stat);
        return list;
    }
}
