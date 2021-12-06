package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.dao.FoodDao;
import com.qingfeng.entity.PageBean;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.pojo.Food;
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
    private FoodDao foodDao = (FoodDao) BeanFactory.getBean(BeanFactoryConstant.FOOD_DAO);

    @Override
    public ResultVO findTablesByStatus(String tableStatus) throws Exception {
        try {
            List<DinnerTable> tables = dinnerTableDao.findTablesByStatus(Integer.parseInt(tableStatus));
            return new ResultVO(MessageConstant.USEABLE_TABLE, tables);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ResultVO(false,MessageConstant.USERABLE_TABLE_FIND_FAIL, MessageConstant.TABLE_STATUS_ERRO);
        }catch (SQLException e){
            e.printStackTrace();
            return new ResultVO(false,MessageConstant.USEABLE_TABLE_FAIL,MessageConstant.SQL_EX );
        }
    }

    @Override
    public PageBean<Food> findByPage(String _currentPage, String _rows) {
        //封装一个完整的pageBean并返回
        //根据当前页码和每页的记录数，查询封装相关的数据
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //上一页边界判断
        if (currentPage <= 0){
            currentPage = 1;
        }

        //1、创建空的PageBean对象
        PageBean<Food> pb = new PageBean<>();
        //2、设置参数  页码和每页的记录数
        pb.setRows(rows);

        //3、调用dao查询总记录数，要根据模糊查询的条件来查询  查询所有，因此传两个空值
        int totalCount = foodDao.findTotalCount("","");
        pb.setTotalCount(totalCount);

        //4、计算总页码  总的记录数 除以 每页显示的记录数来判断
        int totalPage = (totalCount % rows == 0)? (totalCount / rows) : (totalCount / rows) + 1;
        //给page对象设置总页码
        pb.setTotalPage(totalPage);

        //下一页边界判断
        if (currentPage >= totalPage){
            currentPage = (totalPage == 0) ? 1: totalPage;
        }
        pb.setCurrentPage(currentPage);

        //计算开始记录的索引
        int start = (currentPage-1)*rows;

        //5、调用dao分页查询，查询每页显示的记录数，要根据模糊查询的条件来查询
        List<Food> foodList =  foodDao.findFoodCondition(null,start,rows);
        //将查询到的每页的记录数据设置到page对象中
        pb.setList(foodList);

        return pb;
    }
}
