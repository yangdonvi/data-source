package com.ncwu.datasource.calc.util;

import java.sql.*;

/**
 * Created by Donvi Yang on 2019/5/1.
 */
public class HiveClientUtils {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String user = "hive";//用户名
    private static String password = "123456";//密码
    private static String url = "jdbc:hive2://192.168.235.140:10000/default";//hive库地址+库名

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void closeConn(Connection connection, Statement statement){
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
