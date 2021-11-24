package com.qingfeng.utils;

import com.qingfeng.pojo.FoodType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu表的增删改查的封装
 *
 * @author 清风学Java
 * @date 2021/11/21
 * @apiNote
 */
public class FoodTypeSql {

    /**
     * 通用的增，删，改的方法
     *
     * @param sql
     */
    public static void updateFoodType(String sql) throws SQLException {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            if (con != null) {
                //开启事务
                con.setAutoCommit(false);
                //利用con(连接)建立增，删，改语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做增，删，改
                pst.execute();
                //提交事务
                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            con.rollback();
        } finally {
            DbUtils.close(con, pst, rs);
        }
    }

    /**
     * 查询所有的书籍信息
     *
     * @param sql
     * @return 返回一个书籍对象的集合
     */
    public static List<FoodType> findAllFoodType(String sql) {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<FoodType> foodTypes = new ArrayList<>();
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()) {
                    //将数据进行封装
                    Long typeId = rs.getLong("type_id");
                    String typeName = rs.getString("type_name");

                    foodTypes.add(new FoodType(typeId, typeName));
                }
                return foodTypes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return null;
    }


}
