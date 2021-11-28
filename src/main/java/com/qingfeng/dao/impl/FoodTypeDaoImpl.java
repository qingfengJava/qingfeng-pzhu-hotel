package com.qingfeng.dao.impl;

import com.qingfeng.dao.FoodTypeDao;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.utils.sql.DbSql;
import com.qingfeng.utils.sql.FoodTypeSql;

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
     * @throws SQLException
     */
    @Override
    public void deleteById(long id) throws SQLException {
        String sql = "delete from t_food_type where type_id = "+id;
        DbSql.update(sql);
    }

    /**
     * 添加菜系
     * @param typeName
     * @throws SQLException
     */
    @Override
    public void save(String typeName) throws SQLException {
        if (typeName != null && typeName != "") {
            //给程序做健壮性判断，不允许添加重复的菜系
            String sql = "select * from t_food_type where type_name = '"+typeName+"'";
            List<FoodType> foodTypeList = FoodTypeSql.findAllFoodType(sql);
            if(foodTypeList.size() == 0){
                //说明没有相同的菜系名，可以添加
                String sql2 = "insert into t_food_type(type_name) values('"+typeName+"')";
                DbSql.update(sql2);
            }
        }
    }

    /**
     * 根据id查找
     * @param typeId
     * @return
     */
    @Override
    public FoodType findById(long typeId) {
        String sql = "select * from t_food_type where type_id = "+typeId;
        return FoodTypeSql.findAllFoodType(sql).get(0);
    }

    /**
     * 更新菜系
     * @param foodType
     * @throws SQLException
     */
    @Override
    public void update(FoodType foodType) throws SQLException {
        //程序健壮性判断
        if (foodType.getTypeName() != null && foodType.getTypeName() != ""){
            //健壮性判断，存在相同的名字，则不允许修改
            String sql = "select * from t_food_type where type_name = '"+foodType.getTypeName()+"'";
            List<FoodType> foodTypeList = FoodTypeSql.findAllFoodType(sql);
            if(foodTypeList.size() == 0){
                //查询不到相同的菜系名称时，才允许修改
                String sql2 = "update t_food_type set type_name = '"+foodType.getTypeName()+"' where type_id = "+foodType.getTypeId();
                DbSql.update(sql2);
            }
        }
    }
}
