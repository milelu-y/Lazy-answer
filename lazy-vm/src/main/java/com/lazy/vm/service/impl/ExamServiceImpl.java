package com.lazy.vm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lazy.common.utils.DateUtils;
import com.lazy.common.utils.SecurityUtils;
import com.lazy.common.utils.uuid.SnowflakeIdWorker;
import com.lazy.vm.domain.ExamDept;
import com.lazy.vm.domain.vo.ExamCourseDto;
import com.lazy.vm.domain.vo.ExamVo;
import com.lazy.vm.mapper.ExamDeptMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.ExamMapper;
import com.lazy.vm.domain.Exam;
import com.lazy.vm.service.IExamService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷添加Service业务层处理
 *
 * @author fgq
 * @date 2021-11-01
 */
@Service
public class ExamServiceImpl implements IExamService {
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamDeptMapper examDeptMapper;

    /**
     * 查询试卷添加
     *
     * @param id 试卷添加ID
     * @return 试卷添加
     */
    @Override
    public ExamVo selectExamById(String id) {
        Exam exam = examMapper.selectExamById(id);
        ExamVo examVo = new ExamVo();
        if (exam != null) {
            String examId = exam.getId();
            List<Integer> deptIds = examDeptMapper.selectByExamId(examId);
            examVo.setDeptIds(deptIds);
        }
        BeanUtils.copyProperties(exam, examVo);
        return examVo;
    }

    /**
     * 查询试卷添加列表
     *
     * @param exam 试卷添加
     * @return 试卷添加
     */
    @Override
    public List<Exam> selectExamList(Exam exam) {
        return examMapper.selectExamList(exam);
    }

    @Override
    public List<Exam> selectUserExamList(Exam exam) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        System.out.println(deptId);
        exam.setDeptId(deptId.intValue());
//        List<Integer> examDeptIds = getExamDeptIds(deptId.intValue());
        List<Exam> examList = examMapper.selectUserExamList(exam);
        return examList;
    }

    /**
     * 新增试卷添加
     *
     * @param examVo 试卷添加
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertExam(ExamVo examVo) {
        examVo.setId(SnowflakeIdWorker.getId());
        Exam exam = new Exam();
        exam.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        BeanUtils.copyProperties(examVo, exam);
        if (exam.getLimitTime() != null && !exam.getLimitTime() && exam.getStatus() != null && exam.getStatus() == 2) {
            exam.setStatus(0);
        }
        examMapper.insertExam(exam);
        return insertExamDept(examVo);
    }

    /**
     * 修改试卷添加
     *
     * @param examVo 试卷添加
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateExam(ExamVo examVo) {
        Exam exam = new Exam();
        exam.setUpdateTime(DateUtils.getNowDate());
        BeanUtils.copyProperties(examVo, exam);

        examMapper.updateExam(exam);
        //先删除，后插入
        examDeptMapper.deleteExamDeptById(exam.getId());

        return insertExamDept(examVo);
    }

    /**
     * 批量删除试卷添加
     *
     * @param ids 需要删除的试卷添加ID
     * @return 结果
     */
    @Override
    public int deleteExamByIds(String[] ids) {
        examMapper.deleteExamByIds(ids);
        return examDeptMapper.deleteExamDeptByIds(ids);
    }

    /**
     * 删除试卷添加信息
     *
     * @param id 试卷添加ID
     * @return 结果
     */
    @Override
    public int deleteExamById(String id) {
        examMapper.deleteExamById(id);
        //删除部门
        return examDeptMapper.deleteExamDeptById(id);
    }

    @Override
    public List<Exam> selectExamJoinPaperList(ExamCourseDto examCourseDto) {
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        examCourseDto.setDeptId(deptId.intValue());
        return examMapper.selectExamJoinPaperList(examCourseDto);
    }

    public int insertExamDept(ExamVo examVo) {
        List<ExamDept> examDeptList = new ArrayList<>();
        for (Integer deptId : examVo.getDeptIds()) {
            ExamDept examDept = new ExamDept();
            examDept.setExamId(examVo.getId());
            examDept.setDeptId(deptId);
            examDeptList.add(examDept);
        }
        if (examDeptList.size() > 0) {
            return examDeptMapper.batchExamDept(examDeptList);
        }
        return 0;
    }

    public List<Integer> getExamDeptIds(Integer deptId) {
        List<Integer> deptIds = examDeptMapper.selectExamDeptById(deptId);
        return deptIds;
    }
}
