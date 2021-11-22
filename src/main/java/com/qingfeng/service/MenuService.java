package com.qingfeng.service;

import com.qingfeng.pojo.Menu;

import java.util.List;

/**
 * 菜单的业务层接口
 *
 * @author 清风学Java
 * @date 2021/11/21
 * @version 1.0.1
 */
public interface MenuService {

    /**
     * 查询所有菜单列表
     * @return 返回菜单列表的集合
     */
    List<Menu> findAll();
}
