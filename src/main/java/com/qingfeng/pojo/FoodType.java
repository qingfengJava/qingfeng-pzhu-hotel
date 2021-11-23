package com.qingfeng.pojo;

/**
 * 菜系类
 * 对应数据库表 t_food_type
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public class FoodType {

    /**
     * 菜系的id
     */
    private Long typeId;
    /**
     * 菜系的名称
     */
    private String typeName;

    public FoodType() {
    }

    public FoodType(Long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
