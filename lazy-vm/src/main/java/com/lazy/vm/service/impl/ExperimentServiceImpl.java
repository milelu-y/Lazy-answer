package com.lazy.vm.service.impl;

import java.util.List;

import com.lazy.common.core.domain.entity.SysResource;
import com.lazy.vm.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.ExperimentMapper;
import com.lazy.vm.domain.Experiment;
import com.lazy.vm.service.IExperimentService;

/**
 * 实验模块Service业务层处理
 *
 * @author fgq
 * @date 2021-10-27
 */
@Service
public class ExperimentServiceImpl implements IExperimentService {
    @Autowired
    private ExperimentMapper experimentMapper;

    @Autowired
    private ISysResourceService resourceService;

    /**
     * 查询实验模块
     *
     * @param id 实验模块ID
     * @return 实验模块
     */
    @Override
    public Experiment selectExperimentById(String id) {
        Experiment experiment = experimentMapper.selectExperimentById(id);
        SysResource sysResource = resourceService.selectSysResourceById(experiment.getOutline());
        experiment.setResource(sysResource);
        return experiment;
    }

    /**
     * 查询实验模块列表
     *
     * @param experiment 实验模块
     * @return 实验模块
     */
    @Override
    public List<Experiment> selectExperimentList(Experiment experiment) {
        return experimentMapper.selectExperimentList(experiment);
    }

    /**
     * 新增实验模块
     *
     * @param experiment 实验模块
     * @return 结果
     */
    @Override
    public int insertExperiment(Experiment experiment) {
        return experimentMapper.insertExperiment(experiment);
    }

    /**
     * 修改实验模块
     *
     * @param experiment 实验模块
     * @return 结果
     */
    @Override
    public int updateExperiment(Experiment experiment) {
        return experimentMapper.updateExperiment(experiment);
    }

    /**
     * 批量删除实验模块
     *
     * @param ids 需要删除的实验模块ID
     * @return 结果
     */
    @Override
    public int deleteExperimentByIds(String[] ids) {
        return experimentMapper.deleteExperimentByIds(ids);
    }

    /**
     * 删除实验模块信息
     *
     * @param id 实验模块ID
     * @return 结果
     */
    @Override
    public int deleteExperimentById(String id) {
        return experimentMapper.deleteExperimentById(id);
    }
}
