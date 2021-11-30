package com.qingfeng.utils.sql;

import com.qingfeng.pojo.Menu;
import com.qingfeng.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu表的增删改查的封装
 *
 * @author 清风学Java
 * @date 2021/11/21
 * @apiNote
 */
public class MenuSql {

    /**
     * 查询所有的菜单列表
     * @param sql
     * @return 返回一个菜单列表集合
     */
    public static List<Menu> findAllMenu(String sql){
        //获取连接
        Connection con = DbUtils.getCon();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Menu> menus = new ArrayList<>();
        try {
            if (con != null) {
                //利用con(连接)建立查询语句
                pst = con.prepareStatement(sql);
                //利用SQL语句做查询
                rs = pst.executeQuery();
                while (rs.next()){
                    //将数据进行封装
                    Long menuId = rs.getLong("menu_id");
                    String menuName = rs.getString("menu_name");
                    String menuUrl = rs.getString("menu_url");

                    menus.add(new Menu(menuId,menuName,menuUrl));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(con, pst,rs);
        }
        return menus;
    }



}
