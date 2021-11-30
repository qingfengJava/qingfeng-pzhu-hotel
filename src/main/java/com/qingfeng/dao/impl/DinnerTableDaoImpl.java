package com.qingfeng.dao.impl;

import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.pojo.DinnerTable;
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
        System.out.println("-----------"+dinnerTable.getTableName());
        String sql = "select * from t_dinner_table where table_name like '%"+dinnerTable.getTableName()+"%'";
        return DinnerTableSql.findCondition(sql);
    }
}
