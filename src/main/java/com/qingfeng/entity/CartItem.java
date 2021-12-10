package com.qingfeng.entity;

import com.qingfeng.pojo.OrderDetail;

import java.io.Serializable;

/**
 * 餐车项
 * 继承订单详情，因为使用的属性正好和订单详情中的属性相同
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/8
 */
public class CartItem extends OrderDetail implements Serializable {

    /**
     * 存放餐车项的单价
     */
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
