package com.qingfeng.controller.food;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
@WebServlet("/food")
public class FoodController extends BaseServlet {

    private FoodService foodService = (FoodService) BeanFactory.getBean(BeanFactoryConstant.FOOD_SERVICE);

    public String search(HttpServletRequest request, HttpServletResponse response){
        //接收传过来的参数 foodName 和 foodTypeName
        String foodName = request.getParameter("foodName");
        String foodTypeName = request.getParameter("foodTypeName");
        //封装数据
        Food food = new Food();
        food.setFoodName(foodName);
        FoodType foodType = new FoodType();
        foodType.setTypeName(foodTypeName);
        food.setFoodType(foodType);

        //调用业务层查询的方法
        List<Food> foods = foodService.findByCondition(food);
        //将数据存放到request域中
        request.setAttribute("foods",foods);
        //将接收的参数存入request域中，一并做回显
        request.setAttribute("foodName",foodName);
        request.setAttribute("foodTypeName",foodTypeName);
        //请求转发出去
        return MessageConstant.FREFIX_FORWAED+"/backend/detail/food/food-list.jsp";
    }
}
