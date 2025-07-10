package com.bookshop.dao;

import com.bookshop.model.Customer;
import com.bookshop.util.DBConnection;
import java.sql.*;

public class CustomerDAO {
	
    public boolean registerCustomer(Customer customer) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO customers (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer loginCustomer(String email, String password) {
    
    	String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, email);
               stmt.setString(2, password);
               
               ResultSet rs = stmt.executeQuery();
               if (rs.next()) {
                   int id = rs.getInt("id");
                   String name = rs.getString("name");
                   
                   Customer customer = new Customer(name, email, password);
                   customer.setCustomerId(id);
                   return customer;
               } else {
                   System.out.println("No matching customer found.");  
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }

           return null;
    }
    	
}
