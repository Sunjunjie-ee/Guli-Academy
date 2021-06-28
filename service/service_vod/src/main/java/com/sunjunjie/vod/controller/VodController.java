package com.sunjunjie.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.servicebase.exceptionhandler.GuliException;
import com.sunjunjie.vod.service.VodService;
import com.sunjunjie.vod.utilis.ConstantVodUtilis;
import com.sunjunjie.vod.utilis.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        try{
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtilis.ACCESS_KEY_ID, ConstantVodUtilis.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001, "删除失败");
        }
    }
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        return R.success().data("videoId", videoId);
    }


    //删除多个视频
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.success();
    }

    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try {
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtilis.ACCESS_KEY_ID, ConstantVodUtilis.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.success().data("playAuth", playAuth);
        } catch (ClientException e) {
            throw new GuliException(20001, "获取失败");
        }

    }
}

