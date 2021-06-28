package com.sunjunjie.eduservice.client;

import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-ucenter")
@ComponentScan
public interface UcenterClient {
    @GetMapping("/educenter/member/getMemberInfoByID/{id}")
    public UcenterMemberVo getMemberInfoById(@PathVariable("id") String id);
}
