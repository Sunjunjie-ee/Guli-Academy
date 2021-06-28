package com.sunjunjie.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunjunjie.commonutils.CourseWebVoOrder;
import com.sunjunjie.commonutils.JwtUtils;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduCourse;
import com.sunjunjie.eduservice.bean.EduTeacher;
import com.sunjunjie.eduservice.bean.chapter.ChapterVo;
import com.sunjunjie.eduservice.bean.frontVo.CourseFrontVo;
import com.sunjunjie.eduservice.bean.frontVo.CourseWebVo;
import com.sunjunjie.eduservice.client.OrdersClient;
import com.sunjunjie.eduservice.service.EduChapterService;
import com.sunjunjie.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CoursefrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);

        return R.success().data(map);
    }
    //查询课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId,
                                HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("#######"+memberId);
        System.out.println("1408615461869699073");
        boolean buyCourse = ordersClient.isBuyCourse(courseId, memberId);
        return R.success().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy", buyCourse);
    }

    //根据课程id查询课程信息（用于order）
    @PostMapping("getCourseInfoOrder/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String courseId){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }


}
