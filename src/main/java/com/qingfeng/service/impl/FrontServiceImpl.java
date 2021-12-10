package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.dao.DinnerTableDao;
import com.qingfeng.dao.FoodDao;
import com.qingfeng.dao.OrderDao;
import com.qingfeng.dao.UserDao;
import com.qingfeng.entity.PageBean;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.Orders;
import com.qingfeng.pojo.User;
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

    private DinnerTableDao dinnerTableDao = (DinnerTableDao) BeanFactory.getBean(BeanFactoryConstant.DINNERTABLE_DAO);
    private FoodDao foodDao = (FoodDao) BeanFactory.getBean(BeanFactoryConstant.FOOD_DAO);
    private UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactoryConstant.USER_USERDAO);
    private OrderDao orderDao = (OrderDao) BeanFactory.getBean(BeanFactoryConstant.ORDER_DAO);

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
    public PageBean<Food> findByPage(String typeId,String _currentPage, String _rows) {
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
        int totalCount = foodDao.findTotalCount(typeId);
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
        Food food = null;
        if (typeId != "") {
            food = new Food();
            food.setTypeId(Long.parseLong(typeId));
        }
        List<Food> foodList =  foodDao.findFoodCondition(food,start,rows);
        //将查询到的每页的记录数据设置到page对象中
        pb.setList(foodList);

        return pb;
    }

    /**
     * 结账处理
     * @param money
     * @param user
     * @param tableId
     * @param orderId
     */
    @Override
    public ResultVO callPay(Double money, User user, Long tableId, String orderId) {
        //扣钱
        if (money > user.getBalance()){
            return new ResultVO(false,"对不起，余额不足，请充值！","没钱还吃饭，快去充钱吧！！！");
        }
        //先查询订单状态
        Orders orders = orderDao.findById(orderId);
        if (orders.getOrderStatus() == 1){
            //说明已经支付过了，要退桌
            //修改餐桌的状态为空闲
            DinnerTable table = dinnerTableDao.findById(tableId);
            //预定时间设置为空
            table.setReservationTime(null);
            //状态设置为空闲
            table.setTableStatus(0);
            try {
                dinnerTableDao.updateStatus(table);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO("订单支付失败，请重新支付！！！",null);
            }

            return new ResultVO(true,"订单已支付","你有一个不错的朋友！！！");
        }
        //用户付钱
        userDao.updateBalance(user.getBalance() - money,user.getUserId());
        //订单状态修改为已支付 设置为1
        orderDao.updateStatus(1,orderId);
        //修改餐桌的状态为空闲
        DinnerTable table = dinnerTableDao.findById(tableId);
        //预定时间设置为空
        table.setReservationTime(null);
        //状态设置为空闲
        table.setTableStatus(0);
        try {
            dinnerTableDao.updateStatus(table);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO("订单支付失败，请重新支付！！！",null);
        }
        return new ResultVO("订单支付成功！！！",null);
    }
}
