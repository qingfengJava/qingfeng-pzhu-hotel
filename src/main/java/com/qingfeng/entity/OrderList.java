package com.qingfeng.entity;

import com.qingfeng.utils.DateUtils;

import java.util.Date;

/**
 * 定义后台展示order列表的实体类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/10
 */
public class OrderList {

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 餐桌名
     */
    private String tableName;
    /**
     * 用户
     */
    private String userName;
    /**
     * 菜品数量
     */
    private int num;
    /**
     * 下单日期
     */
    private Date orderCreateDate;
    /**
     * 下单日期字符形式
     */
    private String orderCreateDateStr;
    /**
     * 总金额
     */
    private Double totalPrice;
    /**
     * 状态
     */
    private int status;

    public OrderList() {
    }

    public OrderList(String orderId, String tableName, String userName, int num, Date orderCreateDate, Double totalPrice, int status) {
        this.orderId = orderId;
        this.tableName = tableName;
        this.userName = userName;
        this.num = num;
        this.orderCreateDate = orderCreateDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getOrderCreateDateStr() {
        //使用封装好的工具类，将日期转换为字符串
        return this.orderCreateDate !=null ? DateUtils.dateToStr(orderCreateDate,"yyyy-MM-dd  HH:mm:ss"):null;
    }

    public void setOrderCreateDateStr(String orderCreateDateStr) {
        this.orderCreateDateStr = orderCreateDateStr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "orderId='" + orderId + '\'' +
                ", tableName='" + tableName + '\'' +
                ", userName='" + userName + '\'' +
                ", num=" + num +
                ", orderCreateDate=" + orderCreateDate +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
