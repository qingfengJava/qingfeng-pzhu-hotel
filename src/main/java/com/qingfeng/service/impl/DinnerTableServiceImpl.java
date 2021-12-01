package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.service.DinnerTableService;

import java.util.Date;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
public class DinnerTableServiceImpl implements DinnerTableService {

    private DinnerTableDao dinnerTableDao = (DinnerTableDao) BeanFactory.getBean(BeanFactoryConstant.DINNERTABLE_DAO);

    /**
     * 查询餐桌集合
     * @param dinnerTable
     * @return
     */
    @Override
    public List<DinnerTable> findByCondition(DinnerTable dinnerTable) {
        //给程序做容错判断
        if (dinnerTable.getTableName() == null ){
            //为null 设置空值
            dinnerTable.setTableName("");
        }else {
            //不为null 去掉前后空格
            dinnerTable.setTableName(dinnerTable.getTableName().trim());
        }
        return dinnerTableDao.findByCondition(dinnerTable);
    }

    /**
     * 修改餐桌状态信息
     * @param tableId
     */
    @Override
    public void updateStatus(String tableId) throws Exception {
        //1、根据tableId查询餐桌状态
        DinnerTable table = dinnerTableDao.findById(Long.parseLong(tableId));
        //2、修改餐桌状态  如果是0 则改成1  如果是1则改成0  0(空闲) 1(预定)
        Integer tableStatus = table.getTableStatus();
        //保证同一个变量判断
        table.setTableStatus(tableStatus == 0 ? 1 : 0);
        //修改时间  如果是0，预定，就创建一个时间   如果是1，退桌，预定时间置空
        table.setReservationTime(tableStatus == 0 ? new Date() :null);
        dinnerTableDao.updateStatus(table);

    }
}
