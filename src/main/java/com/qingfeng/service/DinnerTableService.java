package com.qingfeng.service;

import com.qingfeng.pojo.DinnerTable;

import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public interface DinnerTableService {

    /**
     * 查询餐桌集合
     * @param dinnerTable
     * @return
     */
    List<DinnerTable> findByCondition(DinnerTable dinnerTable);

    /**
     * 修改餐桌状态信息
     * @param tableId
     */
    void updateStatus(String tableId) throws Exception;

    /**
     * 根据餐桌名，保存餐桌信息
     * @param tableName
     */
    void save(String tableName);

    /**
     * 根据餐桌id删除餐桌信息
     * @param tableId
     */
    void deleteById(int tableId);

    /**
     * 根据餐桌状态修改餐桌信息
     * @param tableId
     */
    void updateStatusByTableId(Long tableId) throws Exception;
}
