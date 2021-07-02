package com.sunjunjie.educms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * banner模块启动类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-21
 */

@SpringBootApplication
@ComponentScan({"com.sunjunjie"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}
