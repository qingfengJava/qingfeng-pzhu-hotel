package com.qingfeng.dao.impl;

import com.qingfeng.dao.UserDao;
import com.qingfeng.pojo.User;
import com.qingfeng.utils.sql.UserSql;

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
}
