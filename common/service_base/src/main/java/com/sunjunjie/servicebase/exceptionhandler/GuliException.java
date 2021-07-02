package com.sunjunjie.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 自定义异常处理
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;
    private String msg;
}
