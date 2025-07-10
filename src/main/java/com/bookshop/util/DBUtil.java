package com.bookshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	
	  private static final String URL = "jdbc:postgresql://localhost:5432/bookshop";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "pass@123";

	    public static Connection getConnection() {
	        try {
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

}
