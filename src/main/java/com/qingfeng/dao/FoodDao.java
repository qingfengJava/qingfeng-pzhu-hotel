package com.qingfeng.dao;

import com.qingfeng.pojo.Food;

import java.sql.SQLException;
import java.util.List;

/**
 * 菜品的持久层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public interface FoodDao {

    /**
     * 根据条件查询对应的菜品
     * 可根据菜名，菜系条件查询
     * @param food
     * @param start
     * @param rows
     * @return
     */
    List<Food> findFoodCondition(Food food, int start, int rows);

    /**
     * 保存/添加菜品
     * @param food
     * @return
     */
    int save(Food food) throws SQLException;

    /**
     * 根据菜品Id，查询菜品信息，用于更新时回显数据局
     * @param foodId
     * @return
     */
    Food findFoodById(long foodId);

    /**
     * 根据菜品id，修改菜品信息
     * @param food
     * @throws SQLException
     */
    void updateFoodById(Food food) throws SQLException;

    /**
     * 根据菜品id删除菜品信息
     * @param parseLong
     * @throws SQLException
     */
    void deleteFood(long parseLong) throws SQLException;

    /**
     * 条件查询菜品总的记录数
     * @param foodName
     * @param typeName
     * @return
     */
    int findTotalCount(String foodName, String typeName);

    /**
     * 根据菜系id，查询菜品总的记录数
     * @param typeId
     * @return
     */
    int countByTypeId(long typeId);
}
