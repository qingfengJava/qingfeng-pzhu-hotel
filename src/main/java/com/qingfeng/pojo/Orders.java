package com.qingfeng.pojo;

import com.qingfeng.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单的实体类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public class Orders implements Serializable {

    /**
     * 订单Id
     */
    private String OrderId;
    /**
     * 餐桌Id
     */
    private Long tableId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总数量
     */
    private int totalNum;
    /**
     * 订单总金额
     */
    private Double orderTotalPrice;
    /**
     * 下单时间
     */
    private Date orderCreateTime;
    /**
     * 下单时间字符串形式
     */
    private String orderCreateTimeStr;
    /**
     * 状态
     */
    private Integer orderStatus;

    public Orders() {
    }

    public Orders(String orderId, Long tableId, Long userId, int totalNum, Double orderTotalPrice, Date orderCreateTime, Integer orderStatus) {
        OrderId = orderId;
        this.tableId = tableId;
        this.userId = userId;
        this.totalNum = totalNum;
        this.orderTotalPrice = orderTotalPrice;
        this.orderCreateTime = orderCreateTime;
        this.orderStatus = orderStatus;
    }

    public String getOrderCreateTimeStr() {
        //使用封装好的工具类，将日期转换为字符串
        return this.orderCreateTime !=null ? DateUtils.dateToStr(orderCreateTime,"yyyy-MM-dd  HH:mm:ss"):null;
    }

    public void setOrderCreateTimeStr(String orderCreateTimeStr) {
        this.orderCreateTimeStr = orderCreateTimeStr;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId='" + OrderId + '\'' +
                ", tableId=" + tableId +
                ", userId=" + userId +
                ", totalNum=" + totalNum +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderCreateTime=" + orderCreateTime +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
