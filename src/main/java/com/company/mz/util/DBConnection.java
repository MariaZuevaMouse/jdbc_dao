package com.company.mz.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection realDbConnection;
    private static Connection testDbConnection;
    private static Properties appProps = new Properties();
    private static String propsPath = "src/main/resources/application.properties";
    private static String  DB_DRIVER = null;
    private static String DB_URL = null;
    private static String TEST_DB_URL = null;


    static {
        try(InputStream in = new FileInputStream(propsPath)){
            appProps.load(in);
            DB_DRIVER = appProps.getProperty("DB_DRIVER_CLASS");
            DB_URL = appProps.getProperty("DB_URL");
            TEST_DB_URL = appProps.getProperty("TEST_DB_URL");
        }catch (IOException e){
            System.out.println("Can not load properties file");
            e.printStackTrace();
        }
    }

    public static Connection getConnection(DatabaseType type){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            if(type == DatabaseType.TEST){
                connection = getTestDbConnection();
            }else {
                connection = getRealDBConnection();
            }
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static synchronized Connection getTestDbConnection()throws SQLException {
        if(testDbConnection == null){
            testDbConnection = DriverManager.getConnection(TEST_DB_URL);
        }
        return testDbConnection;
    }

    private static synchronized Connection getRealDBConnection()throws SQLException {
        if(realDbConnection == null){
            realDbConnection = DriverManager.getConnection(DB_URL);
        }
        return realDbConnection;
    }

}
