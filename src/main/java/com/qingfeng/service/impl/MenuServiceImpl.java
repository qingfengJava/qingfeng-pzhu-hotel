package com.qingfeng.service.impl;

import com.qingfeng.dao.MenuDao;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Menu;
import com.qingfeng.service.MenuService;

import java.util.List;

/**
 * 菜单业务层接口的实现
 *
 * @author 清风学Java
 * @date 2021/11/21
 * @apiNote
 */
public class MenuServiceImpl implements MenuService {

    /**
     * 业务层维护持久层对象
     */
    private MenuDao menuDao = (MenuDao) BeanFactory.getBean("menuDao");

    /**
     * 查询所有菜单列表
     * @return 返回所有的菜单集合
     */
    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }
}
