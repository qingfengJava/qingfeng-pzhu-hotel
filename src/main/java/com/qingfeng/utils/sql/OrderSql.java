package com.qingfeng.utils.sql;

import com.qingfeng.entity.OrderDetailList;
import com.qingfeng.entity.OrderList;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.Orders;
import com.qingfeng.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * t_food表的增删改查的封装
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class OrderSql {

    /**
     * 根据条件查询菜品，要关联菜系表
     * @param sql
     * @return
     */
    public static Orders findById(String sql) {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Orders orders = null;
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()) {
                    //将数据进行封装
                    String orderId = rs.getString("order_id");
                    Long tableId = rs.getLong("table_id");
                    Long userId = rs.getLong("user_id");
                    int totalNum = rs.getInt("total_num");
                    double orderTotalPrice = rs.getDouble("order_total_price");
                    Date orderCreateTime = rs.getDate("order_create_time");
                    int orderStatus = rs.getInt("order_status");
                    //将数据封装到对象中
                    orders = new Orders(orderId,tableId,userId,totalNum,orderTotalPrice,orderCreateTime,orderStatus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return orders;
    }

    /**
     * 条件查询菜品总的记录数
     * @param sql
     * @return
     */
    public static int findTotalCount(String sql) {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Food> foods = new ArrayList<>();
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();

                //移动一个指针，获取总的记录数
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return 0;
    }


    public static List<OrderList> findAllOrders(String sql) {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderList> ordersList = new ArrayList<>();
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()) {
                    //将数据进行封装
                    String orderId = rs.getString("order_id");
                    String tableName = rs.getString("table_name");
                    String username = rs.getString("username");
                    int totalNum = rs.getInt("total_num");
                    double orderTotalPrice = rs.getDouble("order_total_price");
                    Date orderCreateTime = rs.getDate("order_create_time");
                    int orderStatus = rs.getInt("order_status");
                    //将数据封装到对象中
                    ordersList.add(new OrderList(orderId,tableName,username,totalNum,orderCreateTime,orderTotalPrice,orderStatus));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return ordersList;
    }

    public static List<OrderDetailList> findAllById(String sql) {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDetailList> ordersList = new ArrayList<>();
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()) {
                    //将数据进行封装
                    String foodName = rs.getString("food_name");
                    int num = rs.getInt("num");
                    Double foodTotalPrice = rs.getDouble("food_total_price");
                    Date orderDetailCreateTime = rs.getDate("order_detail_create_time");
                    //将数据封装到对象中
                    ordersList.add(new OrderDetailList(foodName,num,foodTotalPrice,orderDetailCreateTime));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return ordersList;
    }
}
