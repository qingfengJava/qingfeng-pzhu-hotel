package com.qingfeng.pojo;

import com.qingfeng.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐桌的实体类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public class DinnerTable implements Serializable {

    /**
     * 餐桌Id
     */
    private Long tableId;
    /**
     * 餐桌名称
     */
    private String tableName;
    /**
     * 餐桌状态（是否预定） 0：否  1：是
     */
    private Integer tableStatus;
    /**
     * 预定时间
     */
    private Date reservationTime;
    /**
     * 定义一个字符串格式时间
     */
    private String reservationTimeStr;

    public DinnerTable() {
    }

    public DinnerTable(Long tableId, String tableName, Integer tableStatus, Date reservationTime) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.tableStatus = tableStatus;
        this.reservationTime = reservationTime;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(Integer tableStatus) {
        this.tableStatus = tableStatus;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * 预定时间字符串的表现形式，是由预定时间转换而来的
     * @return
     */
    public String getReservationTimeStr() {
        //使用封装好的工具类，将日期转换为字符串
        return this.reservationTime !=null ? DateUtils.dateToStr(reservationTime,"yyyy-MM-dd  HH:mm:ss"):null;
    }

    public void setReservationTimeStr(String reservationTimeStr) {
        this.reservationTimeStr = reservationTimeStr;
    }

    @Override
    public String toString() {
        return "DinnerTable{" +
                "tableId=" + tableId +
                ", tableName='" + tableName + '\'' +
                ", tableStatus=" + tableStatus +
                ", reservationTime=" + reservationTime +
                ", reservationTimeStr='" + reservationTimeStr + '\'' +
                '}';
    }
}
