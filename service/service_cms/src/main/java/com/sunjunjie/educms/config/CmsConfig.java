package com.sunjunjie.educms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * banner统一配置类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-21
 */

@Configuration
@MapperScan("com/sunjunjie/educms/mapper")
public class CmsConfig {
}
