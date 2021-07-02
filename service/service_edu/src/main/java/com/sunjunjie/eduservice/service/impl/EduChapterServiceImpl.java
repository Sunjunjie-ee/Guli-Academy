package com.sunjunjie.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduChapter;
import com.sunjunjie.eduservice.bean.EduVideo;
import com.sunjunjie.eduservice.bean.chapter.ChapterVo;
import com.sunjunjie.eduservice.bean.chapter.VideoVo;
import com.sunjunjie.eduservice.bean.subject.OneSubject;
import com.sunjunjie.eduservice.mapper.EduChapterMapper;
import com.sunjunjie.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunjunjie.eduservice.service.EduVideoService;
import com.sunjunjie.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService videoService;


    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //根据课程id查询每个章节的课程小结
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);


        List<ChapterVo> finalList = new ArrayList<>();
        //章节list数据封装
        for (EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);


            List<VideoVo> videoList = new ArrayList<>();
            //小节list数据封装
            for(EduVideo eduVideo : eduVideoList){
                //判断小节的chapterid与chapterid是否相同
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //章节里有小节时则不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        //查询记录数
        int count = videoService.count(wrapper);
        if(count > 0){//能查询出小节，不删除
            throw new GuliException(20001, "请先删除小节");
        }else{
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }



    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
