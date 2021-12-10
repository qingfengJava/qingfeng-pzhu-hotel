package com.qingfeng.service;

import com.qingfeng.entity.CartItem;
import com.qingfeng.entity.OrderDetailList;
import com.qingfeng.entity.OrderList;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.pojo.Orders;
import com.qingfeng.pojo.User;

import java.util.List;

/**
 * 订单的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public interface OrderService {

    /**
     * 根据餐车中的数据，生成订单
     * @param cartList
     * @param totalPrice
     * @param totalNum
     * @param loginUser
     * @param dinner_table_id
     * @return
     */
    ResultVO genernateOrder(List<CartItem> cartList, Double totalPrice, Integer totalNum, User loginUser, Long dinner_table_id);

    /**
     * 查询所有的订单信息
     * @return
     */
    List<OrderList> findAllOrder();

    /**
     * 根据订单Id查询详情
     * @param orderListId
     * @return
     */
    List<OrderDetailList> findOrderById(String orderListId);

    /**
     * 根据Id查询order信息
     * @param orderId
     * @return
     */
    Orders findById(String orderId);

    /**
     * 根据id将订单状态设置为1
     * @param orderId
     * @param status
     */
    void updateOrderStatus(int status,String orderId);
}
