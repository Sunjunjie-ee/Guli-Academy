package com.sunjunjie.educenter.service;

import com.sunjunjie.educenter.bean.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunjunjie.educenter.bean.vo.RegistVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void regist(RegistVo registVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
