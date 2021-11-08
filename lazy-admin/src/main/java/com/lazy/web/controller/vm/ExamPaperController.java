package com.lazy.web.controller.vm;

import java.util.List;
import java.util.Map;

import com.lazy.vm.domain.vo.ExamAnswerVo;
import com.lazy.vm.domain.vo.FullAnswerVo;
import com.lazy.vm.domain.vo.PaperCreateVo;
import com.lazy.vm.domain.vo.PaperQuQueryDTO;
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
import com.lazy.vm.domain.ExamPaper;
import com.lazy.vm.service.IExamPaperService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 试卷Controller
 *
 * @author lazy
 * @date 2021-11-03
 */
@RestController
@RequestMapping("/vm/examPaper")
public class ExamPaperController extends BaseController
{
    @Autowired
    private IExamPaperService examPaperService;

    /**
     * 查询试卷列表
     */
    @PreAuthorize("@ss.hasPermi('vm:examPaper:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamPaper examPaper)
    {
        startPage();
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        return getDataTable(list);
    }

    /**
     * 导出试卷列表
     */
    @PreAuthorize("@ss.hasPermi('vm:examPaper:export')")
    @Log(title = "试卷", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamPaper examPaper)
    {
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        ExcelUtil<ExamPaper> util = new ExcelUtil<ExamPaper>(ExamPaper.class);
        return util.exportExcel(list, "试卷数据");
    }

    /**
     * 获取试卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:examPaper:query')")
    @GetMapping(value = "/paperDetail/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(examPaperService.selectExamPaperById(id));
    }

    /**
     * 新增考试试卷
     */
    @PreAuthorize("@ss.hasPermi('vm:examPaper:add')")
    @Log(title = "新增考试试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamPaper examPaper)
    {
        return toAjax(examPaperService.insertExamPaper(examPaper));
    }

    /**
     * 修改试卷
     */
    @PreAuthorize("@ss.hasPermi('vm:examPaper:edit')")
    @Log(title = "新增考试试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamPaper examPaper)
    {
        return toAjax(examPaperService.updateExamPaper(examPaper));
    }

    /**
     * 删除试卷
     */
    @PreAuthorize("@ss.hasPermi('vm:examPaper:remove')")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(examPaperService.deleteExamPaperByIds(ids));
    }

    /**
     * 创建试卷
     *
     * @param paperCreateVo
     * @return
     */
    @Log(title = "创建试卷", businessType = BusinessType.INSERT)
    @PostMapping("/createPaper")
    public AjaxResult createPaper(@RequestBody PaperCreateVo paperCreateVo) {
        return examPaperService.createPaper(paperCreateVo);
    }

    /**
     *保存考生答题情况
     * @param fullAnswerVo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:edit')")
    @Log(title = "保存考生答题情况", businessType = BusinessType.UPDATE)
    @PostMapping("/fullAnswer")
    public AjaxResult fullAnswer(@RequestBody ExamAnswerVo fullAnswerVo){
        return AjaxResult.success(examPaperService.fullAnswer(fullAnswerVo));
    }

    /**
     *学生考试分数
     * @param map
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:edit')")
    @Log(title = "学生考试分数", businessType = BusinessType.UPDATE)
    @PostMapping("/saveExam")
    public AjaxResult saveExam(@RequestBody Map<String,String> map){
        return AjaxResult.success(examPaperService.saveExam(map.get("id")));
    }

    /**
     * 获取题目详情
     *
     * @param paperQuQueryDTO
     * @return
     */
    @PostMapping("/quDetail")
    public AjaxResult createPaper(@RequestBody PaperQuQueryDTO paperQuQueryDTO) {
        return examPaperService.quDetail(paperQuQueryDTO);
    }

    @GetMapping("/paperResult/{id}")
    public AjaxResult paperResult(@PathVariable("id") String id) {
        return examPaperService.paperResult(id);
    }
}
