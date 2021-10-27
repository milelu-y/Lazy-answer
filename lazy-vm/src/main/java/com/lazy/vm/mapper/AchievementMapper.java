package com.lazy.vm.mapper;

import java.util.List;
import com.lazy.vm.domain.Achievement;

/**
 * 学生考试成绩Mapper接口
 * 
 * @author fgq
 * @date 2021-10-27
 */
public interface AchievementMapper 
{
    /**
     * 查询学生考试成绩
     * 
     * @param id 学生考试成绩ID
     * @return 学生考试成绩
     */
    public Achievement selectAchievementById(String id);

    /**
     * 查询学生考试成绩列表
     * 
     * @param achievement 学生考试成绩
     * @return 学生考试成绩集合
     */
    public List<Achievement> selectAchievementList(Achievement achievement);

    /**
     * 新增学生考试成绩
     * 
     * @param achievement 学生考试成绩
     * @return 结果
     */
    public int insertAchievement(Achievement achievement);

    /**
     * 修改学生考试成绩
     * 
     * @param achievement 学生考试成绩
     * @return 结果
     */
    public int updateAchievement(Achievement achievement);

    /**
     * 删除学生考试成绩
     * 
     * @param id 学生考试成绩ID
     * @return 结果
     */
    public int deleteAchievementById(String id);

    /**
     * 批量删除学生考试成绩
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAchievementByIds(String[] ids);
}
