package com.qingfeng.service;

import com.qingfeng.entity.CartItem;
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
     */
    void genernateOrder(List<CartItem> cartList, Double totalPrice, Integer totalNum, User loginUser, Long dinner_table_id);
}
