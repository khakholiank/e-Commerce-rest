package com.rgu.ecommerce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
