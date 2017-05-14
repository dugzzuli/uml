/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/uml";
    private static final String NAME = "root";
    private static final String PASSWORD = "123456";

    private static Connection conn = null;

    // 静态代码块（将加载驱动、连接数据库放入静态块中）
    static {
        try {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库的连接
            conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        if (conn == null) {
            try {
                // 1.加载驱动程序
                Class.forName("com.mysql.jdbc.Driver");
                // 2.获得数据库的连接
                conn = (Connection) DriverManager.getConnection(URL, NAME, PASSWORD);
               
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static int getCount(String sql) throws SQLException {
        PreparedStatement prestat = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = prestat.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        prestat.close();
        return count;
    }

    /*
     * 
     * 测试代码
     */
    public static void main(String[] args) throws Exception {

        // 3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Users");// 选择import
        // java.sql.ResultSet;
        while (rs.next()) {// 如果对象中有数据，就会循环打印出来
            System.out.println(rs.getInt("id"));
        }
    }
}
