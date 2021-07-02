package com.sunjunjie.eduservice.controller;


import com.sunjunjie.servicebase.exceptionhandler.GuliException;
import org.apache.commons.lang.StringUtils;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduVideo;
import com.sunjunjie.eduservice.client.VodClient;
import com.sunjunjie.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频controller
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加课程视频
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.success();
    }

    //删除课程视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001){
                throw new GuliException(20001, "删除视频失败，触发熔断器");
            }
        }
        videoService.removeById(id);
        return R.success();
    }

}

