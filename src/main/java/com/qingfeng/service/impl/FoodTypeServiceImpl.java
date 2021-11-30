package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.ExceptionMessageConstant;
import com.qingfeng.dao.FoodDao;
import com.qingfeng.dao.FoodTypeDao;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodTypeService;

import java.sql.SQLException;
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
    private FoodDao foodDao = (FoodDao) BeanFactory.getBean(BeanFactoryConstant.FOOD_DAO);

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
    public ResultVO deleteById(String typeId) throws Exception {
        //异常处理，保证程序的健壮性
        try {
            //删除菜系之前，要根据菜系id查询有没有关联的菜品   有关联的 不能删除  没有关联的，可以删除
            if(foodDao.countByTypeId(Long.parseLong(typeId))>0){
                //查询的总的记录数大于0，不可以删除
                return new ResultVO(false,ExceptionMessageConstant.FOODTYPE_DELETE_FAIL_MESSAGE,null);
            }
            foodTypeDao.deleteById(Long.parseLong(typeId));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //成功
        return new ResultVO();
    }

    /**
     * 添加菜系
     * @param typeName
     * @return
     */
    @Override
    public int save(String typeName) {
        try {
            if (typeName !=null && typeName != ""){
                return foodTypeDao.save(typeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回1 表示添加失败
        return 1;
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
