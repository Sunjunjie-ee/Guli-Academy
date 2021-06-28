package com.sunjunjie.eduorder.service.impl;

import com.sunjunjie.commonutils.CourseWebVoOrder;
import com.sunjunjie.commonutils.UcenterMemberOrder;
import com.sunjunjie.eduorder.bean.Order;
import com.sunjunjie.eduorder.client.EduClient;
import com.sunjunjie.eduorder.client.UcenterClient;
import com.sunjunjie.eduorder.mapper.OrderMapper;
import com.sunjunjie.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunjunjie.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //生成订单
    @Override
    public String creatOrders(String courseId, String memberId) {
        //通过远程调用用户信息
        UcenterMemberOrder userInfoOrder =  ucenterClient.getUserInfoOrder(memberId);
        //调用课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建order对象，并添加数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
