package com.qingfeng.dao.impl;

import com.qingfeng.dao.FoodDao;
import com.qingfeng.pojo.Food;
import com.qingfeng.utils.sql.DbSql;
import com.qingfeng.utils.sql.FoodSql;

import java.sql.SQLException;
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

    /**
     * 保存或者添加菜品
     * @param food
     */
    @Override
    public void save(Food food) throws SQLException {
        String sql = "insert into t_food(type_id,food_name,food_price,food_mprice,food_image,food_desc)" +
                " values("+food.getTypeId()+",'"+food.getFoodName()+"',"+food.getFoodPrice()+","+food.getFoodMprice()+",'"+food.getFoodImage()+"','"+food.getFoodDesc()+"')";
        DbSql.update(sql);
    }

    /**
     * 根据菜品Id，查询菜品信息，用于更新时回显数据局
     * @param foodId
     * @return
     */
    @Override
    public Food findFoodById(long foodId) {
        String sql = "select f.*,ft.type_name from t_food f,t_food_type ft where f.food_id = "+foodId+" and f.type_id = ft.type_id";
        return null;
    }
}
