package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.service.DinnerTableService;

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
}
