package com.qingfeng.service;

import com.qingfeng.pojo.Food;

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
}
