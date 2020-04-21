package com.wsk.study.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class JDBCUtil {
    private static ComboPooledDataSource dataSource;

    /**
     * 连接XXX库并设置自动提交为否
     */
    public static Connection getConnection(String user, String password, String url, String driverClass) {
        Connection conn = null;
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setUser(user);
            //dataSource.setPassword("1");
            dataSource.setPassword(password);
            dataSource.setJdbcUrl(url);
            dataSource.setDriverClass(driverClass);
            //dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/pjxt?useUnicode=true&characterEncoding=utf-8");
            //dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setMinPoolSize(1);
            dataSource.setAcquireIncrement(1);
            dataSource.setMaxPoolSize(1);
            conn = dataSource.getConnection();
//            conn.setAutoCommit(false);//设置自动提交为否
            System.out.println("连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 提交数据
     *
     * @throws Exception
     */
    public static void commit(Connection conn) throws Exception {
        if (null != conn) {
            conn.commit();//提交
        }
        if (null != conn) {
            conn.close();//关闭
        }
    }

    /**
     * 回滚
     *
     * @throws Exception
     */
    public static void rollback(Connection conn) throws Exception {
        if (null != conn) {
            conn.rollback();//LIS数据回滚
        }
        if (null != conn) {
            conn.close();

        }
    }

    /**
     *  关闭连接
     *
     * @param conn
     */
    public static void connColse(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

