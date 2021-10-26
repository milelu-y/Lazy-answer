package com.lazy.vm.mapper;

import java.util.List;

import com.lazy.vm.domain.Chapter;
import com.lazy.vm.domain.vo.ChapterVo;

/**
 * 章节Mapper接口
 *
 * @author lazy
 * @date 2021-10-26
 */
public interface ChapterMapper {
    /**
     * 查询章节
     *
     * @param id 章节ID
     * @return 章节
     */
    public Chapter selectChapterById(String id);

    /**
     * 查询章节列表
     *
     * @param chapter 章节
     * @return 章节集合
     */
    public List<Chapter> selectChapterList(Chapter chapter);

    /**
     * 新增章节
     *
     * @param chapter 章节
     * @return 结果
     */
    public int insertChapter(Chapter chapter);

    /**
     * 修改章节
     *
     * @param chapter 章节
     * @return 结果
     */
    public int updateChapter(Chapter chapter);

    /**
     * 删除章节
     *
     * @param id 章节ID
     * @return 结果
     */
    public int deleteChapterById(String id);

    /**
     * 批量删除章节
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChapterByIds(String[] ids);

    public List<ChapterVo> selectChapterByTaskId(String id);
}
