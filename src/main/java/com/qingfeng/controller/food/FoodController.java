package com.qingfeng.controller.food;

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodService;
import com.qingfeng.service.FoodTypeService;
import com.qingfeng.utils.FileUploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @MultipartConfig 用于处理文件上传的
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
@WebServlet("/food")
@MultipartConfig
public class FoodController extends BaseServlet {

    private FoodService foodService = (FoodService) BeanFactory.getBean(BeanFactoryConstant.FOOD_SERVICE);
    private FoodTypeService foodTypeService = (FoodTypeService) BeanFactory.getBean(BeanFactoryConstant.FOODTYPE_SERVICE);


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
        return MessageConstant.PREFIX_FORWAED +"/backend/detail/food/food-list.jsp";
    }

    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //调用工具类，实现文件上传的方法  返回文件的真实路径  /images/文件名
            String reslut = FileUploadUtils.uploadFile(request, "imageUrl", "/files/images/");
            //判断文件上传是否出错
            if (MessageConstant.FILEUPLOAD_ERROR.equals(reslut)){
                return MessageConstant.FILEUPLOAD_ERROR;
            }

            //文件上传成功，获取其他的请求参数，将数据进行保存
            String typeId = request.getParameter("typeId");
            String foodName = request.getParameter("foodName");
            String foodPrice = request.getParameter("foodPrice");
            String foodMprice = request.getParameter("foodMprice");
            String foodDesc = request.getParameter("foodDesc");

            //创建Food对象，保存数据
            Food food = new Food(null,Long.parseLong(typeId),foodName,Double.parseDouble(foodPrice),Double.parseDouble(foodMprice),reslut,foodDesc,null);
            //调用业务层保存数据
            foodService.save(food);

            //请求转发出去
            return MessageConstant.PREFIX_REDIRECT +request.getContextPath()+"/food?method=search";
        } catch (SQLException e) {
            e.printStackTrace();
            return "添加菜品异常，请稍后再试！";
        }
    }

    /**
     * 查询所有的菜系，返回json格式的数据
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    public String findFoodTypeList(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //调用业务层的方法，查询菜系的集合
        List<FoodType> foodTypes = foodTypeService.findCondition(new FoodType());
        //转成JSON格式的字符串 并返回给BaseServlet响应
        Gson gson = new Gson();
        return gson.toJson(foodTypes);
    }

    /**
     * 查询所有的菜系，请求转发回去
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    public String toSaveUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //调用业务层的方法，查询所有菜系的集合
        List<FoodType> foodTypes = foodTypeService.findCondition(new FoodType());
        //获取前端穿过来的菜品的Id
        String foodId = req.getParameter("foodId");
        //根据菜品Id查询菜品信息
        Food food = foodService.findFoodById(foodId);
        return "";
    }


}
