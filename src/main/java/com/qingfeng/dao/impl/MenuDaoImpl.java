package com.qingfeng.dao.impl;

import com.qingfeng.dao.MenuDao;
import com.qingfeng.pojo.Menu;
import com.qingfeng.utils.sql.MenuSql;

import java.util.List;

/**
 * 菜单持久层实现
 *
 * @author 清风学Java
 * @date 2021/11/20
 * @apiNote
 */
public class MenuDaoImpl implements MenuDao {

    /**
     * 查询所有菜单列表
     * @return
     */
    @Override
    public List<Menu> findAll() {
        //定义sql语句，查询所有菜单列表
        String sql = "select * from t_menu";
        return MenuSql.findAllMenu(sql);
    }
}
