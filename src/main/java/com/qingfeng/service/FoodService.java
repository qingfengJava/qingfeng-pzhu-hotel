package com.qingfeng.service;

import com.qingfeng.pojo.Food;

import java.sql.SQLException;
import java.util.List;

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
    List<Food> findByCondition(Food food);

    /**
     * 添加菜品
     * @param food
     * @throws SQLException
     */
    void save(Food food) throws SQLException;

    /**
     * 根据菜品Id，查询菜品对象
     * @param foodId
     * @return 返回一个菜品对象
     */
    Food findFoodById(String foodId);

    /**
     * 根据菜品id，修改菜品信息
     * @param food
     */
    void updateFoodById(Food food) throws SQLException;
}
