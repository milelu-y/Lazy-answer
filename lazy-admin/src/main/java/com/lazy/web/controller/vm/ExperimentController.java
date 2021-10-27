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
import com.lazy.vm.domain.Experiment;
import com.lazy.vm.service.IExperimentService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 实验模块Controller
 *
 * @author fgq
 * @date 2021-10-27
 */
@RestController
@RequestMapping("/vm/Experiment")
public class ExperimentController extends BaseController
{
    @Autowired
    private IExperimentService experimentService;

    /**
     * 查询实验模块列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Experiment experiment)
    {
        startPage();
        List<Experiment> list = experimentService.selectExperimentList(experiment);
        return getDataTable(list);
    }

    /**
     * 导出实验模块列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:export')")
    @Log(title = "实验模块", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Experiment experiment)
    {
        List<Experiment> list = experimentService.selectExperimentList(experiment);
        ExcelUtil<Experiment> util = new ExcelUtil<Experiment>(Experiment.class);
        return util.exportExcel(list, "实验模块数据");
    }

    /**
     * 获取实验模块详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(experimentService.selectExperimentById(id));
    }

    /**
     * 新增实验模块
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:add')")
    @Log(title = "实验模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Experiment experiment)
    {
        return toAjax(experimentService.insertExperiment(experiment));
    }

    /**
     * 修改实验模块
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:edit')")
    @Log(title = "实验模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Experiment experiment)
    {
        return toAjax(experimentService.updateExperiment(experiment));
    }

    /**
     * 删除实验模块
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:remove')")
    @Log(title = "实验模块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(experimentService.deleteExperimentByIds(ids));
    }
}
