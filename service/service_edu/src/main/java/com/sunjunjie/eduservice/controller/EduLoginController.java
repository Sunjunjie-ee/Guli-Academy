package com.sunjunjie.eduservice.controller;

import com.sunjunjie.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.success().data("token", "admin");
    }

    @GetMapping("info")
    public R info(){
        return R.success().data("roles", "[admin]").data("name", "admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
