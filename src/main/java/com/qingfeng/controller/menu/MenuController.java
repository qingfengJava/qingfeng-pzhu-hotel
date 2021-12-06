package com.qingfeng.controller.menu; /**
 * @author 清风学Java
 * @date 2021/11/21
 * @version 1.0.0
 */

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Menu;
import com.qingfeng.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 通过自己封装的工厂类创建对象
     */
    private MenuService menuService = (MenuService) BeanFactory.getBean(BeanFactoryConstant.MENU_SERVICE);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //调用业务层方法查询所有菜单列表
            List<Menu> list = menuService.findAll();

            //json格式的字符串
            Gson gson = new Gson();
            ResultVO vo = new ResultVO(MessageConstant.MENU_FIND_SUCCESS, list.toArray());
            String listJsonString = gson.toJson(vo);

            //响应数据
            response.getWriter().write(listJsonString);
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().write(MessageConstant.COMMON_ERROR_MESSAGE);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
