package com.qingfeng.service;

import com.qingfeng.entity.PageBean;
import com.qingfeng.pojo.Food;

import java.sql.SQLException;

/**
 * 菜品的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public interface FoodService {

    /**
     * 根据条件查询对应的菜品
     * 可根据菜名，菜系条件查询
     * @param food
     * @return
     */
    PageBean<Food> findByCondition(Food food, String _currentPage, String _rows);

    /**
     * 添加菜品
     * @param food
     * @throws SQLException
     * @return
     */
    int save(Food food) throws SQLException;

    /**
     * 根据菜品Id，查询菜品对象
     * @param foodId
     * @return 返回一个菜品对象
     */
    Food findFoodById(String foodId);

    /**
     * 根据菜品id，修改菜品信息
     * @param food
     * @throws SQLException
     */
    void updateFoodById(Food food) throws SQLException;

    /**
     * 根据菜品Id删除菜品信息
     * @param foodId
     */
    void deleteFood(String foodId) throws SQLException;
}
