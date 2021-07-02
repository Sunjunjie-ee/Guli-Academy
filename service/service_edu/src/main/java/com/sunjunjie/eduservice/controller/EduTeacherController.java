package com.sunjunjie.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.StringUtils ;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduTeacher;
import com.sunjunjie.eduservice.bean.vo.TeacherQuery;
import com.sunjunjie.eduservice.service.EduTeacherService;
import com.sunjunjie.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

/**
 * <p>
 * 讲师controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //访问地址 ：http://localhost:8080/eduservice/teacher/findAll
     @Autowired
    private EduTeacherService teacherService;

     //查询所有
     @GetMapping("findAll")
    public R findAllTeacher(){
         List<EduTeacher> list = teacherService.list(null);
         return R.success().data("items", list);
     }

     //按照id删除
     @DeleteMapping("delete/{id}")
    public R removeTeahcher(@PathVariable String id){
         boolean flag = teacherService.removeById(id);
         if(flag){
             return R.success();
         }else{
             return R.error();
         }
     }

     //分页查询讲师
     @GetMapping("pageTeacher/{current}/{limit}")
     public R pageListTeacher(@PathVariable long current,
                              @PathVariable long limit){
         Page<EduTeacher> pageTeacher = new Page<>(current, limit);
         // 将分页的所有数据都封装到pageTeacher对象中
         teacherService.page(pageTeacher, null);
         long total = pageTeacher.getTotal();
         List<EduTeacher> records = pageTeacher.getRecords();
         return R.success().data("total", total).data("rows", records);
     }


     //组合条件分页查询
     @PostMapping("pageTeacherCondition/{current}/{limit}")
     public R pageTeacherCondition(@PathVariable long current,
                                   @PathVariable long limit,
                                   @RequestBody(required=false) TeacherQuery teacherQuery){
         Page<EduTeacher> pageTeacher = new Page<>(current, limit);
         QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

         String name = teacherQuery.getName();
         Integer level = teacherQuery.getLevel();
         String begin = teacherQuery.getBegin();
         String end = teacherQuery.getEnd();
         if(!StringUtils.isEmpty(name)){
            wrapper.like(" name", name);
         }
         if(level != null){
             wrapper.eq("level", level);
         }
         if(!StringUtils.isEmpty(begin)){
             wrapper.gt("gmt_creat", begin);
         }
         if(!StringUtils.isEmpty(end)){
             wrapper.le("gmt_creat", end);
         }

         teacherService.page(pageTeacher, wrapper);

         long total = pageTeacher.getTotal();
         List<EduTeacher> records = pageTeacher.getRecords();
         return R.success().data("total", total).data("rows", records);
     }


     //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
         boolean save = teacherService.save(eduTeacher);
         if(save){
             return R.success();
         }else{
             return R.error();
         }
    }



    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.success().data("teacher", eduTeacher);
    }


    //讲师修改
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return R.success();
        }else{
            return R.error();
        }
    }
}

