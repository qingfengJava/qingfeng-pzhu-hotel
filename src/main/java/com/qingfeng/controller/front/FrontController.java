package com.qingfeng.controller.front;

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.CartItem;
import com.qingfeng.entity.OrderList;
import com.qingfeng.entity.PageBean;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.pojo.Orders;
import com.qingfeng.pojo.User;
import com.qingfeng.service.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台数据的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/5
 */
@WebServlet("/front")
public class FrontController extends BaseServlet {

    private FrontService frontService = (FrontService) BeanFactory.getBean(BeanFactoryConstant.FRONT_SERVICE);
    private FoodTypeService foodTypeService = (FoodTypeService) BeanFactory.getBean(BeanFactoryConstant.FOODTYPE_SERVICE);
    private FoodService foodService = (FoodService) BeanFactory.getBean(BeanFactoryConstant.FOOD_SERVICE);
    private CartService cartService = (CartService) BeanFactory.getBean(BeanFactoryConstant.CART_SERVICE);
    private DinnerTableService dinnerTableService = (DinnerTableService) BeanFactory.getBean(BeanFactoryConstant.DINNERTABLE_SERVICE);
    private OrderService orderService = (OrderService) BeanFactory.getBean(BeanFactoryConstant.ODER_SERVICE);

    /**
     * 根据餐桌的空闲状态查询餐桌的信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String findTablesByStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要查询的餐桌的状态   0-表示餐桌空闲
        String tableStatus = request.getParameter("tableStatus");
        //根据餐桌状态查询餐桌信息
        ResultVO resultVO = frontService.findTablesByStatus(tableStatus);

        Gson gson = new Gson();
        //以JSON格式的字符串返回
        return gson.toJson(resultVO);
    }

    /**
     * 分页查询菜品信息
     * @param request
     * @param response
     * @return
     */
    public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询菜系列表
        HttpSession session = request.getSession();
        //获取session中菜系列表数据
        List<FoodType> foodTypes = (List<FoodType>) session.getAttribute("foodTypes");
        if (foodTypes == null || foodTypes.size() == 0){
            //如果session中没有菜系数据，那就需要查询，然后展示在前端
            foodTypes = foodTypeService.findCondition(new FoodType());
            session.setAttribute("foodTypes",foodTypes);
        }

        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页的记录数
        String rows = request.getParameter("rows");

        //给程序做健壮性判断  没有就赋默认值
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        //每页最多显示8条数据
        if (rows == null || "".equals(rows)){
            //默认显示8条数据
            rows = "8";
        }

        //获取前台传过来的typeId，进行条件的模糊查询，这里注意，全部菜品默认没有typeId
        String typeId = request.getParameter("typeId");
        if (typeId==null || "0".equals(typeId)){
            //如果typeId为空，说明是查询全部菜品
            typeId = "";
            //并在request域中默认存入0，用于前台比较判断高亮显示列表
            request.setAttribute("typeNum",0);
        }else{
            request.setAttribute("typeNum",typeId);
        }

        //分页查询数据
        PageBean<Food> pageBean = frontService.findByPage(typeId,currentPage,rows);
        request.setAttribute("pb",pageBean);
        //还要存储菜系的名称，用作前台的数据展示  依然要对typeId做判断
        if (typeId == ""){
            //说明是查询全部的菜品
            request.setAttribute("foodType_name","全部菜品");
        }else {
            //直接按菜系名称存储
            request.setAttribute("foodType_name",pageBean.getList().get(0).getFoodType().getTypeName());
        }

        //因为后面加入餐车的操作，是加入到那个餐桌对应的餐车，所以这里要往session中存入一个餐桌的id
        //注意：用户第一次登录传入餐桌id，过来，如果是在内部重复调用这个接口，会出现tableId不存在的情况，因此还要对session做判断
        if (request.getParameter("tableId") != null){
            //也说明是从首页进来的，表示这个餐桌已经被别人预定了，这时要修改餐桌的状态  还要判断是否有人登录
            if (session.getAttribute("loginUser") != null){
                //用户登录，才修改餐桌的状态
                dinnerTableService.updateStatus(request.getParameter("tableId"));
            }
            request.getSession().setAttribute("dinner_table_id",request.getParameter("tableId"));
        }else{
            //等于null，就没有不要判断了，因为session中不管有没有，后面都会做处理。
        }

        return MessageConstant.PREFIX_FORWAED+"/front/detail/menu.jsp";
    }

    /**
     * 根据菜品id查询菜品信息，用于显示到菜品详情也
     * @param request
     * @param response
     * @return
     */
    public String findFoodById(HttpServletRequest request, HttpServletResponse response){
        String foodId = request.getParameter("foodId");
        //根据id查询菜品信息
        Food food = foodService.findFoodById(foodId);
        request.setAttribute("food",food);
        return MessageConstant.PREFIX_FORWAED+"front/detail/foodDetail.jsp";
    }

    /**
     * 加入餐车的功能接口
     * @param request
     * @param response
     * @return
     */
    public String addCart(HttpServletRequest request, HttpServletResponse response){
        //获取菜品Id
        String foodIdStr = request.getParameter("foodId");
        Long foodId = Long.parseLong(foodIdStr);
        //先从session中取出餐车，判断餐车是否存在
        HttpSession session = request.getSession();
        //获取用户数据
        User user = (User) session.getAttribute("loginUser");
        if(user == null){
            // "未登录，无法加入餐车，请先登录！！！"
            return MessageConstant.UNLOGIN_USER;
        }
        //注意还要获取餐桌的Id
        String dinnerTableId = (String) session.getAttribute("dinner_table_id");
        if (dinnerTableId == null){
            //"您还没有选择餐桌，不能下单！！！";
            return MessageConstant.NO_CHOOSE_TABLE;
        }
        //得到餐车  通过map集合来存储数据  键是菜品的id，值是对应菜品的信息
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("dinnerCart:"+user.getUserId()+"_"+dinnerTableId);

        //分析餐桌是否存在
        if (cart == null){
            //餐车不存在，创建餐车，把cartItem加入进来
            cart = new HashMap<>(10);
        }
        //餐车存在  要判断该菜品在餐桌中是否已经存在
        if (cart.containsKey(foodId)){
            //菜品存在，有cartItem  那么就修改数量，更新时间，小计等最后查的时候动态计算出来
            //通过可以获取值
            CartItem cartItem = cart.get(foodId);
            //修改菜品数量
            cartItem.setNum(cartItem.getNum()+1);
            //修改更新时间
            cartItem.setOrderDetailUpdateTime(new Date());
            //更新完放回餐车
            cart.put(foodId,cartItem);
        }else {
            //菜品不存在，直接存储数据
            CartItem cartItem = new CartItem();
            cartItem.setFoodId(foodId);
            cartItem.setNum(1);
            cartItem.setOrderDetailUpdateTime(new Date());
            //更新完放回餐车
            cart.put(foodId,cartItem);
        }

        //把餐车放回session
        session.setAttribute("dinnerCart:"+user.getUserId()+"_"+dinnerTableId,cart);

        //加入餐车成功 去展示餐车列表页面的接口
        return MessageConstant.PREFIX_REDIRECT+"/front?method=searchCart";
    }

    /**
     * 展示餐车列表数据
     * @param request
     * @param response
     * @return
     */
    public String searchCart(HttpServletRequest request, HttpServletResponse response){
        //先从session中取出餐车，判断餐车是否存在
        HttpSession session = request.getSession();
        //获取用户数据
        User user = (User) session.getAttribute("loginUser");
        if(user == null){
            // "未登录，无法加入餐车，请先登录！！！"
            return MessageConstant.UNLOGIN_USER;
        }
        //注意还要获取餐桌的Id
        String dinnerTableId = (String) session.getAttribute("dinner_table_id");
        if (dinnerTableId == null){
            //"您还没有选择餐桌，不能下单！！！";
            return MessageConstant.NO_CHOOSE_TABLE;
        }

        //得到餐车  通过map集合来存储数据  键是菜品的id，值是对应菜品的信息
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("dinnerCart:"+user.getUserId()+"_"+dinnerTableId);
        if(cart == null){
            //说明没有数据，或者session过期，直接return
            return MessageConstant.USER_NOT_CANCHE;
        }

        //通过业务层得到餐车的map集合存储完整的数据，将用户是否是会员的信息一起传递，用于前端数据展示
        Map<String, Object> map = cartService.getCartList(cart, user.getIsMember());

        //得到详细的数据 cartList  并存到session中进行保存
        session.setAttribute("cartList",map.get("list"));
        System.out.println(map.get("list"));
        session.setAttribute("totalPrice",map.get("totalPrice"));
        session.setAttribute("totalNum",map.get("totalNum"));

        return MessageConstant.PREFIX_REDIRECT+"/front/detail/clientCart.jsp";
    }

    /**
     * 下单的操作
     * @param request
     * @param response
     * @return
     */
    public String genernateOrder(HttpServletRequest request, HttpServletResponse response){
        //获取餐车中的数据
        HttpSession session = request.getSession();
        //生成订单
        List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        Integer totalNum = (Integer) session.getAttribute("totalNum");
        User loginUser = (User) session.getAttribute("loginUser");
        String dinner_table_id = (String) session.getAttribute("dinner_table_id");
        //要判断是否为空，不为空才有意义
        if (cartList != null) {
            //生成订单
            ResultVO resultVO = orderService.genernateOrder(cartList, totalPrice, totalNum, loginUser, Long.valueOf(dinner_table_id));

            //设置订单详情数据
            session.setAttribute("orderDetailList", cartList);
            //订单总金额
            Orders orders = (Orders) resultVO.getData();
            session.setAttribute("orderTotalPrice",orders.getOrderTotalPrice() );
            //订单菜品总数量
            session.setAttribute("orderTotalNum", orders.getTotalNum());
            //订单号
            session.setAttribute("orderId",orders.getOrderId());
            //清空餐车中的数据
            session.removeAttribute("cartList");
            session.removeAttribute("totalPrice");
            session.removeAttribute("totalNum");
        }

        return MessageConstant.PREFIX_REDIRECT+"/front/detail/clientOrderList.jsp";
    }

    /**
     * 结账处理
     * @param request
     * @param response
     * @return
     */
    public String callPay(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String dinner_table_id = (String)session.getAttribute("dinner_table_id");
        //支付金额
        String moneyStr = request.getParameter("money");
        String orderId = request.getParameter("orderId");

        //支付
        ResultVO resultVO = frontService.callPay(Double.valueOf(moneyStr), loginUser, Long.valueOf(dinner_table_id), orderId);

        if (resultVO.getSuccess()){
            //订单支付成功，清空所有数据
            session.invalidate();
        }

        //回去登录
        return MessageConstant.PREFIX_REDIRECT+"/front/detail/checkOut.jsp";
    }

    /**
     * 查询历史订单记录
     * @param request
     * @param response
     * @return
     */
    public String myClient(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String userId = request.getParameter("userId");
        if (userId == null){
            //说明用户没有登录，直接返回让用户登录
            return MessageConstant.PREFIX_FORWAED+"/";
        }
        List<OrderList> orderList = orderService.findAllOrderByUserId(userId);

        request.setAttribute("orderList",orderList);
        System.out.println(orderList);

        return MessageConstant.PREFIX_FORWAED+"/front/detail/myClient.jsp";
    }
}
