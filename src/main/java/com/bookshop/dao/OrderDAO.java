package com.bookshop.dao;

import java.sql.*;
import java.util.*;
import com.bookshop.model.Order;
import com.bookshop.model.OrderDetails;
import com.bookshop.util.DBUtil;

public class OrderDAO {

    
    public boolean placeOrder(Order order) {
        try (Connection conn = DBUtil.getConnection()) {

            
            PreparedStatement checkBook = conn.prepareStatement("SELECT quantity FROM books WHERE id = ?");
            checkBook.setInt(1, order.getBookId());
            ResultSet rs = checkBook.executeQuery();

            if (!rs.next()) {
                System.out.println("Book not found");
                return false;
            }

            int availableQty = rs.getInt("quantity");
            if (order.getQuantity() > availableQty) {
                System.out.println("Not enough books available. Remaining: " + availableQty);
                return false;
            }

            
            PreparedStatement insertOrder = conn.prepareStatement(
                "INSERT INTO orders (customer_id, book_id, quantity) VALUES (?, ?, ?)");
            insertOrder.setInt(1, order.getCustomerId());
            insertOrder.setInt(2, order.getBookId());
            insertOrder.setInt(3, order.getQuantity());
            insertOrder.executeUpdate();

           
            PreparedStatement updateBook = conn.prepareStatement(
                "UPDATE books SET quantity = quantity - ? WHERE id = ?");
            updateBook.setInt(1, order.getQuantity());
            updateBook.setInt(2, order.getBookId());
            updateBook.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

   
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("customer_id"),
                    rs.getInt("book_id"),
                    rs.getInt("quantity")
                );
                order.setBookId(rs.getInt("id"));  
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

   
    public List<OrderDetails> getOrderDetailsByCustomerId(int customerId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        String sql = "SELECT o.id AS order_id, b.title, b.price, o.quantity, o.order_date " +
                     "FROM orders o JOIN books b ON o.book_id = b.id WHERE o.customer_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetails od = new OrderDetails();
                od.setOrderId(rs.getInt("order_id"));
                od.setBookTitle(rs.getString("title"));
                od.setBookPrice(rs.getDouble("price"));
                od.setQuantity(rs.getInt("quantity"));
                od.setOrderDate(rs.getTimestamp("order_date"));
                orderDetailsList.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetailsList;
    }
}
