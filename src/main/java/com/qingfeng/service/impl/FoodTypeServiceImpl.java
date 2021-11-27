package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.FoodTypeDao;
import com.qingfeng.factory.BeanFactory;
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

    /**
     * 通过自己封装的工厂类来创建对象
     */
    private FoodTypeDao foodTypeDao = (FoodTypeDao) BeanFactory.getBean(BeanFactoryConstant.FOODTYPE_DAO);

    /**
     * 根据条件查询菜系
     * @param foodType 菜系类
     * @return
     */
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

    /**
     * 根据菜系id 删除菜系
     * @param typeId
     * @throws Exception
     */
    @Override
    public void deleteById(String typeId) throws Exception {
        //异常处理，保证程序的健壮性
        try {
            foodTypeDao.deleteById(Long.parseLong(typeId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加菜系
     * @param typeName
     */
    @Override
    public void save(String typeName) {
        try {
            if (typeName !=null && typeName != ""){
                foodTypeDao.save(typeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询菜系
     * @param typeId
     * @return
     */
    @Override
    public FoodType findById(String typeId) {
        try {
            return foodTypeDao.findById(Long.parseLong(typeId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改菜系信息
     * @param foodType
     */
    @Override
    public void update(FoodType foodType) {
        try {
            foodTypeDao.update(foodType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
