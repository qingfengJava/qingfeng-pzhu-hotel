package com.qingfeng.utils.sql;

import com.qingfeng.pojo.User;
import com.qingfeng.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户的sql封装
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
public class UserSql {

    public static User findUserByName(String sql){
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()){
                    user = new User();
                    user.setUserId(rs.getLong("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setNickName(rs.getString("nick_name"));
                    user.setIsAdmin(rs.getInt("is_admin"));
                    user.setPhone(rs.getString("phone"));
                    user.setGender(rs.getInt("gender"));
                    user.setUserStatus(rs.getInt("user_status"));
                    user.setUserCreateTime(rs.getTimestamp("user_create_time"));
                    user.setUserUpdateTime(rs.getTimestamp("user_update_time"));
                    user.setIsDelete(rs.getInt("is_delete"));
                    user.setIsMember(rs.getInt("is_member"));
                    user.setBalance(rs.getDouble("balance"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(con, pst,rs);
        }
        return user;
    }
}
