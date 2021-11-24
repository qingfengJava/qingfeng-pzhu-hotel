package com.qingfeng.controller;

import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodTypeService;
import com.qingfeng.service.impl.FoodTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author 清风学Java
 * @date 2021/11/24
 * @version 1.0.0
 */
@WebServlet("/foodtype/findById")
public class FoodTypeFindByIdController extends HttpServlet {

    private FoodTypeService foodTypeService = new FoodTypeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1、获取请求的参数Id
        String typeId = request.getParameter("typeId");
        //根据id调用业务层查询的方法，返回一个foodType对象
        FoodType foodType = foodTypeService.findById(typeId);
        //将foodType对象存入request域中，进行数据共享
        request.setAttribute("foodType",foodType);
        //查询成功，请求转发到更新页面
        request.getRequestDispatcher("/backend/detail/foodtype/foodtype-update.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
