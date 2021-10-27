package com.lazy.vm.service;

import java.util.List;
import com.lazy.vm.domain.TestPaper;
import com.lazy.vm.domain.vo.TestPaperVo;

/**
 * 试卷Service接口
 * 
 * @author fgq
 * @date 2021-10-27
 */
public interface ITestPaperService 
{
    /**
     * 查询试卷
     * 
     * @param id 试卷ID
     * @return 试卷
     */
    public TestPaper selectTestPaperById(String id);

    /**
     * 查询试卷列表
     * 
     * @param testPaper 试卷
     * @return 试卷集合
     */
    public List<TestPaper> selectTestPaperList(TestPaper testPaper);

    /**
     * 新增试卷
     * 
     * @param testPaper 试卷
     * @return 结果
     */
    public int insertTestPaper(TestPaper testPaper);

    /**
     * 修改试卷
     * 
     * @param testPaper 试卷
     * @return 结果
     */
    public int updateTestPaper(TestPaper testPaper);

    /**
     * 批量删除试卷
     * 
     * @param ids 需要删除的试卷ID
     * @return 结果
     */
    public int deleteTestPaperByIds(String[] ids);

    /**
     * 删除试卷信息
     * 
     * @param id 试卷ID
     * @return 结果
     */
    public int deleteTestPaperById(String id);


    public  int  addTestPaper(TestPaperVo testPaperVo);
}
