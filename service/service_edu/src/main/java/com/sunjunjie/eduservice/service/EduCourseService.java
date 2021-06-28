package com.sunjunjie.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunjunjie.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunjunjie.eduservice.bean.frontVo.CourseFrontVo;
import com.sunjunjie.eduservice.bean.frontVo.CourseWebVo;
import com.sunjunjie.eduservice.bean.vo.CourseInfoVo;
import com.sunjunjie.eduservice.bean.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
