package com.qingfeng.pojo;

import java.io.Serializable;

/**
 * 菜单的实体类
 * 对应数据库表t_menu
 *
 * @author 清风学Java
 * @date 2021/11/20
 * @apiNote
 */
public class Menu implements Serializable {

    /**
     * 主键：菜单的ID 无实意
     */
    private Long menuId;
    /**
     * 菜单的名称
     */
    private String menuName;
    /**
     * 菜单的访问路径
     */
    private String menuUrl;

    public Menu() {
    }

    public Menu(Long menuId, String menuName, String menuUrl) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                '}';
    }
}
