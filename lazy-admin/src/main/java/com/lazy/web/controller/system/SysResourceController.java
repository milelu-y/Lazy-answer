package com.lazy.web.controller.system;

import java.util.List;

import com.lazy.common.core.domain.entity.SysResource;
import com.lazy.vm.service.ISysResourceService;
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
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 资源Controller
 *
 * @author lazy
 * @date 2021-12-13
 */
@RestController
@RequestMapping("/system/resource")
public class SysResourceController extends BaseController
{
    @Autowired
    private ISysResourceService sysResourceService;

    /**
     * 查询资源列表
     */
    @PreAuthorize("@ss.hasPermi('system:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysResource sysResource)
    {
        startPage();
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        return getDataTable(list);
    }

    /**
     * 导出资源列表
     */
    @PreAuthorize("@ss.hasPermi('system:resource:export')")
    @Log(title = "资源", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysResource sysResource)
    {
        List<SysResource> list = sysResourceService.selectSysResourceList(sysResource);
        ExcelUtil<SysResource> util = new ExcelUtil<SysResource>(SysResource.class);
        return util.exportExcel(list, "资源数据");
    }

    /**
     * 获取资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysResourceService.selectSysResourceById(id));
    }

    /**
     * 新增资源
     */
    @PreAuthorize("@ss.hasPermi('system:resource:add')")
    @Log(title = "资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysResource sysResource)
    {
        return toAjax(sysResourceService.insertSysResource(sysResource));
    }

    /**
     * 修改资源
     */
    @PreAuthorize("@ss.hasPermi('system:resource:edit')")
    @Log(title = "资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysResource sysResource)
    {
        return toAjax(sysResourceService.updateSysResource(sysResource));
    }

    /**
     * 删除资源
     */
    @PreAuthorize("@ss.hasPermi('system:resource:remove')")
    @Log(title = "资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysResourceService.deleteSysResourceByIds(ids));
    }
}
