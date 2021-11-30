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
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Food> findFoodCondition(Food food,int start,int rows) {
        String sql = "select f.*,ft.type_name from t_food f,t_food_type ft where f.type_id = ft.type_id and ft.type_name like '%"+food.getFoodType().getTypeName()+"%' and f.food_name like '%"+food.getFoodName()+"%' order by f.food_id desc limit "+start+","+rows;
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
        //复用之前按条件查询的SQL代码
        return FoodSql.findCondition(sql).get(0);
    }

    /**
     * 根据菜品id，修改菜品信息
     * @param food
     */
    @Override
    public void updateFoodById(Food food) throws SQLException {
        String sql = "update t_food set type_id="+food.getTypeId()+",food_name='"+food.getFoodName()+"'," +
                "food_price="+food.getFoodPrice()+",food_mprice="+food.getFoodMprice()+",food_image='"+food.getFoodImage()+"'," +
                "food_desc='"+food.getFoodDesc()+"' where food_id = "+food.getFoodId();
        DbSql.update(sql);
    }

    @Override
    public void deleteFood(long foodId) throws SQLException {
        String sql = "delete from t_food where food_id = "+foodId;
        DbSql.update(sql);
    }

    @Override
    public int findTotalCount(String foodName, String typeName) {
        String sql = "select count(*) from t_food f,t_food_type ft where f.type_id = ft.type_id and ft.type_name like '%"+typeName+"%' and f.food_name like '%"+foodName+"%'";
        return FoodSql.findTotalCount(sql);
    }
}
