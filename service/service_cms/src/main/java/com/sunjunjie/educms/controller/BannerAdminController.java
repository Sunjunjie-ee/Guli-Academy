package com.sunjunjie.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.educms.bean.CrmBanner;

import com.sunjunjie.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-21
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        crmBannerService.page(pageBanner, null);
        return R.success().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmbanner){
        crmBannerService.save(crmbanner);
        return R.success();
    }

    @DeleteMapping("remove/{crmBannerId}")
    public R removeBanner(@RequestBody String crmBannerId){
        crmBannerService.removeById(crmBannerId);
        return R.success();
    }

    @PutMapping("update")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.success();
    }

    @GetMapping("get/{id}")
    public R getById(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return R.success().data("item", crmBanner);
    }


}

