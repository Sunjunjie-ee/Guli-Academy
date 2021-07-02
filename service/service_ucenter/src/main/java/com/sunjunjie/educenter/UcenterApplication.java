package com.sunjunjie.educenter;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 用户登录模块启动类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-22
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.sunjunjie"})
@MapperScan("com.sunjunjie.educenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
