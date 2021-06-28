package com.sunjunjie.eduservice.service;

import com.sunjunjie.eduservice.bean.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunjunjie.eduservice.bean.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();

}
