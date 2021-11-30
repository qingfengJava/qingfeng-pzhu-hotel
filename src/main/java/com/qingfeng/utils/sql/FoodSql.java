package com.qingfeng.utils.sql;

import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * t_food表的增删改查的封装
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class FoodSql {

    /**
     * 根据条件查询菜品，要关联菜系表
     * @param sql
     * @return
     */
    public static List<Food> findCondition(String sql) {
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
                while (rs.next()) {
                    //将数据进行封装
                    Long foodId = rs.getLong("food_id");
                    long typeId = rs.getLong("type_id");
                    String foodName = rs.getString("food_name");
                    double foodPrice = rs.getDouble("food_price");
                    double foodMprice = rs.getDouble("food_mprice");
                    String foodImage = rs.getString("food_image");
                    String foodDesc = rs.getString("food_desc");
                    String typeName = rs.getString("type_name");
                    //将数据封装到对象中
                    foods.add(new Food(foodId, typeId, foodName, foodPrice, foodMprice,foodImage,foodDesc,new FoodType(typeId,typeName)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return foods;
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



}
