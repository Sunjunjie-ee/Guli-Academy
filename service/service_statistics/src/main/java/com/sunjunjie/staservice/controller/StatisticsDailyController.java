package com.sunjunjie.staservice.controller;


import com.sunjunjie.commonutils.R;
import com.sunjunjie.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 controller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-26
 */

@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day){
        staService.registerCount(day);
        return R.success();
    }

    //图表显示返回两部分数据，json
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end){
        Map<String, Object> map = staService.getShowData(type, begin, end);
        return R.success().data(map);
    }

}

