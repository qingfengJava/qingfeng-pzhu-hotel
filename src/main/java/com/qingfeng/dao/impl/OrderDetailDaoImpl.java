package com.qingfeng.dao.impl;

import com.qingfeng.dao.OrderDetailDao;
import com.qingfeng.pojo.OrderDetail;
import com.qingfeng.utils.sql.DbSql;

import java.sql.SQLException;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public class OrderDetailDaoImpl implements OrderDetailDao {

    /**
     * 订单详情信息保存
     * @param orderDetail
     */
    @Override
    public void save(OrderDetail orderDetail) {
        String sql = "insert into t_order_detail(order_detail_id,order_id,food_id,num,food_total_price,order_detail_create_time,order_detail_update_time) " +
                "values('"+orderDetail.getOrderDetailId()+"','"+orderDetail.getOrderId()+"',"+orderDetail.getFoodId()+","+orderDetail.getNum()+","+orderDetail.getFoodTotalPrice()+",'"+orderDetail.getOrderDetailCreateTimeStr()+"','"+orderDetail.getOrderDetailUpdateTimeStr()+"')";
        try {
            DbSql.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
