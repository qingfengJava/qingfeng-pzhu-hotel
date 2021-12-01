package com.qingfeng.dao;

import com.qingfeng.pojo.User;

/**
 * 用户的持久层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
public interface UserDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
