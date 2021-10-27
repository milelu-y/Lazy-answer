package com.lazy.vm.service.impl;

import java.util.List;
import java.util.UUID;

import com.lazy.common.utils.DateUtils;
import com.lazy.common.utils.SecurityUtils;
import com.lazy.vm.domain.Answer;
import com.lazy.vm.domain.vo.TestPaperVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.TestPaperMapper;
import com.lazy.vm.domain.TestPaper;
import com.lazy.vm.service.ITestPaperService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷Service业务层处理
 *
 * @author fgq
 * @date 2021-10-27
 */
@Service
public class TestPaperServiceImpl implements ITestPaperService
{
    @Autowired
    private TestPaperMapper testPaperMapper;

    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    @Override
    public TestPaper selectTestPaperById(String id)
    {
        return testPaperMapper.selectTestPaperById(id);
    }

    /**
     * 查询试卷列表
     *
     * @param testPaper 试卷
     * @return 试卷
     */
    @Override
    public List<TestPaper> selectTestPaperList(TestPaper testPaper)
    {
        return testPaperMapper.selectTestPaperList(testPaper);
    }

    /**
     * 新增试卷
     *
     * @param testPaper 试卷
     * @return 结果
     */
    @Override
    public int insertTestPaper(TestPaper testPaper)
    {
        return testPaperMapper.insertTestPaper(testPaper);
    }

    /**
     * 修改试卷
     *
     * @param testPaper 试卷
     * @return 结果
     */
    @Override
    public int updateTestPaper(TestPaper testPaper)
    {
        testPaper.setUpdateTime(DateUtils.getNowDate());
        return testPaperMapper.updateTestPaper(testPaper);
    }

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的试卷ID
     * @return 结果
     */
    @Override
    public int deleteTestPaperByIds(String[] ids)
    {
        return testPaperMapper.deleteTestPaperByIds(ids);
    }

    /**
     * 删除试卷信息
     *
     * @param id 试卷ID
     * @return 结果
     */
    @Override
    public int deleteTestPaperById(String id)
    {
        return testPaperMapper.deleteTestPaperById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addTestPaper(TestPaperVo testPaperVo) {
        if(testPaperVo !=null){
            String id = UUID.randomUUID().toString().replaceAll("-", "").toString();
            TestPaper testPaper = new TestPaper();
            BeanUtils.copyProperties(testPaperVo,testPaper);
            testPaper.setUserId(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
            testPaper.setId(id);
            this.insertTestPaper(testPaper);
            for (Answer ans : testPaperVo.getAnswers()){
                testPaperMapper.addTestPaper(id,String.valueOf(ans.getId()));
            }
            return  1;
        }
        return 0;
    }
}
