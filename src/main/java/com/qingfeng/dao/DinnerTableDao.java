package com.qingfeng.dao;

import com.qingfeng.pojo.DinnerTable;

import java.sql.SQLException;
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

    /**
     * 查找餐桌信息
     * @param tableId
     * @return
     */
    DinnerTable findById(long tableId);

    /**
     * 修改餐桌状态信息
     * @param table table实体
     * @throws Exception
     */
    void updateStatus(DinnerTable table) throws Exception;

    /**
     * 根据餐桌状态查询餐桌信息
     * @param tableStatus
     * @return
     * @throws SQLException
     */
    List<DinnerTable> findTablesByStatus(int tableStatus) throws SQLException;
}
