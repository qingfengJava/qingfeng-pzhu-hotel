package com.qingfeng.service.impl;

import com.qingfeng.dao.FoodTypeDao;
import com.qingfeng.dao.impl.FoodTypeDaoImpl;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodTypeService;

import java.util.List;

/**
 * 菜系管理的业务层接口实现
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public class FoodTypeServiceImpl implements FoodTypeService {

    private FoodTypeDao foodTypeDao = new FoodTypeDaoImpl();

    @Override
    public List<FoodType> findCondition(FoodType foodType) {
        //这里要对foodType对象进行判断  对象不为空，值不为空，去掉空格不为空
        if(foodType != null && foodType.getTypeName() != null && !foodType.getTypeName().trim().equals("")){
            //typeName 不是空
            foodType.setTypeName(foodType.getTypeName().trim());
        }else {
            //有可能foodType是null，所以要做处理
            foodType.setTypeName("");
        }

        return foodTypeDao.findCondition(foodType);
    }
}
