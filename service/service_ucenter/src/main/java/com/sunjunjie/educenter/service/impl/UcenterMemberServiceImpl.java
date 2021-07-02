package com.sunjunjie.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sunjunjie.commonutils.JwtUtils;
import com.sunjunjie.commonutils.MD5;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.educenter.bean.UcenterMember;
import com.sunjunjie.educenter.bean.vo.RegistVo;
import com.sunjunjie.educenter.mapper.UcenterMemberMapper;
import com.sunjunjie.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunjunjie.servicebase.exceptionhandler.GuliException;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-22
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String login(UcenterMember member) {
        //判断是否为空
        String email = member.getEmail();
        String password = member.getPassword();
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new GuliException(20001, "登录失败");
        }

        //判断邮箱号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        UcenterMember emailMember = baseMapper.selectOne(wrapper);
        if(emailMember == null){
            throw new GuliException(20001, "邮箱不存在");
        }

        //判断用户密码是否正确
        if(!MD5.encrypt(password).equals(emailMember.getPassword())){
            throw new GuliException(20001, "密码错误");
        }

        //判断用户是否被禁用
        if(emailMember.getIsDisabled()){
            throw new GuliException(20001, "登录失败");
        }

        String jwtToken = JwtUtils.getJwtToken(emailMember.getId(), emailMember.getNickname());
        return jwtToken;
    }

    @Override
    public void regist(RegistVo registVo) {
        String code = registVo.getCode();
        String email = registVo.getEmail();
        String nickname = registVo.getNickname();
        String password = registVo.getPassword();
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)) {
            throw new GuliException(20001, "必要信息不能为空");
        }
            //获取redis中的验证码
        String redisCode = redisTemplate.opsForValue().get(email);

        if(StringUtils.isEmpty(redisCode)){
            throw new GuliException(20001, "验证码失效");
        }
        if(!code.equals(redisCode)){
            throw new GuliException(20001, "验证码错误");
        }
        System.out.println("redis pass");

        //判断手机号是否重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new GuliException(20001, "此手机号已经被注册");
        }
        System.out.println("email pass");
        //储存用户信息到数据库中
        UcenterMember member = new UcenterMember();
        member.setEmail(email);
        member.setPassword(MD5.encrypt(password));
        member.setNickname(nickname);
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);

    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
