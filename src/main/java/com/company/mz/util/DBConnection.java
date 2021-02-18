package com.company.mz.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Properties appProps = new Properties();
    private static String propsPath = "src/main/resources/application.properties";
    private static String  DB_DRIVER = null;
    private static String DB_URL = null;
    private static String DB_USER = null;
    private static String DB_PASSWORD = null;

    static {
        try(InputStream in = new FileInputStream(propsPath)){
            appProps.load(in);
            DB_DRIVER = appProps.getProperty("DB_DRIVER_CLASS");
            DB_URL = appProps.getProperty("DB_URL");
//            DB_USER = appProps.getProperty("DB_USERNAME");
//            DB_PASSWORD = appProps.getProperty("DB_USERNAME");
        }catch (IOException e){
            System.out.println("Can not load properties file");
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
//            System.out.println("database connection successfully created");
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
