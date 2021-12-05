package com.qingfeng.service;

import com.qingfeng.entity.ResultVO;

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
     */
    ResultVO findTablesByStatus(String tableStatus) throws Exception;
}
