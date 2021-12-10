package com.qingfeng.entity;

import com.qingfeng.utils.DateUtils;

import java.util.Date;

/**
 * 后台菜详情实体
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/10
 */
public class OrderDetailList {

    /**
     * 菜名
     */
    private String menuName;
    /**
     * 数量
     */
    private int num;
    /**
     * 小计
     */
    private Double totalPrice;

    /**
     * 下单时间
     */
    private Date orderDetailCreateTime;
    /**
     * 下单时间字符串形式
     */
    private String orderDetailCreateTimeStr;

    public OrderDetailList() {
    }

    public OrderDetailList(String menuName, int num, Double totalPrice, Date orderDetailCreateTime) {
        this.menuName = menuName;
        this.num = num;
        this.totalPrice = totalPrice;
        this.orderDetailCreateTime = orderDetailCreateTime;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailList{" +
                "menuName='" + menuName + '\'' +
                ", num=" + num +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
