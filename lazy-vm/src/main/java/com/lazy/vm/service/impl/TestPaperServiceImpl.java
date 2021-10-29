package com.lazy.vm.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lazy.common.utils.DateUtils;
import com.lazy.common.utils.SecurityUtils;
import com.lazy.common.utils.uuid.SnowflakeIdWorker;
import com.lazy.vm.domain.Answer;
import com.lazy.vm.domain.Group;
import com.lazy.vm.domain.GroupAnswer;
import com.lazy.vm.domain.vo.AnswerOptionVo;
import com.lazy.vm.domain.vo.AnswerVo;
import com.lazy.vm.domain.vo.GroupVo;
import com.lazy.vm.domain.vo.TestPaperVo;
import com.lazy.vm.mapper.AnswerMapper;
import com.lazy.vm.mapper.GroupAnswerMapper;
import com.lazy.vm.mapper.GroupMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.TestPaperMapper;
import com.lazy.vm.domain.TestPaper;
import com.lazy.vm.service.ITestPaperService;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;

/**
 * 试卷Service业务层处理
 *
 * @author fgq
 * @date 2021-10-27
 */
@Service
public class TestPaperServiceImpl implements ITestPaperService {
    @Autowired
    private TestPaperMapper testPaperMapper;

    @Autowired
    private GroupAnswerMapper groupAnswerMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 查询试卷
     *
     * @param id 试卷ID
     * @return 试卷
     */
    @Override
    public TestPaperVo selectTestPaperById(String id) {
        TestPaperVo testPaperVo = new TestPaperVo();
        TestPaper testPaper = testPaperMapper.selectTestPaperById(id);
        //通过试卷Id查询分组
        String paperId = testPaper.getId();
        List<Group> groups = groupMapper.selectGroupByPaperId(paperId);
        List<GroupVo> groupList = new ArrayList<>();
        for (Group group : groups) {
            //通过分组ID查题目中间表
            List<GroupAnswer> groupAnswers = groupAnswerMapper.selectGroupAnswerByGroupId(group.getId());
            List<AnswerVo> answerVos = new ArrayList<>();
            for (GroupAnswer groupAnswer : groupAnswers) {
                String answerId = groupAnswer.getAnswerId();
                Answer answer = answerMapper.selectAnswerById(answerId);
                AnswerVo answerVo = new AnswerVo();
                answerVo.setScore(groupAnswer.getScore());
                BeanUtils.copyProperties(answer, answerVo);
                List<AnswerOptionVo> answerOptionVos = (List<AnswerOptionVo>) JSONObject.parse(answerVo.getOptions());
                answerVo.setAnswerList(answerOptionVos);
                answerVos.add(answerVo);
            }
            GroupVo groupVo = new GroupVo();
            BeanUtils.copyProperties(group, groupVo);
            groupVo.setQuList(answerVos);
            groupList.add(groupVo);
        }
        BeanUtils.copyProperties(testPaper, testPaperVo);
        testPaperVo.setGroupList(groupList);
        return testPaperVo;
    }

    /**
     * 查询试卷列表
     *
     * @param testPaper 试卷
     * @return 试卷
     */
    @Override
    public List<TestPaper> selectTestPaperList(TestPaper testPaper) {
        return testPaperMapper.selectTestPaperList(testPaper);
    }

    /**
     * 新增试卷
     *
     * @param testPaperVo 试卷
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTestPaper(TestPaperVo testPaperVo) {
        TestPaper testPaper = new TestPaper();
        String paperId = SnowflakeIdWorker.getId();
        testPaperVo.setId(paperId);
        for (GroupVo groupVo : testPaperVo.getGroupList()) {
            String groupId = SnowflakeIdWorker.getId();//分组ID
            groupVo.setPaperId(paperId);
            groupVo.setId(groupId);
            for (AnswerVo answerVo : groupVo.getQuList()) {
                String answerId = answerVo.getId(); //题目ID
                //TODO: 插入中间表
                GroupAnswer groupAnswer = new GroupAnswer(groupId, answerId, answerVo.getScore());
                groupAnswerMapper.insertGroupAnswer(groupAnswer);
            }
            //插入分组表
            Group group = new Group();
            group.setId(groupId);
            BeanUtils.copyProperties(groupVo, group);
            groupMapper.insertGroup(group);
        }
        //插入试卷表
        BeanUtils.copyProperties(testPaperVo, testPaper);
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        testPaper.setUserId(String.valueOf(userId));
        return testPaperMapper.insertTestPaper(testPaper);
    }

    /**
     * 修改试卷
     *
     * @param testPaperVo 试卷
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTestPaper(TestPaperVo testPaperVo) {
        TestPaper testPaper = new TestPaper();
        String paperId = testPaperVo.getId();
        List<Group> groups = groupMapper.selectGroupByPaperId(paperId);
        for (Group group : groups) {
            String id = group.getId();
            groupMapper.deleteGroupById(id);//删除分组
            groupAnswerMapper.deleteGroupAnswerById(id);//删除分组与题目关联表
        }
        List<GroupVo> groupList = testPaperVo.getGroupList();
        for (GroupVo groupVo : groupList) {
            String groupId = SnowflakeIdWorker.getId();//分组ID
            groupVo.setPaperId(paperId);
            groupVo.setId(groupId);
            for (AnswerVo answerVo : groupVo.getQuList()) {
                String answerId = answerVo.getId(); //题目ID
                //TODO: 插入中间表
                GroupAnswer groupAnswer = new GroupAnswer(groupId, answerId, answerVo.getScore());
                groupAnswerMapper.insertGroupAnswer(groupAnswer);
            }
            //插入分组表
            Group group = new Group();
            group.setId(groupId);
            BeanUtils.copyProperties(groupVo, group);
            groupMapper.insertGroup(group);
        }
        //修改试卷表
        BeanUtils.copyProperties(testPaperVo, testPaper);
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        testPaper.setUserId(String.valueOf(userId));
        return testPaperMapper.updateTestPaper(testPaper);
    }

    /**
     * 批量删除试卷
     *
     * @param ids 需要删除的试卷ID
     * @return 结果
     */
    @Override
    public int deleteTestPaperByIds(String[] ids) {
        return testPaperMapper.deleteTestPaperByIds(ids);
    }

    /**
     * 删除试卷信息
     *
     * @param id 试卷ID
     * @return 结果
     */
    @Override
    public int deleteTestPaperById(String id) {
        return testPaperMapper.deleteTestPaperById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addTestPaper(TestPaperVo testPaperVo) {
        if (testPaperVo != null) {
            String id = UUID.randomUUID().toString().replaceAll("-", "").toString();
            TestPaper testPaper = new TestPaper();
            BeanUtils.copyProperties(testPaperVo, testPaper);
            testPaper.setUserId(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
            testPaper.setId(id);
            testPaperMapper.insertTestPaper(testPaper);
            for (Answer ans : testPaperVo.getAnswers()) {
                testPaperMapper.addTestPaper(id, String.valueOf(ans.getId()));
            }
            return 1;
        }
        return 0;
    }
}
