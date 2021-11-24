package com.qingfeng.controller;

import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 解决Servlet代码冗余问题，一个模块最好对应一个Servlet
 *
 * 解决思路：在请求路径上加一个参数(method)来区分我们要做哪一个逻辑。如：/foodtype?method=delete
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/25
 */
@WebServlet("/foodtype")
public class FoodTypeController extends HttpServlet {

    /**
     * 通过自己封装的工厂类来创建对象
     */
    private FoodTypeService foodTypeService = (FoodTypeService) BeanFactory.getBean("foodTypeService");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数method
        String method = req.getParameter("method");

        //通过switch来判断要做那个处理逻辑
        switch (method){
            case "delete":
                delete(req,resp);
                break;
            case "save":
                save(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            case "findById":
                findById(req,resp);
                break;
            case "search":
                search(req,resp);
                break;
            default:
                resp.getWriter().write("非法请求！");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 删除菜系的方法
     * @param req
     * @param resp
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //获取前端要删除的Id值
            String typeId = req.getParameter("typeId");
            //调用业务层删除的方法
            foodTypeService.deleteById(typeId);

            //删除成功，重定向到菜系管理，展示所有的菜系
            resp.sendRedirect(req.getContextPath() + "/foodtype?method=search");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("服务器正忙，请稍后重试... ...");
        }
    }

    /**
     * 添加菜系的方法
     * @param req
     * @param resp
     */
    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeName = req.getParameter("typeName");
        //2、调用业务层，添加菜系的方法
        foodTypeService.save(typeName);

        //添加成功，重定向到菜系管理，展示所有的菜系
        resp.sendRedirect(req.getContextPath() + "/foodtype?method=search");
    }

    /**
     * 更新菜系的方法
     * @param req
     * @param resp
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1、获取请求参数
        String updateName = req.getParameter("updateName");
        String updateId = req.getParameter("updateId");
        //将数据封装到foodtype对象中
        FoodType foodType = new FoodType(Long.parseLong(updateId),updateName);
        //2、调用业务层，修改菜系的方法
        foodTypeService.update(foodType);

        //添加成功，重定向到菜系管理，展示所有的菜系
        resp.sendRedirect(req.getContextPath() + "/foodtype?method=search");
    }

    /**
     * 根据id查找菜系的方法
     * @param req
     * @param resp
     */
    private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数Id
        String typeId = req.getParameter("typeId");
        //根据id调用业务层查询的方法，返回一个foodType对象
        FoodType foodType = foodTypeService.findById(typeId);
        //将foodType对象存入request域中，进行数据共享
        req.setAttribute("foodType",foodType);
        //查询成功，请求转发到更新页面
        req.getRequestDispatcher("/backend/detail/foodtype/foodtype-update.jsp").forward(req,resp);
    }

    /**
     * 查询所有菜系的方法
     * @param req
     * @param resp
     */
    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //获取输入框输入的菜系名称
            String keyword = req.getParameter("keyword");
            //将获取到的参数，存入foodType对象中
            FoodType foodType = new FoodType();
            foodType.setTypeName(keyword);

            //调用业务层的方法，查询菜系的集合
            List<FoodType> foodTypes = foodTypeService.findCondition(foodType);
            //将查询到的菜系的集合，存入request域对象中
            req.setAttribute("foodTypes",foodTypes);
            //将查询的菜名也存到request域对象中，用作数据的回显
            req.setAttribute("keyword",foodType.getTypeName());
            //请求转发，将数据共享
            req.getRequestDispatcher("/backend/detail/foodtype/foodtype-list.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("服务器正忙，请稍后重试... ...");
        }
    }
}
