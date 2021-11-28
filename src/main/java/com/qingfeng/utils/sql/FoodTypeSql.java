package com.qingfeng.utils.sql;

import com.qingfeng.pojo.FoodType;
import com.qingfeng.utils.DbUtils;

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
     * 查询菜系
     *
     * @param sql
     * @return 返回一个菜系集合
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
