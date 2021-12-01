package com.qingfeng.dao.impl;

import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.utils.sql.DbSql;
import com.qingfeng.utils.sql.DinnerTableSql;

import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public class DinnerTableDaoImpl implements DinnerTableDao {

    /**
     * 根据条件查询餐桌集合
     * @param dinnerTable
     * @return
     */
    @Override
    public List<DinnerTable> findByCondition(DinnerTable dinnerTable) {
        String sql = "select * from t_dinner_table where table_name like '%"+dinnerTable.getTableName()+"%'";
        return DinnerTableSql.findCondition(sql);
    }

    /**
     * 查找餐桌信息
     * @param tableId
     * @return
     */
    @Override
    public DinnerTable findById(long tableId) {
        String sql = "select * from t_dinner_table where table_id ="+tableId;
        return DinnerTableSql.findCondition(sql).get(0);
    }

    /**
     * 修改餐桌状态信息
     * @param table
     */
    @Override
    public void updateStatus(DinnerTable table) throws Exception {
        String sql = null;
        if (table.getReservationTimeStr() != null) {
            sql = "update t_dinner_table set table_status = " + table.getTableStatus() + ",reservation_time= '" + table.getReservationTimeStr() + "' where table_id = " + table.getTableId();
        }else{
            sql = "update t_dinner_table set table_status = " + table.getTableStatus() + ",reservation_time= " + table.getReservationTimeStr() + " where table_id = " + table.getTableId();
        }
        DbSql.update(sql);
    }
}
