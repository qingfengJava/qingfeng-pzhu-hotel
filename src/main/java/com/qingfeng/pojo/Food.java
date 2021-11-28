package com.qingfeng.pojo;

import java.io.Serializable;

/**
 * 菜品实体类
 *
 * 关联菜系类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/28
 */
public class Food implements Serializable {

    /**
     * 菜品Id
     */
    private Long foodId;
    /**
     * 菜系Id，作为外键关联菜系
     */
    private Long typeId;
    /**
     * 菜品名称
     */
    private String foodName;
    /**
     * 菜品价格
     */
    private Double foodPrice;
    /**
     * 会员的价格
     */
    private Double foodMprice;
    /**
     * 菜品图片
     */
    private String foodImage;
    /**
     * 菜品简介
     */
    private String foodDesc;

    /**
     * 映射多对一的对象，多个菜品属于一个菜系
     * 连表查询
     */
    private FoodType foodType;

    public Food() {
    }

    public Food(Long foodId, Long typeId, String foodName, Double foodPrice, Double foodMprice, String foodImage, String foodDesc, FoodType foodType) {
        this.foodId = foodId;
        this.typeId = typeId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodMprice = foodMprice;
        this.foodImage = foodImage;
        this.foodDesc = foodDesc;
        this.foodType = foodType;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Double getFoodMprice() {
        return foodMprice;
    }

    public void setFoodMprice(Double foodMprice) {
        this.foodMprice = foodMprice;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", typeId=" + typeId +
                ", foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodMprice=" + foodMprice +
                ", foodImage='" + foodImage + '\'' +
                ", foodDesc='" + foodDesc + '\'' +
                ", foodType=" + foodType +
                '}';
    }
}
