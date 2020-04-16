package com.wsk.study.jdbc;

import java.sql.*;

/**
 * @Description: 根据主键生成，批量查询的SQL
 * @Auther: wsk
 * @Date: 2020/3/5 11:21
 * @Version: 1.0
 */
public class ProducerSQLScript {


    private static final String URL = "jdbc:mysql://10.199.138.6:3306/product_customer_center_test?characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "servyou1234";
    private static final String TABLE_NAME = "cc_group_profile_info";
    private static final int    PAGE_SIZE = 100000;
    private static Connection conn = null ;
    private static PreparedStatement preppaerstatementCountSQL = null ;
    private static PreparedStatement preppaerstatementInsertSQL = null ;

    private static int insertCount = 0 ;
    private static long num = 0 ;


    public static void main(String[] args) throws SQLException {
        conn();
        ResultSet p1 = conn.prepareStatement("select min(id) from " + TABLE_NAME).executeQuery();
        ResultSet p2 = conn.prepareStatement("select max(id) from " + TABLE_NAME).executeQuery();
        long beging = 0L;
        long end = 0L;
        if(p1.next()){
            beging = p1.getLong(1);
        }
        if(p2.next()){
            end = p2.getLong(1)+1;
        }


        preppaerstatementCountSQL.setLong(1, beging);
        preppaerstatementCountSQL.setLong(2, end);
        ResultSet rs = preppaerstatementCountSQL.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        int num = count / PAGE_SIZE;
        num = count % PAGE_SIZE == 0 ? num : (num + 1);

        long realPageSize = (end-beging)/num;
        for (int i = 0; i < num; i++) {
            producerInsertSQL(i * realPageSize, (i + 1) * realPageSize);
        }

        System.out.println("自动插入" + insertCount + "条SQL");
    }


    private static void producerInsertSQL(long beginig,long end) throws SQLException{
        preppaerstatementCountSQL.setLong(1, beginig);
        preppaerstatementCountSQL.setLong(2, end);
        ResultSet rs = preppaerstatementCountSQL.executeQuery();
        if(rs.next()){
            num = rs.getLong(1);
        }
        if (num>PAGE_SIZE){
            long  half = (end-beginig)/2 +beginig;
            producerInsertSQL(beginig,half);
            producerInsertSQL(half,end);
        }else if(num<=PAGE_SIZE&&num>0){
            insertCount++;
            System.out.println("id>="+beginig+" and id<"+end);
        }
        num =0;
    }


    /**
     * Statement 和 PreparedStatement之间的关系和区别.
     * 关系：PreparedStatement继承自Statement,都是接口
     * 区别：PreparedStatement可以使用占位符，是预编译的，批处理比Statement效率高
     */
    public static void conn() {
        // 1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库链接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3./预编译,通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            String sql = "select count(1) from "+ TABLE_NAME +" where id>=? and id<?  ";
            preppaerstatementCountSQL = conn.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close() {
        if (preppaerstatementCountSQL !=null){
            try {
                preppaerstatementCountSQL.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}