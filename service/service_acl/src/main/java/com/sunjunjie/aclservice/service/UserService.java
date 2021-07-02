package com.sunjunjie.aclservice.service;

import com.sunjunjie.aclservice.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-22
 */
public interface UserService extends IService<User> {

    // 从数据库中取出用户信息
    User selectByUsername(String username);
}
