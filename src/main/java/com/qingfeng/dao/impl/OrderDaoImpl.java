package com.qingfeng.dao.impl;

import com.qingfeng.dao.OrderDao;
import com.qingfeng.pojo.Orders;
import com.qingfeng.utils.sql.DbSql;

import java.sql.SQLException;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public class OrderDaoImpl implements OrderDao {

    /**
     * 保存订单信息
     * @param order
     */
    @Override
    public void save(Orders order) {
        String sql = "insert into t_order(order_id,table_id,user_id,total_num,order_total_price,order_create_time,order_status) " +
                "values('"+order.getOrderId()+"',"+order.getTableId()+","+order.getUserId()+","+order.getTotalNum()+","+order.getOrderTotalPrice()+",'"+order.getOrderCreateTimeStr()+"',"+order.getOrderStatus()+")";
        try {
            DbSql.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
