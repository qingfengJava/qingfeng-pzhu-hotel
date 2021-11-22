package com.qingfeng.controller; /**
 * @author 清风学Java
 * @date 2021/11/21
 * @version 1.0.0
 */

import com.qingfeng.pojo.Menu;
import com.qingfeng.service.MenuService;
import com.qingfeng.service.impl.MenuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 清风学Java
 * @date 2021/11/21
 * @version 1.0.0
 */
@WebServlet("/menu/list")
public class MenuController extends HttpServlet {

    /**
     * 控制层维护业务层对象
     */
    private MenuService menuService = new MenuServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用业务层方法查询所有菜单列表
        List<Menu> list = menuService.findAll();
        System.out.println(list);
        //将查询到的列表集合存入session域对象中，共享出去。这里要注意：存入request域中，只有一次请求，无法访问到。
        request.getSession().setAttribute("menus",list);

        request.getRequestDispatcher("/backend/index.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
