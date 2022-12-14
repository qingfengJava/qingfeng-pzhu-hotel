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
        String sql = null;
        if (food != null && food.getFoodType() != null){
            //后台的分页条件查询
            sql = "select f.*,ft.type_name from t_food f,t_food_type ft where f.type_id = ft.type_id and ft.type_name like '%"+food.getFoodType().getTypeName()+"%' and f.food_name like '%"+food.getFoodName()+"%' order by f.food_id desc limit "+start+","+rows;
        }else{
            //前台直接查询所有记录  注意：这里还要对typeId做判断，防止空值出现
            if (food == null){
                //查询所有
                sql = "select f.*,ft.type_name from t_food f,t_food_type ft where f.type_id = ft.type_id limit "+start+","+rows;
            }else{
                //条件查询
                sql = "select f.*,ft.type_name from t_food f,t_food_type ft where f.type_id = ft.type_id and f.type_id = "+food.getTypeId()+" limit "+start+","+rows;
            }
        }
        //调用封装的查询的方法，进行查询
        return FoodSql.findCondition(sql);
    }

    /**
     * 保存或者添加菜品
     * @param food
     * @return
     */
    @Override
    public int save(Food food) throws SQLException {
        //判断菜品名称是否已经存在
        String sql = "select * from t_food where food_name = '"+food.getFoodName()+"' and type_id = "+food.getTypeId();
        List<Food> condition = FoodSql.findCondition(sql);
        if (condition.size() == 0){
            //说明菜品不存在，可以添加
            String sql2 = "insert into t_food(type_id,food_name,food_price,food_mprice,food_image,food_desc)" +
                    " values("+food.getTypeId()+",'"+food.getFoodName()+"',"+food.getFoodPrice()+","+food.getFoodMprice()+",'"+food.getFoodImage()+"','"+food.getFoodDesc()+"')";
            DbSql.update(sql2);
            //返回0 说明添加成功
            return 0;
        }
        //说明菜名重复，添加失败
        return 1;
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

    /**
     * 重载一个根据菜品id查询总条数的方法
     * @param typeId
     * @return
     */
    @Override
    public int findTotalCount(String typeId) {
        String sql = null;
        if (typeId == "") {
            sql = "select count(*) from t_food";
        }else {
            sql = "select count(*) from t_food where type_id = "+Long.parseLong(typeId);
        }
        return FoodSql.findTotalCount(sql);
    }

    @Override
    public int countByTypeId(long typeId) {
        String sql = "select count(*) from t_food where type_id = "+typeId;
        return FoodSql.findTotalCount(sql);
    }
}
