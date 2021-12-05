package com.qingfeng.controller.front;

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
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

    public String findTablesByStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要查询的餐桌的状态   0-表示餐桌空闲
        String tableStatus = request.getParameter("tableStatus");
        //根据餐桌状态查询餐桌信息
        ResultVO resultVO = frontService.findTablesByStatus(tableStatus);

        Gson gson = new Gson();
        //以JSON格式的字符串返回
        return gson.toJson(resultVO);
    }
}
