package com.qingfeng.dao;

import com.qingfeng.entity.OrderDetailList;
import com.qingfeng.entity.OrderList;
import com.qingfeng.pojo.Orders;

import java.util.List;

/**
 * 订单的持久层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public interface OrderDao {

    /**
     * 保存订单信息
     * @param order
     */
    void save(Orders order);

    /**
     * 查询订单
     * @param orderId
     * @return
     */
    Orders findById(String orderId);

    /**
     * 修改订单状态为已支付
     * @param orderStatus
     * @param orderId
     */
    void updateStatus(Integer orderStatus,String orderId);

    /**
     * 查询所有的订单
     * @return
     */
    List<OrderList> findAllOrders();

    /**
     * 根据订单id查询详情
     * @param orderListId
     * @return
     */
    List<OrderDetailList> findOrderById(String orderListId);

    /**
     * 查询用户历史订单记录
     * @param userId
     * @return
     */
    List<OrderList> findAllOrderByUserId(Long userId);
}
