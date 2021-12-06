package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.ExceptionMessageConstant;
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

    /**
     * 根据餐桌名，保存餐桌信息
     * @param tableName
     */
    @Override
    public void save(String tableName) {
        //判断餐桌名是否重复
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.setTableName(tableName);
        List<DinnerTable> list = dinnerTableDao.findByCondition(dinnerTable);
        //测试代码
        System.out.println(list);
        //如果list的长度是0，说明没哟查到数据，可以添加
        if (list.size() == 0) {
            //调用持久层添加餐桌的方法
            dinnerTableDao.save(tableName);
        }else{
            //重复，抛出一个不可添加的异常  "不允许添加重复餐桌"
            throw new RuntimeException(ExceptionMessageConstant.DINNERTABLE_ADD_FAIL_MESSAGE);
        }
    }

    /**
     * 根据餐桌Id删除餐桌信息
     * @param tableId
     */
    @Override
    public void deleteById(int tableId) {
        try {
            dinnerTableDao.deleteById(tableId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
