package com.bookshop.model;

import java.time.LocalDateTime;

public class Order {
	
    private int orderId;
    private int customerId;
    private int bookId;
    private int quantity;
    private LocalDateTime orderDate;

    
    public Order() {}

    public Order(int customerId, int bookId, int quantity) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

   
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

}
