package com.qingfeng.service;

import com.qingfeng.entity.CartItem;

import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public interface CartService {

    /**
     * 将餐车map集合中的数据转换成list集合存储
     * @param cart
     * @param isMember
     * @return
     */
    Map<String, Object> getCartList(Map<Long, CartItem> cart, Integer isMember);
}
