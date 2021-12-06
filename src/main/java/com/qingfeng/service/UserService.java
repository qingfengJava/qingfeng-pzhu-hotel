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
}
