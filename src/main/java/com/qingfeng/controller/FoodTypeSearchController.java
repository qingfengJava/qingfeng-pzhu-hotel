package com.qingfeng.controller; /**
 * @author 清风学Java
 * @date 2021/11/24
 * @version 1.0.0
 */

import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodTypeService;
import com.qingfeng.service.impl.FoodTypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * 菜系管理的控制层
 *
 * @author 清风学Java
 * @date 2021/11/24
 * @version 1.0.0
 */
@WebServlet("/foodType/search")
public class FoodTypeSearchController extends HttpServlet {

    private FoodTypeService foodTypeService = new FoodTypeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取输入框输入的菜系名称
        String keyword = request.getParameter("keyword");
        //将获取到的参数，存入foodType对象中
        FoodType foodType = new FoodType();
        foodType.setTypeName(keyword);

        //调用业务层的方法，查询菜系的集合
        List<FoodType> foodTypes = foodTypeService.findCondition(foodType);
        //将查询到的菜系的集合，存入request域对象中
        request.setAttribute("foodTypes",foodTypes);
        //将查询的菜名也存到request域对象中，用作数据的回显
        request.setAttribute("keyword",foodType.getTypeName());
        //请求转发，将数据共享
        request.getRequestDispatcher("/backend/detail/foodtype/foodtype-list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
