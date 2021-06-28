package com.sunjunjie.eduservice.bean.excel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {

    @ExcelProperty(index = 0) //第一列
    private String oneSubjectName;

    @ExcelProperty(index = 1) // 第二列
    private String twoSubjectName;


}
