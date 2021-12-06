package com.qingfeng.dao.impl;

import com.qingfeng.constant.ExceptionMessageConstant;
import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.utils.sql.DbSql;
import com.qingfeng.utils.sql.DinnerTableSql;

import java.sql.SQLException;
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

    /**
     * 根据餐桌状态查询餐桌信息
     * @param tableStatus
     * @return
     * @throws SQLException
     */
    @Override
    public List<DinnerTable> findTablesByStatus(int tableStatus) throws SQLException {
        String sql = "select * from t_dinner_table where table_status ="+tableStatus;
        return DinnerTableSql.findCondition(sql);
    }

    @Override
    public void save(String tableName) {
        //添加餐桌，默认都是空闲，时间为空
        String sql = "insert into t_dinner_table(table_name,table_status,reservation_time) values('"+tableName+"',0,null)";
        try {
            DbSql.update(sql);
        } catch (SQLException e) {
            //"添加餐桌出现未知的异常！！！"
            throw new RuntimeException(ExceptionMessageConstant.DINNERTABLE_ADD_UNKONE_MESSAGE);
        }
    }

    @Override
    public void deleteById(int tableId) {
        try {
            String sql = "delete from t_dinner_table where table_id = "+tableId;
            DbSql.update(sql);
        } catch (SQLException e) {
            //"删除餐桌出现未知的异常"
            throw new RuntimeException(ExceptionMessageConstant.DINNERTABLE_DELETE_FAIL_MESSAGE);
        }
    }
}
