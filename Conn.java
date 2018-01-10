package com.allen;

import java.sql.*;

/*  通过Connection对象中的getConnection()方法，获取数据库的连接，并将数据库中的数据查询出来。
*
* */
public class Conn {
    //Connection接口代表与特定的数据库连接，在连接上下文中执行sql语句并返回结果
    private static Connection con;
    //Statement接口用于在已经建立连接的基础上向数据库发送sql语句
    private static Statement sql;
    //ResultSet接口类似于一个临时表，用来暂时存放数据库查询操作所获得的结果集
    private static ResultSet res;

    private Connection getConnection(){
        //forName()方法用来加载数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功！");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        //getConnection()方法用来建立数据库连接
        try {
            con = DriverManager.getConnection("jdbc:mysql:"+"//127.0.0.1:3306/test","root","0311");
            System.out.println("数据库连接成功！");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    public static void main(String[] args) {
        Conn ts = new Conn();
        ts.getConnection();
        try {
            sql = con.createStatement();
            res = sql.executeQuery("select * from t_employee");
            //如果当前语句不是最后一条，则进入循环。
            while (res.next()){
                String id   =  res.getString("Id");
                String name =  res.getString("Name");
                String hir  =  res.getString("Hiredate");
                String sal  =  res.getString("Salary");
                String deptNo  =  res.getString("DeptNo");

                System.out.print("  编号：  " + id );
                System.out.print("  姓名：  " + name );
                System.out.print("  上班日期：  " + hir );
                System.out.print("  薪资：  " + sal );
                System.out.println("  部门：  " + deptNo );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
