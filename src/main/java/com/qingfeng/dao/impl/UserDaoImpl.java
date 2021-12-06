package com.qingfeng.dao.impl;

import com.qingfeng.dao.UserDao;
import com.qingfeng.pojo.User;
import com.qingfeng.utils.sql.DbSql;
import com.qingfeng.utils.sql.UserSql;

import java.sql.SQLException;

/**
 * 用户持久层接口的实现
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
public class UserDaoImpl implements UserDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        String sql = "select * from t_user where username = '"+username+"'";
        return UserSql.findUserByName(sql);
    }

    @Override
    public int register(User user) {
        try {
            String sql = "insert into t_user(username,password,nick_name,is_admin,phone,gender,user_status,user_create_time,user_update_time,is_delete,is_member,balance) " +
                    "values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getNickName()+"',"+user.getIsAdmin()+",'"+user.getPhone()+"'," +
                    user.getGender()+","+user.getUserStatus()+",'"+user.getUserCreateTimeStr()+"','"+user.getUserUpdateTimeStr()+"',"+user.getIsDelete()+","+user.getIsMember()+","+user.getBalance()+")";
            return UserSql.insert(sql);
        } catch (Exception e) {
            throw new RuntimeException("注册用户出现未知的异常");
        }
    }

    @Override
    public void updateByUserName(String username, String password) {
        try {
            String sql = "update t_user set password ='"+password+"' where username = '"+username+"'";
            DbSql.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新/找回密码时出现未知的异常！");
        }
    }
}
