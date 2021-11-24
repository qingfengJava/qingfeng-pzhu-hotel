package com.qingfeng.service;

import com.qingfeng.pojo.FoodType;

import java.util.List;

/**
 * 菜系管理的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public interface FoodTypeService {

    /**
     * 根据条件查询菜系
     * @param foodType 菜系类
     * @return
     */
    List<FoodType> findCondition(FoodType foodType);

    /**
     * 根据菜系Id 删除菜系
     * @param typeId
     */
    void deleteById(String typeId) throws Exception;
}
