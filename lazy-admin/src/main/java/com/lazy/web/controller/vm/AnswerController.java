package com.lazy.web.controller.vm;

import java.util.List;

import com.lazy.vm.domain.vo.AnswerVo;
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
import com.lazy.vm.domain.Answer;
import com.lazy.vm.service.IAnswerService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 作业题目Controller
 *
 * @author fgq
 * @date 2021-10-26
 */
@RestController
@RequestMapping("/vm/Answer")
public class AnswerController extends BaseController
{
    @Autowired
    private IAnswerService answerService;

    /**
     * 查询作业题目列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Answer answer)
    {
        startPage();
        List<Answer> list = answerService.selectAnswerList(answer);
        return getDataTable(list);
    }

    /**
     * 导出作业题目列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:export')")
    @Log(title = "作业题目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Answer answer)
    {
        List<Answer> list = answerService.selectAnswerList(answer);
        ExcelUtil<Answer> util = new ExcelUtil<Answer>(Answer.class);
        return util.exportExcel(list, "作业题目数据");
    }

    /**
     * 获取作业题目详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(answerService.selectAnswerById(id));
    }

    /**
     * 新增作业题目
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:add')")
    @Log(title = "作业题目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Answer answer)
    {
        return toAjax(answerService.insertAnswer(answer));
    }

    /**
     * 修改作业题目
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:edit')")
    @Log(title = "作业题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Answer answer)
    {
        return toAjax(answerService.updateAnswer(answer));
    }

    /**
     * 删除作业题目
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:remove')")
    @Log(title = "作业题目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(answerService.deleteAnswerByIds(ids));
    }

    /**
     *考生答题分数
     * @param answerVo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vm:Answer:edit')")
    @Log(title = "考生答题分数", businessType = BusinessType.UPDATE)
    @PostMapping("/testScore")
    public AjaxResult testScore(@RequestBody List<AnswerVo> answerVo){
        return toAjax(answerService.testScore(answerVo));
    };
}
