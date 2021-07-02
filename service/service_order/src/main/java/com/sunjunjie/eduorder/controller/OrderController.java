package com.sunjunjie.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunjunjie.commonutils.JwtUtils;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduorder.bean.Order;
import com.sunjunjie.eduorder.service.OrderService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单contorller
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-25
 */

@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    //生成订单
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        //创建订单，返回订单号
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("###########" + memberId);
        String orderNo = orderService.creatOrders(courseId, memberId);

        return R.success().data("orderId", orderNo);
    }

    //获取订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        Order order = orderService.getOne(wrapper);
        System.out.println(order);
        return R.success().data("item", order);
    }

    //根据课程id和用户id查询订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,
                               @PathVariable String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);
        int count = orderService.count(wrapper);
        return (count > 0);
    }
}

