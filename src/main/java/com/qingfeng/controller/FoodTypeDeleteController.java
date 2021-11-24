package com.qingfeng.controller;

import com.qingfeng.service.FoodTypeService;
import com.qingfeng.service.impl.FoodTypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 删除菜系的控制层
 *
 * @author 清风学Java
 * @date 2021/11/24
 * @version 1.0.0
 */
@WebServlet("/foodtype/delete")
public class FoodTypeDeleteController extends HttpServlet {

    private FoodTypeService foodTypeService = new FoodTypeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");*/
        try {
            //获取前端要删除的Id值
            String typeId = request.getParameter("typeId");
            //调用业务层删除的方法
            foodTypeService.deleteById(typeId);

            //删除成功，重定向到菜系管理，展示所有的菜系
            response.sendRedirect(request.getContextPath() + "/foodType/search");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("服务器正忙，请稍后重试... ...");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
