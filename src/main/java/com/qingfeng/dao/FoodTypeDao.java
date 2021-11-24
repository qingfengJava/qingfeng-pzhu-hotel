package com.qingfeng.dao;

import com.qingfeng.pojo.FoodType;

import java.sql.SQLException;
import java.util.List;

/**
 * 菜系管理的持久层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public interface FoodTypeDao {

    /**
     * 根据条件查询菜系
     * @param foodType 菜系类
     * @return
     */
    List<FoodType> findCondition(FoodType foodType);

    /**
     * 根据菜系id，删除相应的菜系
     * @param id
     * @throws SQLException
     */
    void deleteById(long id) throws SQLException;

    /**
     * 添加菜系
     * @param typeName
     * @throws SQLException
     */
    void save(String typeName) throws SQLException;
}
