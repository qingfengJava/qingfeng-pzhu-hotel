package com.qingfeng.dao;

import com.qingfeng.pojo.Orders;

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
}
