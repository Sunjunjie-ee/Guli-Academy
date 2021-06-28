package com.sunjunjie.eduservice.controller;


import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduChapter;
import com.sunjunjie.eduservice.bean.EduCourse;
import com.sunjunjie.eduservice.bean.chapter.ChapterVo;
import com.sunjunjie.eduservice.service.EduChapterService;
import com.sunjunjie.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.success().data("allChapterVideo", list);
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return R.success();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R setChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.success().data("chapter", eduChapter);
    }

    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.success();
    }

    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag) {
            return R.success();
        } else {
            return R.error();
        }

    }



}

