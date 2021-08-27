package com.lazy.web.controller.vm;

import java.util.List;
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
import com.lazy.vm.domain.Subject;
import com.lazy.vm.service.ISubjectService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 课程科目Controller
 *
 * @author lazy
 * @date 2021-08-04
 */
@RestController
@RequestMapping("/vm/subject")
public class SubjectController extends BaseController
{
    @Autowired
    private ISubjectService subjectService;

    /**
     * 查询课程科目列表
     */
    @PreAuthorize("@ss.hasPermi('vm:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(Subject subject)
    {
        startPage();
        List<Subject> list = subjectService.selectSubjectList(subject);
        return getDataTable(list);
    }

    /**
     * 导出课程科目列表
     */
    @PreAuthorize("@ss.hasPermi('vm:subject:export')")
    @Log(title = "课程科目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Subject subject)
    {
        List<Subject> list = subjectService.selectSubjectList(subject);
        ExcelUtil<Subject> util = new ExcelUtil<Subject>(Subject.class);
        return util.exportExcel(list, "课程科目数据");
    }

    /**
     * 获取课程科目详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:subject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(subjectService.selectSubjectById(id));
    }

    /**
     * 新增课程科目
     */
    @PreAuthorize("@ss.hasPermi('vm:subject:add')")
    @Log(title = "课程科目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Subject subject)
    {
        return toAjax(subjectService.insertSubject(subject));
    }

    /**
     * 修改课程科目
     */
    @PreAuthorize("@ss.hasPermi('vm:subject:edit')")
    @Log(title = "课程科目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Subject subject)
    {
        return toAjax(subjectService.updateSubject(subject));
    }

    /**
     * 删除课程科目
     */
    @PreAuthorize("@ss.hasPermi('vm:subject:remove')")
    @Log(title = "课程科目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(subjectService.deleteSubjectByIds(ids));
    }
}
