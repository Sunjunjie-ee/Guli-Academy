package com.sunjunjie.educms.controller;

import com.sunjunjie.commonutils.R;
import com.sunjunjie.educms.bean.CrmBanner;
import com.sunjunjie.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台banner controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-21
 */

@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //将所有banner放入redis
    @Cacheable(key="'selectIndexList'", value = "banner")
    @GetMapping("getAllBanner")
    public R getAllCrmBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.success().data("list", list);
    }
}
