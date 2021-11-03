package com.lazy.web.controller.vm;

import java.awt.print.Paper;
import java.util.List;

import com.lazy.vm.domain.vo.AnswerVo;
import com.lazy.vm.domain.vo.PaperCreateVo;
import com.lazy.vm.domain.vo.TestPaperVo;
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
import com.lazy.vm.domain.TestPaper;
import com.lazy.vm.service.ITestPaperService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 试卷Controller
 *
 * @author fgq
 * @date 2021-10-27
 */
@RestController
@RequestMapping("/vm/testPaper")
public class TestPaperController extends BaseController
{
    @Autowired
    private ITestPaperService testPaperService;

    /**
     * 查询试卷列表
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestPaper testPaper)
    {
        startPage();
        List<TestPaper> list = testPaperService.selectTestPaperList(testPaper);
        return getDataTable(list);
    }

    /**
     * 导出试卷列表
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:export')")
    @Log(title = "试卷", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TestPaper testPaper)
    {
        List<TestPaper> list = testPaperService.selectTestPaperList(testPaper);
        ExcelUtil<TestPaper> util = new ExcelUtil<TestPaper>(TestPaper.class);
        return util.exportExcel(list, "试卷数据");
    }

    /**
     * 获取试卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(testPaperService.selectTestPaperById(id));
    }

    /**
     * 新增试卷
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:add')")
    @Log(title = "试卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestPaperVo testPaper)
    {
        return toAjax(testPaperService.insertTestPaper(testPaper));
    }

    /**
     * 修改试卷
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:edit')")
    @Log(title = "试卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestPaperVo testPaperVo)
    {
        return toAjax(testPaperService.updateTestPaper(testPaperVo));
    }

    /**
     * 删除试卷
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:remove')")
    @Log(title = "试卷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(testPaperService.deleteTestPaperByIds(ids));
    }


    /**
     *添加试卷
     * @param testPaperVo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vm:testPaper:add')")
    @Log(title = "添加试卷", businessType = BusinessType.UPDATE)
    @PostMapping("/addTestPaper")
    public AjaxResult testScore(@RequestBody TestPaperVo testPaperVo){
        return toAjax(testPaperService.addTestPaper(testPaperVo));
    }

}
