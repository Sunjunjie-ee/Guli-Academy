package com.sunjunjie.eduservice.bean.vo;

import lombok.Data;

@Data
public class TeacherQuery {
    //讲师名字
    private String name;
    //讲师等级
    private Integer level;
    //查询开始时间
    private String begin;
    //查询结束时间
    private String end;

}
