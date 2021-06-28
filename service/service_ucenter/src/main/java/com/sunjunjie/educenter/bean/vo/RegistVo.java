package com.sunjunjie.educenter.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegistVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String email;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
