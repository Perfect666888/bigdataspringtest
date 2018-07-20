package com.bigdata.demo.util;

import java.io.IOException;
import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtils {
    private static String Driver;
    private static String Username;
    private static String Password;
    private static String Url;

    static {
        //创建properties对象
        Properties properties = new Properties();
        try {
            //通过类加载器加载properties
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("DBUtils.properties"));
            //获得对应的字段
            Driver = properties.getProperty("Driver");
            Username = properties.getProperty("Username");
            Password = properties.getProperty("Password");
            Url = properties.getProperty("Url");

            //加载驱动
            try {
                Class.forName(Driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建连接的方法
    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Url, Username, Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static <T> List<T> select(String sql, Class<T> c, Object... parames) {

       //创建集合存储结果
        List<T> lists = new ArrayList<>();

        //创建连接
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        //创建sql预执行对象
        try {
            preparedStatement = connection.prepareStatement(sql);

            //传递参数，对于sql语句中的?进行赋值
            for (int i = 0; i < parames.length; i++) {
                preparedStatement.setObject(i + 1, parames[i]);
            }

            //执行sql语句
            ResultSet resultSet = preparedStatement.executeQuery();

            //获得该类的全部属性
            Field[] declaredFields = c.getDeclaredFields();
            //遍历查询结果
            while(resultSet.next()){

                //创建一个t的实例对象，存储赋值之后的结果
                T t = c.newInstance();

                //比例属性集合，去拼接set方法
                //应为需要去获得对应数据，所以不使用增强for
                //列索引是从1开始的
                for (int i = 0; i < declaredFields.length; i++) {
                    //获得属性的名字
                    String filedName = declaredFields[i].getName();

                    //凭借set方法
                    String methodName="set"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);

                    //去查找这个方法
                    Method method = c.getMethod(methodName, declaredFields[i].getType());

                    //调用该方法对对象赋值
                    //查询结果可能存在空值
                    Object column =null;
                    //根据名字取获得对应的值,前提，列名和变量名相同
                        try {
                            column=resultSet.getObject(filedName);
                            //赋值
                            method.invoke(t,column);
                        } catch (Exception e) {
                            System.out.println("不存在这个列名:"+filedName);
                        }
                }
                //添加到集合中
                lists.add(t);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }finally{
            //释放资源
            close(connection,preparedStatement);
        }


        return lists;
    }


    //释放资源的方法
    public static void close(Connection connection, Statement statement) {
        try {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
