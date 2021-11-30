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

    public String search(HttpServletRequest request, HttpServletResponse response){
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.setTableName(request.getParameter("keyword"));
        List<DinnerTable> tableList = dinnerTableService.findByCondition(dinnerTable);

        //将数据保存到request域中
        request.setAttribute("tables",tableList);

        System.out.println(tableList);

        //请求转发到对应的页面
        return MessageConstant.PREFIX_FORWAED+"/backend/detail/table/table-list.jsp";
    }
}

