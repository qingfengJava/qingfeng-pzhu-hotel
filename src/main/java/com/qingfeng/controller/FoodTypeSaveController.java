package com.qingfeng.controller;

import com.qingfeng.factory.BeanFactory;
import com.qingfeng.service.FoodTypeService;

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
@WebServlet("/foodtype/save")
public class FoodTypeSaveController extends HttpServlet {

    /**
     * 通过自己封装的工厂类来创建对象
     */
    private FoodTypeService foodTypeService = (FoodTypeService) BeanFactory.getBean("foodTypeService");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");*/
        //1、获取请求参数
        String typeName = request.getParameter("typeName");
        //2、调用业务层，添加菜系的方法
        foodTypeService.save(typeName);

        //添加成功，重定向到菜系管理，展示所有的菜系
        response.sendRedirect(request.getContextPath() + "/foodType/search");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
