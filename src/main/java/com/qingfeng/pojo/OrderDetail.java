package com.qingfeng.pojo;

import com.qingfeng.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情实体类
 * 对应数据库表t-order
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/8
 */
public class OrderDetail implements Serializable {

    /**
     * 详情id
     */
    private String orderDetailId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 菜品id
     */
    private Long foodId;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 小计
     */
    private Double foodTotalPrice;
    /**
     * 创建时间
     */
    private Date orderDetailCreateTime;
    /**
     * 创建时间——字符串对象
     */
    private String orderDetailCreateTimeStr;
    /**
     * 更新时间
     */
    private Date orderDetailUpdateTime;
    /**
     * 更新时间——字符串对象
     */
    private String orderDetailUpdateTimeStr;

    /**
     * 关联菜品，维护菜品对象
     */
    private Food food;

    public OrderDetail() {
    }

    public OrderDetail(String orderDetailId, String orderId, Long foodId, Integer num, Double foodTotalPrice, Date orderDetailCreateTime, Date orderDetailUpdateTime) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.foodId = foodId;
        this.num = num;
        this.foodTotalPrice = foodTotalPrice;
        this.orderDetailCreateTime = orderDetailCreateTime;
        this.orderDetailUpdateTime = orderDetailUpdateTime;
    }

    public Date getOrderDetailCreateTime() {
        return orderDetailCreateTime;
    }

    public void setOrderDetailCreateTime(Date orderDetailCreateTime) {
        this.orderDetailCreateTime = orderDetailCreateTime;
    }

    public String getOrderDetailCreateTimeStr() {
        //使用封装好的工具类，将日期转换为字符串
        return this.orderDetailCreateTime !=null ? DateUtils.dateToStr(orderDetailCreateTime,"yyyy-MM-dd  HH:mm:ss"):null;
    }

    public void setOrderDetailCreateTimeStr(String orderDetailCreateTimeStr) {
        this.orderDetailCreateTimeStr = orderDetailCreateTimeStr;
    }

    public Date getOrderDetailUpdateTime() {
        return orderDetailUpdateTime;
    }

    public void setOrderDetailUpdateTime(Date orderDetailUpdateTime) {
        this.orderDetailUpdateTime = orderDetailUpdateTime;
    }

    public String getOrderDetailUpdateTimeStr() {
        //使用封装好的工具类，将日期转换为字符串
        return this.orderDetailUpdateTime !=null ? DateUtils.dateToStr(orderDetailUpdateTime,"yyyy-MM-dd  HH:mm:ss"):null;
    }

    public void setOrderDetailUpdateTimeStr(String orderDetailUpdateTimeStr) {
        this.orderDetailUpdateTimeStr = orderDetailUpdateTimeStr;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getFoodTotalPrice() {
        return foodTotalPrice;
    }

    public void setFoodTotalPrice(Double foodTotalPrice) {
        this.foodTotalPrice = foodTotalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", foodId=" + foodId +
                ", num=" + num +
                ", foodTotalPrice=" + foodTotalPrice +
                '}';
    }
}
