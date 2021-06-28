package com.sunjunjie.eduservice.service;

import com.sunjunjie.eduservice.bean.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunjunjie.eduservice.bean.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);


}



