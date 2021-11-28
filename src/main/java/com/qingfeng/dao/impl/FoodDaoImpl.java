package com.qingfeng.dao.impl;

import com.qingfeng.dao.FoodDao;
import com.qingfeng.pojo.Food;
import com.qingfeng.utils.sql.FoodSql;

import java.util.List;

/**
 * 菜品持久层的实现
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class FoodDaoImpl implements FoodDao {

    /**
     * 根据条件查询对应的菜品
     * 可根据菜名，菜系条件查询
     * @param food
     * @return
     */
    @Override
    public List<Food> findFoodCondition(Food food) {
        String sql = "select f.*,ft.type_name from t_food f,t_food_type ft where f.type_id = ft.type_id and ft.type_name like '%"+food.getFoodType().getTypeName()+"%' and f.food_name like '%"+food.getFoodName()+"%'";
        //调用封装的查询的方法，进行查询
        return FoodSql.findCondition(sql);
    }
}
