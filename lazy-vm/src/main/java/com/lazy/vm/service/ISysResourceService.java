package com.lazy.vm.service;

import com.lazy.common.core.domain.entity.SysResource;

import java.util.List;

/**
 * 资源Service接口
 *
 * @author lazy
 * @date 2021-08-03
 */
public interface ISysResourceService
{
    /**
     * 查询资源
     *
     * @param id 资源ID
     * @return 资源
     */
    public SysResource selectSysResourceById(String id);

    /**
     * 查询资源列表
     *
     * @param sysResource 资源
     * @return 资源集合
     */
    public List<SysResource> selectSysResourceList(SysResource sysResource);

    /**
     * 新增资源
     *
     * @param sysResource 资源
     * @return 结果
     */
    public int insertSysResource(SysResource sysResource);

    /**
     * 修改资源
     *
     * @param sysResource 资源
     * @return 结果
     */
    public int updateSysResource(SysResource sysResource);

    /**
     * 批量删除资源
     *
     * @param ids 需要删除的资源ID
     * @return 结果
     */
    public int deleteSysResourceByIds(String[] ids);

    /**
     * 删除资源信息
     *
     * @param id 资源ID
     * @return 结果
     */
    public int deleteSysResourceById(String id);
}
