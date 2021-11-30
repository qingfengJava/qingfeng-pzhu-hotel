package com.qingfeng.dao;

import com.qingfeng.pojo.DinnerTable;

import java.util.List;

/**
 * 餐桌的持久层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public interface DinnerTableDao {

    /**
     * 查询餐桌集合
     * @param dinnerTable
     * @return
     */
    List<DinnerTable> findByCondition(DinnerTable dinnerTable);
}
