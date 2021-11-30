package com.qingfeng.utils.sql;

import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.utils.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public class DinnerTableSql {

    /**
     * 根据条件查询餐桌的集合
     * @param sql
     * @return
     */
    public static List<DinnerTable> findCondition(String sql) {
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<DinnerTable> dinnerTables = new ArrayList<>();
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()) {
                    //将数据进行封装
                    long tableId = rs.getLong("table_id");
                    String tableName = rs.getString("table_name");
                    int tableStatus = rs.getInt("table_status");
                    Date reservationTime = rs.getDate("reservation_time");
                    //将数据封装到对象中
                    dinnerTables.add(new DinnerTable(tableId,tableName,tableStatus,reservationTime));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, pst, rs);
        }
        return dinnerTables;
    }
}
