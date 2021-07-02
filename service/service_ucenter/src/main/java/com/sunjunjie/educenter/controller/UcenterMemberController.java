package com.sunjunjie.educenter.controller;


import com.sunjunjie.commonutils.JwtUtils;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.commonutils.vo.UcenterMemberOrder;
import com.sunjunjie.educenter.bean.UcenterMember;
import com.sunjunjie.educenter.bean.vo.RegistVo;
import com.sunjunjie.educenter.bean.vo.UcenterMemberVo;
import com.sunjunjie.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-22
 */

@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        return R.success().data("token", token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegistVo registVo){
        memberService.regist(registVo);
        return R.success();
    }
    //根据token获取用户信息（方便前端使用）
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        System.out.println(member);
        return R.success().data("userInfo", member);
    }
    //评论方法中的远程调用
    @GetMapping("getMemberInfoById/{id}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        UcenterMemberVo memberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member, memberVo);
        return memberVo;
    }
    //order方法中的远程调用
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    //查询注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day){
        Integer count = memberService.countRegisterDay(day);
        return R.success().data("countRegister", count);
    }

}

