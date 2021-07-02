package com.sunjunjie.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * message 启动类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-17
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.sunjunjie")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
