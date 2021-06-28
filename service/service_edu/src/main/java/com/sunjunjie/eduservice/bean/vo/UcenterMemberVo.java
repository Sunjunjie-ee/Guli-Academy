package com.sunjunjie.eduservice.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UcenterMemberVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String Avatar;
}
