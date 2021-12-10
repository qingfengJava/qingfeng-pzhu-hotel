package com.qingfeng.constant;

/**
 * 常量接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/26
 */
public interface MessageConstant {

    /**
     * 错误提示信息
     */
    String COMMON_ERROR_MESSAGE = "服务器正忙，请稍后重试！！！";

    /**
     * 请求转发前缀
     */
    String PREFIX_FORWAED = "forward:";

    /**
     * 重定向前缀
     */
    String PREFIX_REDIRECT = "redirect:";

    /**
     * 标志符
     */
    String TAG = ":";

    /**
     * 方法参数：method
     */
    String PARAM_METHOD = "method";
    String FILEUPLOAD_ERROR = "上传文件不能为空！！！";

    /**
     * 餐桌的提示信息
     */
    String USEABLE_TABLE = "可用餐桌查询成功！";
    String USERABLE_TABLE_FIND_FAIL = "可用餐桌查询失败！";
    String TABLE_STATUS_ERRO = "餐桌状态不对";
    String USEABLE_TABLE_FAIL = "可用餐桌查询失败！";
    String SQL_EX = "SQL出异常了";

    /**
     * 菜单提示信息
     */
    String MENU_FIND_SUCCESS = "菜单查询成功";

    /**
     * 用户页面提示信息
     */
    String CODE_ERROR = "验证码输入错误！";
    String REGISTER_SUCCESS = "恭喜你，注册成功，马上登录吧！";
    String REGISTER_FAIL = "用户已存在，注册失败！";
    String UPDATE_PASSWORD_SUCCESS = "恭喜你，密码更新/找回成功，马上登录吧！";
    String UPDATE_USER_FAIL = "用户不存在，不能修改/找回密码！";
    String LOGIN_SUCCESS = "登录成功";

    String UNLOGIN_USER = "未登录，无法加入餐车，请先登录！！！";
    String NO_CHOOSE_TABLE = "您还没有选择餐桌，不能下单！！！";

    String USER_NOT_CANCHE = "当前用于没有对应的餐车！！！";
}
