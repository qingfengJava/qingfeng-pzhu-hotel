package com.qingfeng.dao.impl;

import com.qingfeng.dao.FoodTypeDao;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.utils.FoodTypeSql;

import java.sql.SQLException;
import java.util.List;

/**
 * 菜系管理持久层的实现
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public class FoodTypeDaoImpl implements FoodTypeDao {

    /**
     * 根据条件查询——菜系名称
     * @param foodType 菜系类
     * @return
     */
    @Override
    public List<FoodType> findCondition(FoodType foodType) {
        //因为后台的条件查询是根据菜系名称进行查询的，所以这里判断菜系名称
        String sql = "select * from t_food_type where type_name like '%"+foodType.getTypeName()+"%'";
        return FoodTypeSql.findAllFoodType(sql);
    }

    /**
     * 根据菜系id，删除相应的菜系
     * @param id
     */
    @Override
    public void deleteById(long id) throws SQLException {
        String sql = "delete from t_food_type where type_id = "+id+"";
        FoodTypeSql.updateFoodType(sql);
    }
}
