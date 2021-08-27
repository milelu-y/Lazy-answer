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
import com.lazy.vm.domain.Teacher;
import com.lazy.vm.service.ITeacherService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 讲师Controller
 *
 * @author lazy
 * @date 2021-08-03
 */
@RestController
@RequestMapping("/vm/teacher")
public class TeacherController extends BaseController
{
    @Autowired
    private ITeacherService teacherService;

    /**
     * 查询讲师列表
     */
    @PreAuthorize("@ss.hasPermi('vm:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(Teacher teacher)
    {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }

    /**
     * 导出讲师列表
     */
    @PreAuthorize("@ss.hasPermi('vm:teacher:export')")
    @Log(title = "讲师", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Teacher teacher)
    {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        return util.exportExcel(list, "讲师数据");
    }

    /**
     * 获取讲师详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:teacher:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(teacherService.selectTeacherById(id));
    }

    /**
     * 新增讲师
     */
    @PreAuthorize("@ss.hasPermi('vm:teacher:add')")
    @Log(title = "讲师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改讲师
     */
    @PreAuthorize("@ss.hasPermi('vm:teacher:edit')")
    @Log(title = "讲师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.updateTeacher(teacher));
    }

    /**
     * 删除讲师
     */
    @PreAuthorize("@ss.hasPermi('vm:teacher:remove')")
    @Log(title = "讲师", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(teacherService.deleteTeacherByIds(ids));
    }
}
