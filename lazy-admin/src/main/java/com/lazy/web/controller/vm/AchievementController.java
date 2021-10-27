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
import com.lazy.vm.domain.Achievement;
import com.lazy.vm.service.IAchievementService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 学生考试成绩Controller
 *
 * @author fgq
 * @date 2021-10-27
 */
@RestController
@RequestMapping("/vm/Achievement")
public class AchievementController extends BaseController
{
    @Autowired
    private IAchievementService achievementService;

    /**
     * 查询学生考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Achievement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Achievement achievement)
    {
        startPage();
        List<Achievement> list = achievementService.selectAchievementList(achievement);
        return getDataTable(list);
    }

    /**
     * 导出学生考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Achievement:export')")
    @Log(title = "学生考试成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Achievement achievement)
    {
        List<Achievement> list = achievementService.selectAchievementList(achievement);
        ExcelUtil<Achievement> util = new ExcelUtil<Achievement>(Achievement.class);
        return util.exportExcel(list, "学生考试成绩数据");
    }

    /**
     * 获取学生考试成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:Achievement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(achievementService.selectAchievementById(id));
    }

    /**
     * 新增学生考试成绩
     */
    @PreAuthorize("@ss.hasPermi('vm:Achievement:add')")
    @Log(title = "学生考试成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Achievement achievement)
    {
        return toAjax(achievementService.insertAchievement(achievement));
    }

    /**
     * 修改学生考试成绩
     */
    @PreAuthorize("@ss.hasPermi('vm:Achievement:edit')")
    @Log(title = "学生考试成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Achievement achievement)
    {
        return toAjax(achievementService.updateAchievement(achievement));
    }

    /**
     * 删除学生考试成绩
     */
    @PreAuthorize("@ss.hasPermi('vm:Achievement:remove')")
    @Log(title = "学生考试成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(achievementService.deleteAchievementByIds(ids));
    }
}
