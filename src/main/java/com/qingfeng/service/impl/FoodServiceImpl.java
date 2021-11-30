package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.FoodDao;
import com.qingfeng.entity.PageBean;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class FoodServiceImpl implements FoodService {

    private FoodDao foodDao = (FoodDao) BeanFactory.getBean(BeanFactoryConstant.FOOD_DAO);

    @Override
    public PageBean<Food> findByCondition(Food food, String _currentPage, String _rows) {
        //根据当前页码和每页的记录数，查询封装相关的数据
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //上一页边界判断
        if (currentPage <= 0){
            currentPage = 1;
        }

        //1、创建空的PageBean对象
        PageBean<Food> pb = new PageBean<>();
        //2、设置参数  页码和每页的记录数
        pb.setRows(rows);

        //这里要对food对象进行判断  对象不为空，值不为空，去掉空格不为空
        if(food != null && food.getFoodName() != null && !food.getFoodName().trim().equals("")){
            //foodName 不是空 去掉空格
            food.setFoodName(food.getFoodName().trim());
        }else {
            //有可能food是null，所以要做处理
            food.setFoodName("");
        }

        //还要判断菜系
        if (food.getFoodType() != null && food.getFoodType().getTypeName() != null && !food.getFoodType().getTypeName().trim().equals("")){
            //typeName（菜系）不是空  去掉空格
            FoodType foodType = new FoodType();
            foodType.setTypeName(food.getFoodType().getTypeName().trim());
            food.setFoodType(foodType);
        }else {
            //有可能foodType是null，做处理
            FoodType foodType = new FoodType();
            foodType.setTypeName("");
            food.setFoodType(foodType);

        }

        //3、调用dao查询总记录数，要根据模糊查询的条件来查询
        int totalCount = foodDao.findTotalCount(food.getFoodName(),food.getFoodType().getTypeName());
        pb.setTotalCount(totalCount);

        //4、计算总页码  总的记录数 除以 每页显示的记录数来判断
        int totalPage = (totalCount % rows == 0)? (totalCount / rows) : (totalCount / rows) + 1;
        //给page对象设置总页码
        pb.setTotalPage(totalPage);

        //下一页边界判断
        if (currentPage >= totalPage){
            currentPage = (totalPage == 0) ? 1: totalPage;
        }
        pb.setCurrentPage(currentPage);

        //计算开始记录的索引
        int start = (currentPage-1)*rows;

        //5、调用dao分页查询，查询每页显示的记录数，要根据模糊查询的条件来查询
        List<Food> list =  foodDao.findFoodCondition(food,start,rows);
        //将查询到的每页的记录数据设置到page对象中
        pb.setList(list);

        return pb;
    }

    /**
     * 添加菜品
     * @param food
     */
    @Override
    public void save(Food food) throws SQLException {
        //添加、保存数据操作
        foodDao.save(food);
    }

    /**
     * 根据菜品Id，查询菜品信息
     * @param foodId
     * @return
     */
    @Override
    public Food findFoodById(String foodId) {
        return foodDao.findFoodById(Long.parseLong(foodId));
    }

    /**
     * 根据菜品id，修改菜品信息
     * @param food
     */
    @Override
    public void updateFoodById(Food food) throws SQLException {
        //直接调用持久层修改菜品的信息
        foodDao.updateFoodById(food);
    }

    /**
     * 根据菜品Id删除菜品信息
     * @param foodId
     */
    @Override
    public void deleteFood(String foodId) throws SQLException {
        //调用持久层删除菜品信息的方法
        foodDao.deleteFood(Long.parseLong(foodId));
    }
}
