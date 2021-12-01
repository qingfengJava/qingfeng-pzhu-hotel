package com.qingfeng.utils;

import java.sql.*;

/**
 * 加载数据库类
 * @author 清风学Java
 */
public class DbUtils {

    /**
     * 驱动名称
     */
    private static String jdbcName="com.mysql.cj.jdbc.Driver";
    /**
     * 数据库连接地址
     */
    private static String dbUrl="jdbc:mysql://localhost:3306/pzhu-hotel?useSSL=true&serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true&useOldAliasMetadataBehavior=true";
    /**
     * 用户名
     */
    private static String dbUserName="root";
    /**
     * 密码
     */
    private static String dbPassword="root";

    /**
     * 获取数据库连接
     */
    public static Connection getCon(){
        try {
            //加载MySQL驱动
            Class.forName(jdbcName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            //建立MySQL连接
            con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeCon(Connection con)throws Exception{
        System.out.println(con);
        if(con!=null){
            con.close();
        }
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

