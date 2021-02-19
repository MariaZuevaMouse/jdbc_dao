package com.company.mz.dao.implementation;

import com.company.mz.util.DBConnection;
import com.company.mz.util.DatabaseType;

import java.sql.Connection;

public class BaseSQLiteImplClass {
    protected Connection connection;

    public BaseSQLiteImplClass(DatabaseType type) {
        connection = DBConnection.getConnection(type);
    }
}
