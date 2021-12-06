package com.qingfeng.controller.dinnertable;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.constant.MessageConstant;
import com.qingfeng.controller.BaseServlet;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.DinnerTable;
import com.qingfeng.service.DinnerTableService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/30
 */
@WebServlet("/dinnertable")
public class DinnerTableController extends BaseServlet {

    private DinnerTableService dinnerTableService = (DinnerTableService) BeanFactory.getBean(BeanFactoryConstant.DINNERTABLE_SERVICE);

    /**
     * 条件查询所有的餐桌
     * @param request
     * @param response
     * @return
     */
    public String search(HttpServletRequest request, HttpServletResponse response){
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.setTableName(request.getParameter("keyword"));
        List<DinnerTable> tableList = dinnerTableService.findByCondition(dinnerTable);

        //将数据保存到request域中
        request.setAttribute("tables",tableList);

        //请求转发到对应的页面
        return MessageConstant.PREFIX_FORWAED+"/backend/detail/table/table-list.jsp";
    }

    /**
     * 更新餐桌信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        dinnerTableService.updateStatus(request.getParameter("tableId"));

        //成功，重定向到展示页面
        return MessageConstant.PREFIX_REDIRECT+"/dinnertable?method=search";
    }

    /**
     * 根据餐桌的名字保存惨祸的
     * @param request
     * @param response
     * @return
     */
    public String save(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取前台提交的餐桌的信息
            String tableName = request.getParameter("tableName");
            //调用业务层保存餐桌的方法
            dinnerTableService.save(tableName);

            //成功，重定向到展示页面
            return MessageConstant.PREFIX_REDIRECT+"/dinnertable?method=search";
        } catch (Exception e) {
            //出现异常，给出提示信息
            return e.getMessage();
        }
    }

    /**
     * 根据id删除餐桌信息
     * @param request
     * @param response
     * @return
     */
    public String delete(HttpServletRequest request, HttpServletResponse response){
        try {
            String tableId = request.getParameter("tableId");
            //调用业务层删除的方法
            dinnerTableService.deleteById(Integer.parseInt(tableId));
            //成功，重定向到展示页面
            return MessageConstant.PREFIX_REDIRECT+"/dinnertable?method=search";
        } catch (Exception e) {
            //出现异常，给出提示信息
            return e.getMessage();
        }
    }
}

