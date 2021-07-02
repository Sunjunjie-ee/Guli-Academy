package com.sunjunjie.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
/**
 * <p>
 * 网站统计日数据模块启动类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-26
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sunjunjie"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.sunjunjie.staservice.mapper")
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
