package com.qingfeng.constant;

/**
 * 异常信息的常量数据封装
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public interface ExceptionMessageConstant {

    /**
     * 菜系删除失败的提示信息
     */
    String FOODTYPE_DELETE_FAIL_MESSAGE = "当前菜系下有关联的菜品，无法删除！！！如果要删除，请先删除关联的菜品数据！！！";

    /**
     * 菜系添加失败的提示信息
     */
    String FOODTYPE_ADD_FAIL_MESSAGE = "菜系已存在，不允许添加！！！";

    /**
     * 菜品添加失败的提示信息
     */
    String FOOD_ADD_FAIL_MESSAGE = "菜品已存在，不允许添加！！！";

    /**
     * 菜品添加异常的提示信息
     */
    String FOOD_ADD_EXCEPTION_MESSAGE = "添加菜品异常，请稍后再试！";

    /**
     * 添加餐桌异常的提示信息
     */
    String DINNERTABLE_ADD_FAIL_MESSAGE = "不允许添加重复的餐桌！";

    /**
     * 添加餐桌出现未知的异常
     */
    String DINNERTABLE_ADD_UNKONE_MESSAGE = "添加餐桌出现未知的异常！！！";

    /**
     * 删除餐桌出现未知的异常！！！
     */
    String DINNERTABLE_DELETE_FAIL_MESSAGE = "删除餐桌出现未知的异常！！！";
}
