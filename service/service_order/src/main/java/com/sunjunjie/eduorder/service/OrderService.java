package com.sunjunjie.eduorder.service;

import com.sunjunjie.eduorder.bean.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author sunjunjie
 * @since 2021-06-25
 */
public interface OrderService extends IService<Order> {

    String creatOrders(String courseId, String memberIdByJwtToken);
}
