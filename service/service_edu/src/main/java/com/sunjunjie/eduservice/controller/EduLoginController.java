package com.sunjunjie.eduservice.controller;

import com.sunjunjie.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登陆controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-21
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    //登陆方法
    @PostMapping("login")
    public R login(){
        return R.success().data("token", "admin");
    }

    //查询登陆信息
    @GetMapping("info")
    public R info(){
        return R.success().data("roles", "[admin]").data("name", "admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
