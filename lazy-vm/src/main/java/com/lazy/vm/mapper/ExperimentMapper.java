package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Experiment;

/**
 * 实验模块Mapper接口
 * 
 * @author fgq
 * @date 2021-10-27
 */
public interface ExperimentMapper 
{
    /**
     * 查询实验模块
     * 
     * @param id 实验模块ID
     * @return 实验模块
     */
    public Experiment selectExperimentById(String id);

    /**
     * 查询实验模块列表
     * 
     * @param experiment 实验模块
     * @return 实验模块集合
     */
    public List<Experiment> selectExperimentList(Experiment experiment);

    /**
     * 新增实验模块
     * 
     * @param experiment 实验模块
     * @return 结果
     */
    public int insertExperiment(Experiment experiment);

    /**
     * 修改实验模块
     * 
     * @param experiment 实验模块
     * @return 结果
     */
    public int updateExperiment(Experiment experiment);

    /**
     * 删除实验模块
     * 
     * @param id 实验模块ID
     * @return 结果
     */
    public int deleteExperimentById(String id);

    /**
     * 批量删除实验模块
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExperimentByIds(String[] ids);
}
