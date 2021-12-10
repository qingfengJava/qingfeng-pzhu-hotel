package com.qingfeng.controller.order;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.OrderDetailList;
import com.qingfeng.entity.OrderList;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Orders;
import com.qingfeng.pojo.User;
import com.qingfeng.service.DinnerTableService;
import com.qingfeng.service.OrderService;
import com.qingfeng.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/10
 */
@WebServlet("/order")
public class OrderController extends BaseServlet {

    private OrderService orderService = (OrderService) BeanFactory.getBean(BeanFactoryConstant.ODER_SERVICE);
    private UserService userService = (UserService) BeanFactory.getBean(BeanFactoryConstant.USER_USERSERVICE);
    private DinnerTableService dinnerTableService = (DinnerTableService) BeanFactory.getBean(BeanFactoryConstant.DINNERTABLE_SERVICE);

    public String search(HttpServletRequest request, HttpServletResponse response){
        //查询所有的订单，并将数据存入request域中
        List<OrderList> orderList = orderService.findAllOrder();
        request.setAttribute("orderList",orderList);

        return MessageConstant.PREFIX_FORWAED+"/backend/detail/order/order-list.jsp";
    }

    public String orderDetail(HttpServletRequest request, HttpServletResponse response){
        //查询符合订单的详情，并将数据存到request域中
        String orderListId = request.getParameter("orderListId");
        List<OrderDetailList> orderDetailLists = orderService.findOrderById(orderListId);
        request.setAttribute("orderDetailLists",orderDetailLists);
        return MessageConstant.PREFIX_FORWAED+"/backend/detail/order/order-detail.jsp";
    }

    public String callPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取订单Id
        String orderId = request.getParameter("orderId");
        String totalPrice = request.getParameter("totalPrice");
        //根据orderId查询订单信息
        Orders orders = orderService.findById(orderId);
        //根据用户id查询用户信息
        User user = userService.findByUserId(orders.getUserId());

        //修改订单状态
        orderService.updateOrderStatus(1,orderId);
        //修改餐桌状态
        dinnerTableService.updateStatusByTableId(orders.getTableId());
        //修改用户剩余金额
        userService.updatePrice(orders.getUserId(),user.getBalance() - Double.valueOf(totalPrice));

        return MessageConstant.PREFIX_REDIRECT+"/order?method=search";
    }
}
