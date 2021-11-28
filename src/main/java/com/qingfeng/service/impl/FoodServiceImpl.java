package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.FoodDao;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodService;

import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class FoodServiceImpl implements FoodService {

    private FoodDao foodDao = (FoodDao) BeanFactory.getBean(BeanFactoryConstant.FOOD_DAO);

    @Override
    public List<Food> findByCondition(Food food) {
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
        return foodDao.findFoodCondition(food);
    }
}
