package com.qingfeng.service;

import com.qingfeng.entity.ResultVO;
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
     * @throws Exception
     */
    ResultVO deleteById(String typeId) throws Exception;

    /**
     * 添加菜系
     * @param typeName
     */
    void save(String typeName);

    /**
     * 根据id查询菜系
     * @param typeId
     * @return
     */
    FoodType findById(String typeId);

    /**
     * 修改菜系信息
     * @param foodType
     */
    void update(FoodType foodType);
}
