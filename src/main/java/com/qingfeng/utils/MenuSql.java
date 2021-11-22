package com.qingfeng.utils;

import com.qingfeng.pojo.Menu;

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
     * 查询所有的书籍信息
     * @param sql
     * @return 返回一个书籍对象的集合
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
                return menus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, pst,rs);
        }
        return null;
    }


    /**
     * 定义通用的关闭资源的方法
     * @param con
     * @param pst
     */
    public static void close(Connection con, PreparedStatement pst, ResultSet rs) {
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
