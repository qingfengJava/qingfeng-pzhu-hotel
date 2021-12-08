package com.qingfeng.controller.front;

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.PageBean;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.FoodType;
import com.qingfeng.service.FoodService;
import com.qingfeng.service.FoodTypeService;
import com.qingfeng.service.FrontService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    private FoodTypeService foodTypeService = (FoodTypeService) BeanFactory.getBean(BeanFactoryConstant.FOODTYPE_SERVICE);
    private FoodService foodService = (FoodService) BeanFactory.getBean(BeanFactoryConstant.FOOD_SERVICE);

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
        //查询菜系列表
        HttpSession session = request.getSession();
        //获取session中菜系列表数据
        List<FoodType> foodTypes = (List<FoodType>) session.getAttribute("foodTypes");
        if (foodTypes == null || foodTypes.size() == 0){
            //如果session中没有菜系数据，那就需要查询，然后展示在前端
            foodTypes = foodTypeService.findCondition(new FoodType());
            session.setAttribute("foodTypes",foodTypes);
        }

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

        //获取前台传过来的typeId，进行条件的模糊查询，这里注意，全部菜品默认没有typeId
        String typeId = request.getParameter("typeId");
        if (typeId==null || "0".equals(typeId)){
            //如果typeId为空，说明是查询全部菜品
            typeId = "";
            //并在request域中默认存入0，用于前台比较判断高亮显示列表
            request.setAttribute("typeNum",0);
        }else{
            request.setAttribute("typeNum",typeId);
        }

        //分页查询数据
        PageBean<Food> pageBean = frontService.findByPage(typeId,currentPage,rows);
        request.setAttribute("pb",pageBean);
        //还要存储菜系的名称  依然要对typeId做判断
        if (typeId == ""){
            //说明是查询全部的菜品
            request.setAttribute("foodType_name","全部菜品");
        }else {
            //直接按菜系名称存储
            request.setAttribute("foodType_name",pageBean.getList().get(0).getFoodType().getTypeName());
        }

        return MessageConstant.PREFIX_FORWAED+"/front/detail/menu.jsp";
    }

    public String findFoodById(HttpServletRequest request, HttpServletResponse response){
        String foodId = request.getParameter("foodId");
        //根据id查询菜品信息
        Food food = foodService.findFoodById(foodId);
        request.setAttribute("food",food);
        return MessageConstant.PREFIX_FORWAED+"front/detail/foodDetail.jsp";
    }
}
