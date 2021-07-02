package com.sunjunjie.eduservice.mapper;

import com.sunjunjie.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunjunjie.eduservice.bean.frontVo.CourseWebVo;
import com.sunjunjie.eduservice.bean.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String id);

    CourseWebVo getBaseCourseInfo(String courseId);
}
