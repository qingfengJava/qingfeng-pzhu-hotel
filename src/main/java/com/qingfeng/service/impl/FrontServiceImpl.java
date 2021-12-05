package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.service.FrontService;

import java.sql.SQLException;
import java.util.List;

/**
 * 前台数据的业务层实现
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/5
 */
public class FrontServiceImpl implements FrontService {

    DinnerTableDao dinnerTableDao = (DinnerTableDao) BeanFactory.getBean(BeanFactoryConstant.DINNERTABLE_DAO);

    @Override
    public ResultVO findTablesByStatus(String tableStatus) throws Exception {
        try {
            List<DinnerTable> tables = dinnerTableDao.findTablesByStatus(Integer.parseInt(tableStatus));
            return new ResultVO("可用餐桌查询成功！", tables);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResultVO(false,"可用餐桌查询失败！", "餐桌状态不对");
        }catch (SQLException e){
            e.printStackTrace();
            return new ResultVO(false,"可用餐桌查询失败！", "SQL出异常了");
        }
    }
}
