package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.OrderDao;
import com.qingfeng.dao.OrderDetailDao;
import com.qingfeng.entity.CartItem;
import com.qingfeng.entity.OrderDetailList;
import com.qingfeng.entity.OrderList;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.OrderDetail;
import com.qingfeng.pojo.Orders;
import com.qingfeng.pojo.User;
import com.qingfeng.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = (OrderDao) BeanFactory.getBean(BeanFactoryConstant.ORDER_DAO);
    private OrderDetailDao orderDetailDao = (OrderDetailDao) BeanFactory.getBean(BeanFactoryConstant.ORDERDETAIL_DAO);

    /**
     * 根据餐车的数据，生成订单
     * @param cartList
     * @param totalPrice
     * @param totalNum
     * @param loginUser
     * @param dinner_table_id
     */
    @Override
    public ResultVO genernateOrder(List<CartItem> cartList, Double totalPrice, Integer totalNum, User loginUser, Long dinner_table_id) {
        try {
            if (loginUser == null){
                throw new RuntimeException("session中的用户失效了");
            }

            //先保存订单主体数据
            Orders order = new Orders();
            //订单编号
            order.setOrderId(System.currentTimeMillis()+"");
            //创建时间
            order.setOrderCreateTime(new Date());
            //订单状态：未支付
            order.setOrderStatus(0);
            //总金额
            order.setOrderTotalPrice(totalPrice);
            //用户id
            order.setUserId(loginUser.getUserId());
            //餐桌id
            order.setTableId(dinner_table_id);
            //菜品总数量
            order.setTotalNum(totalNum);

            orderDao.save(order);
            //再保存订单详情数据
            for (CartItem cartItem : cartList) {
                //订单详情
                OrderDetail orderDetail = new OrderDetail();
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //详情Id
                orderDetail.setOrderDetailId(System.currentTimeMillis()+"");
                //订单Id
                orderDetail.setOrderId(order.getOrderId());
                //菜品Id
                orderDetail.setFoodId(cartItem.getFoodId());
                //小计
                orderDetail.setFoodTotalPrice(cartItem.getFoodTotalPrice());
                //数量
                orderDetail.setNum(cartItem.getNum());
                //详情创建时间
                orderDetail.setOrderDetailCreateTime(new Date());
                orderDetail.setOrderDetailUpdateTime(new Date());

                orderDetailDao.save(orderDetail);
            }
            return new ResultVO("生成订单成功！",order);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return new ResultVO(true,"生成订单失败",null);
    }

    /**
     * 查询所有的订单信息
     * @return
     */
    @Override
    public List<OrderList> findAllOrder() {
        return orderDao.findAllOrders();
    }

    /**
     * 根据订单Id查询详情
     * @param orderListId
     * @return
     */
    @Override
    public List<OrderDetailList> findOrderById(String orderListId) {
        return orderDao.findOrderById(orderListId);
    }

    @Override
    public Orders findById(String orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public void updateOrderStatus( int status,String orderId) {
        orderDao.updateStatus(status,orderId);
    }
}
