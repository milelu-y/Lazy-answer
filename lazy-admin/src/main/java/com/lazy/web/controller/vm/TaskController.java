package com.lazy.web.controller.vm;

import java.util.List;

import com.lazy.vm.domain.vo.TaskVo;
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
import com.lazy.vm.domain.Task;
import com.lazy.vm.service.ITaskService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 作业Controller
 *
 * @author lazy
 * @date 2021-10-26
 */
@RestController
@RequestMapping("/vm/task")
public class TaskController extends BaseController
{
    @Autowired
    private ITaskService taskService;

    /**
     * 查询作业列表
     */
    @PreAuthorize("@ss.hasPermi('vm:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(Task task)
    {
        startPage();
        List<Task> list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    /**
     * 导出作业列表
     */
    @PreAuthorize("@ss.hasPermi('vm:task:export')")
    @Log(title = "作业", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Task task)
    {
        List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        return util.exportExcel(list, "作业数据");
    }

    /**
     * 获取作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(taskService.selectTaskById(id));
    }

    /**
     * 新增作业
     */
    @PreAuthorize("@ss.hasPermi('vm:task:add')")
    @Log(title = "作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskVo taskVo)
    {
        return toAjax(taskService.insertTask(taskVo));
    }

    /**
     * 修改作业
     */
    @PreAuthorize("@ss.hasPermi('vm:task:edit')")
    @Log(title = "作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskVo taskVo)
    {
        return toAjax(taskService.updateTask(taskVo));
    }

    /**
     * 删除作业
     */
    @PreAuthorize("@ss.hasPermi('vm:task:remove')")
    @Log(title = "作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return taskService.deleteTaskByIds(ids);
    }
}
