package com.qingfeng.dao.impl;

import com.qingfeng.dao.OrderDao;
import com.qingfeng.entity.OrderDetailList;
import com.qingfeng.entity.OrderList;
import com.qingfeng.pojo.Orders;
import com.qingfeng.utils.sql.DbSql;
import com.qingfeng.utils.sql.OrderSql;

import java.sql.SQLException;
import java.util.List;

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

    @Override
    public Orders findById(String orderId) {
        String sql = "select * from t_order where order_id = '" + orderId+"'";
        return OrderSql.findById(sql);
    }

    @Override
    public void updateStatus(Integer orderStatus, String orderId) {
        String sql = "update t_order set order_status = " + orderStatus + " where order_id = '" + orderId + "'";
        try {
            DbSql.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderList> findAllOrders() {
        String sql = "select order_id,table_name,username,total_num,order_total_price,order_create_time,order_status from t_order td,t_dinner_table tdt,t_user tu where td.table_id = tdt.table_id and td.user_id = tu.user_id";
        return OrderSql.findAllOrders(sql);
    }

    @Override
    public List<OrderDetailList> findOrderById(String orderListId) {
        String sql = "select food_name,num,food_total_price,order_detail_create_time from t_order_detail tod,t_food tf where tod.food_id = tf.food_id and order_id = " + orderListId;
        return OrderSql.findAllById(sql);
    }
}
