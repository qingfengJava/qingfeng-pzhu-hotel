package com.qingfeng.utils.sql;

import com.qingfeng.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通用增删改
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class DbSql {

    /**
     * 通用的增，删，改的方法
     *
     * @param sql
     */
    public static void update(String sql) throws SQLException {
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
}
