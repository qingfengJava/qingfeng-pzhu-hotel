package com.qingfeng.controller.front;

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.PageBean;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.service.FrontService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String findByPage(HttpServletRequest request, HttpServletResponse response){
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

        //分页查询数据
        PageBean<Food> pageBean = frontService.findByPage(currentPage,rows);
        request.setAttribute("pb",pageBean);
        return MessageConstant.PREFIX_FORWAED+"/front/detail/menu.jsp";
    }
}
