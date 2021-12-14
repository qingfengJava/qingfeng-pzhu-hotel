package com.qingfeng.service.impl;

import com.qingfeng.constant.BeanFactoryConstant;
import com.qingfeng.dao.FoodDao;
import com.qingfeng.entity.CartItem;
import com.qingfeng.factory.BeanFactory;
import com.qingfeng.pojo.Food;
import com.qingfeng.service.CartService;

import java.util.*;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/9
 */
public class CartServiceImpl implements CartService {

    private FoodDao foodDao = (FoodDao) BeanFactory.getBean(BeanFactoryConstant.FOOD_DAO);

    /**
     * 将餐车map集合中的数据转换成list集合存储完整的数据
     * @param cart
     * @param isMember
     * @return
     */
    @Override
    public Map<String, Object> getCartList(Map<Long, CartItem> cart, Integer isMember) {

        //定义总计变量
        Double totalPrice = 0D;
        //总数量
        Integer totalNum = 0;

        for (CartItem cartItem : cart.values()) {
            //拿到每一项后，查询food
            Food food = foodDao.findFoodById(cartItem.getFoodId());
            cartItem.setFood(food);

            //根据是否是会员，拿出餐车项菜品的单价
            Double price = isMember.intValue() == 1 ? food.getFoodMprice():food.getFoodPrice();
            //单价
            cartItem.setPrice(price);
            //根据是否是会员来计算小计
            cartItem.setFoodTotalPrice(cartItem.getNum() * price);

            //每一项加起来就是总计
            totalPrice += cartItem.getFoodTotalPrice();
            totalNum += cartItem.getNum();
        }
        //排序，根据更新时间
        ArrayList<CartItem> list = new ArrayList<>(cart.values());
        //降序排序
        Collections.sort(list, (o1,o2) -> (int)(o2.getOrderDetailUpdateTime().getTime() - o1.getOrderDetailUpdateTime().getTime()));

        Map<String,Object> map = new HashMap<>(10);
        map.put("list",list);
        map.put("totalPrice",totalPrice);
        map.put("totalNum",totalNum);

        return map;
    }
}
