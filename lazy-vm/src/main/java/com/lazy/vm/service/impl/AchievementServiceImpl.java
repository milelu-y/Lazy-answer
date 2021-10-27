package com.lazy.vm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazy.vm.mapper.AchievementMapper;
import com.lazy.vm.domain.Achievement;
import com.lazy.vm.service.IAchievementService;

/**
 * 学生考试成绩Service业务层处理
 *
 * @author fgq
 * @date 2021-10-27
 */
@Service
public class AchievementServiceImpl implements IAchievementService
{
    @Autowired
    private AchievementMapper achievementMapper;

    /**
     * 查询学生考试成绩
     *
     * @param id 学生考试成绩ID
     * @return 学生考试成绩
     */
    @Override
    public Achievement selectAchievementById(String id)
    {
        return achievementMapper.selectAchievementById(id);
    }

    /**
     * 查询学生考试成绩列表
     *
     * @param achievement 学生考试成绩
     * @return 学生考试成绩
     */
    @Override
    public List<Achievement> selectAchievementList(Achievement achievement)
    {
        return achievementMapper.selectAchievementList(achievement);
    }

    /**
     * 新增学生考试成绩
     *
     * @param achievement 学生考试成绩
     * @return 结果
     */
    @Override
    public int insertAchievement(Achievement achievement)
    {
        return achievementMapper.insertAchievement(achievement);
    }

    /**
     * 修改学生考试成绩
     *
     * @param achievement 学生考试成绩
     * @return 结果
     */
    @Override
    public int updateAchievement(Achievement achievement)
    {
        return achievementMapper.updateAchievement(achievement);
    }

    /**
     * 批量删除学生考试成绩
     *
     * @param ids 需要删除的学生考试成绩ID
     * @return 结果
     */
    @Override
    public int deleteAchievementByIds(String[] ids)
    {
        return achievementMapper.deleteAchievementByIds(ids);
    }

    /**
     * 删除学生考试成绩信息
     *
     * @param id 学生考试成绩ID
     * @return 结果
     */
    @Override
    public int deleteAchievementById(String id)
    {
        return achievementMapper.deleteAchievementById(id);
    }
}
