package com.qingfeng.controller.food;

import com.google.gson.Gson;
import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.ExceptionMessageConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.entity.PageBean;
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
import javax.servlet.http.Part;
import java.io.File;
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

        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页的记录数
        String rows = request.getParameter("rows");

        //给程序做健壮性判断
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            //默认显示5条数据
            rows = "5";
        }


        //调用业务层查询的方法
        PageBean<Food> pb = foodService.findByCondition(food,currentPage,rows);
        //将数据存放到request域中
        request.setAttribute("pb",pb);
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
            int status = foodService.save(food);
            if (status == 0){
                //说明菜品添加成功   请求转发出去
                return MessageConstant.PREFIX_REDIRECT +request.getContextPath()+"/food?method=search";
            }

            //否则，说明菜品重复，添加失败，给出异常信息
            return ExceptionMessageConstant.FOOD_ADD_FAIL_MESSAGE;

        } catch (SQLException e) {
            e.printStackTrace();
            return ExceptionMessageConstant.FOOD_ADD_EXCEPTION_MESSAGE;
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
        //获取前端传过来的菜品的Id
        String foodId = req.getParameter("foodId");
        //根据菜品Id查询菜品信息
        Food food = foodService.findFoodById(foodId);

        //将查到的信息保存到request域中，用做前端的数据回显
        req.setAttribute("foodTypes",foodTypes);
        req.setAttribute("food",food);
        req.setAttribute("currentPage",req.getParameter("currentPage"));
        //请求转发到更新页面
        return MessageConstant.PREFIX_FORWAED+"/backend/detail/food/food-update.jsp";
    }

    /**
     * 更新菜品信息
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public String updateFoodById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //获取各个参数信息
        String foodId = req.getParameter("foodId");
        String foodTypeId = req.getParameter("foodTypeId");
        String foodName = req.getParameter("foodName");
        String foodPrice = req.getParameter("foodPrice");
        String foodMprice = req.getParameter("foodMprice");
        String foodDesc = req.getParameter("foodDesc");
        String oldImage = req.getParameter("oldImage");
        String newImage = null;

        //创建一个food对象，用于存放要更新的food信息
        Food food = null;

        //获取上传的图片对象，判断图片是否为空
        //获取上传的文件对象
        Part part = req.getPart("imageUrl");
        //获取提交的图片文件的名字
        String filename = part.getSubmittedFileName();

        //容错判断，防止空文件（没有选择上传的文件）如果为空，说明没有更新图片
        if (filename == null || filename == "") {
            //说明没有更新图片，直接获取原图片信息保存
            food = new Food(Long.parseLong(foodId),Long.parseLong(foodTypeId),foodName,Double.parseDouble(foodPrice),Double.parseDouble(foodMprice),oldImage,foodDesc,null);
        }else{
            //更新图片信息
            //调用工具类，实现文件上传的方法  返回文件的真实路径  /images/文件名
            newImage = FileUploadUtils.uploadFile(req, "imageUrl", "/files/images/");
            //判断文件上传是否出错
            if (MessageConstant.FILEUPLOAD_ERROR.equals(newImage)){
                return MessageConstant.FILEUPLOAD_ERROR;
            }

            //上传成功，删除原来的图片
            String oldFileName = oldImage.substring(oldImage.lastIndexOf("/") + 1);
            //得到上传文件的目标位置
            String desPath = req.getSession().getServletContext().getRealPath("/files/images/");
            File file = new File(desPath,oldFileName);
            if (file.exists()){
                //如果文件存在，就删除
                file.delete();
            }

            //将信息保存到food对象中
            food = new Food(Long.parseLong(foodId),Long.parseLong(foodTypeId),foodName,Double.parseDouble(foodPrice),Double.parseDouble(foodMprice),newImage,foodDesc,null);
        }

        //调用业务层更新菜品信息的操作
        foodService.updateFoodById(food);

        //更新成功，跳转到查询菜品的控制层方法
        return MessageConstant.PREFIX_REDIRECT+"/food?method=search&currentPage="+req.getParameter("currentPage");
    }

    /**
     * 根据id删除菜品信息
     * @return
     */
    public String deleteFood(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        //获取前端传递过来菜品的id
        String foodId = req.getParameter("foodId");

        //根据id查询对象
        Food food = foodService.findFoodById(foodId);
        //拿到图片
        String image = food.getFoodImage();

        //上传成功，删除原来的图片
        String oldFileName = image.substring(image.lastIndexOf("/") + 1);
        //得到上传文件的目标位置
        String desPath = req.getSession().getServletContext().getRealPath("/files/images/");
        File file = new File(desPath,oldFileName);
        if (file.exists()){
            //如果文件存在，就删除
            file.delete();
        }

        //调用业务层的方法删除id
        foodService.deleteFood(foodId);
        //删除成功，跳转到查询菜品的控制层方法
        return MessageConstant.PREFIX_REDIRECT+"/food?method=search&currentPage="+req.getParameter("currentPage");
    }


}
