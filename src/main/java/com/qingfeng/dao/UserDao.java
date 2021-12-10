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

    /**
     * 注册用户
     * @param user
     * @return 返回1-表示注册成功  返回0-表示注册失败
     */
    int register(User user);

    /**
     * 根据用户名，更新密码
     * @param username
     * @param password
     */
    void updateByUserName(String username, String password);

    /**
     * 用户支付
     * @param money
     * @param userId
     */
    void updateBalance(double money, Long userId);
}
