package com.sunjunjie.servicebase.exceptionhandler;


import com.sunjunjie.commonutils.R ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-12
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //统一处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //自定义异常
    @ResponseBody
    @ExceptionHandler(GuliException.class)
    public R error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
