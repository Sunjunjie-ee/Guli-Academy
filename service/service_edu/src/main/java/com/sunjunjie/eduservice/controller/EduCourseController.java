package com.sunjunjie.eduservice.controller;

import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduCourse;
import com.sunjunjie.eduservice.bean.vo.CourseInfoVo;
import com.sunjunjie.eduservice.bean.vo.CoursePublishVo;
import com.sunjunjie.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService courseService;

    //添加课程信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.success().data("courseId", id);
    }

    //根据课程id查询课程
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.success().data("courseInfo", courseInfoVo);
    }

    // 更新课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.success();
    }

    //查询已经发表的课程信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.success().data("publishCourse", coursePublishVo);
    }

    //发布课程（将status设成normal）
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Noraml");
        courseService.updateById(eduCourse);
        return R.success();
    }

    //查询课程列表
    @GetMapping
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.success().data("list", list);
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.success();
    }
}

