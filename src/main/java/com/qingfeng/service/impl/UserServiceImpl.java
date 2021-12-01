package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.UserDao;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.User;
import com.qingfeng.service.UserService;
import com.qingfeng.utils.Md5Utils;

/**
 * 用户的接口层实现
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactoryConstant.USER_USERDAO);

    @Override
    public ResultVO login(String username, String password) {
        //1、根据用户名查询用户信息，不能删除
        User user = userDao.findByUsername(username);
        //2、判断
        if (user != null) {
            //2.1 状态是否激活
            if (user.getUserStatus().intValue() != 1){
                return new ResultVO(false,"status_is_error",null);
            }
            //2.2 账户是否删除
            if (user.getIsDelete().intValue() != 0){
                return new ResultVO(false,"status_is_deleted",null);
            }
            //2.3 密码是否正确
            if (!user.getPassword().equals(Md5Utils.md5(password))){
                return new ResultVO(false,"username_or_password_is_error",null);
            }
        }else{
            return new ResultVO(false,"user_is_not_exists",null);
        }


        //3、返回用户信息
        return new ResultVO("登录成功",user);
    }
}
