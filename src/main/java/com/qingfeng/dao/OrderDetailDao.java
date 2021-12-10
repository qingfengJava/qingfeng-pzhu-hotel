package com.qingfeng.dao;

import com.qingfeng.pojo.OrderDetail;

/**
 * 订单详情的持久层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public interface OrderDetailDao {

    /**
     * 保存订单详情信息
     * @param orderDetail
     */
    void save(OrderDetail orderDetail);
}
