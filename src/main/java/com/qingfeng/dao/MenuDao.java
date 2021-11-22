package com.qingfeng.dao;

import com.qingfeng.pojo.Menu;

import java.util.List;

/**
 * 菜单的持久层接口
 *
 * @author 清风学Java
 * @date 2021/11/20
 * @apiNote
 */
public interface MenuDao {

    /**
     * 查询所有菜单列表
     * @return 返回菜单列表的list集合
     */
    List<Menu> findAll();
}
