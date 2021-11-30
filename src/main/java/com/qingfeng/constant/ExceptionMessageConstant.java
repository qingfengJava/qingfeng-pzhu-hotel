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
}
