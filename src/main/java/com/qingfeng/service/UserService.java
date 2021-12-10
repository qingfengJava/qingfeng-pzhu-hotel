package com.qingfeng.service;

import com.qingfeng.entity.ResultVO;
import com.qingfeng.pojo.User;

/**
 * 用户的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户登录的方法
     * @param username
     * @param password
     * @return
     */
    ResultVO login(String username, String password);

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
     * 根据用户Id，查询用户信息
     * @param userId
     * @return
     */
    User findByUserId(Long userId);

    /**
     * 根据用户id，修改用户余额
     * @param userId
     * @param price
     */
    void updatePrice(Long userId, double price);

    /**
     * 给登录成功的次数加一
     * @param loginUser
     */
    void addLoginNum(User loginUser);
}
