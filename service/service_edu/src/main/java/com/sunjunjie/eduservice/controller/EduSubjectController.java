package com.sunjunjie.eduservice.controller;


import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.subject.OneSubject;
import com.sunjunjie.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;
    @PostMapping("addSubject")
    public R addsubject(MultipartFile file){
        subjectService.saveSubject(file, subjectService);
        return R.success();
    }

    //课程分类列表（tree）
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.success().data("list",list);

    }


}

