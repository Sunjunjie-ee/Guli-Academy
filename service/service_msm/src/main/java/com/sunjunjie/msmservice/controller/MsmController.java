package com.sunjunjie.msmservice.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.msmservice.service.MsmService;
import com.sunjunjie.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * message controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-17
 */

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送邮件
    @GetMapping("send/{emailaddress}")
    public R sendMsm(@PathVariable String emailaddress){
        String code = redisTemplate.opsForValue().get(emailaddress);
        if(!StringUtils.isEmpty(code)){
            return R.success();
        }
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, emailaddress);
        if(isSend){
            redisTemplate.opsForValue().set(emailaddress, code, 5, TimeUnit.MINUTES);

            return R.success();
        }else{
            return R.error().message("邮件发送失败");
        }
    }
}
