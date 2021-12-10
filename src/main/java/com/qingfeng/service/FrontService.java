package com.qingfeng.service;

import com.qingfeng.entity.PageBean;
import com.qingfeng.entity.ResultVO;
import com.qingfeng.pojo.Food;
import com.qingfeng.pojo.User;

/**
 * 前台数据的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/5
 */
public interface FrontService {

    /**
     * 根据状态查询符合的餐桌的集合
     * @param tableStatus
     * @return
     * @throws Exception
     */
    ResultVO findTablesByStatus(String tableStatus) throws Exception;

    /**
     * 根据页码和每页显示的总数，进行分页查询
     *
     * @param typeId
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Food> findByPage(String typeId, String _currentPage, String _rows);

    /**
     * 结账处理
     * @param valueOf
     * @param user
     * @param valueOf1
     * @param orderId
     * @return
     */
    ResultVO callPay(Double valueOf, User user, Long valueOf1, String orderId);
}
