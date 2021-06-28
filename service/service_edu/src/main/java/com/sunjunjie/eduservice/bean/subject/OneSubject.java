package com.sunjunjie.eduservice.bean.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private String id;
    private String title;

    //建立一级分类于二级分类的关系
    private List<TwoSubject> children = new ArrayList<>();
}
