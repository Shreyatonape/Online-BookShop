package com.bookshop;
               
import java.util.*;
import com.bookshop.dao.*;
import com.bookshop.model.*;
import com.bookshop.model.OrderDetails;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer loggedInCustomer = null;

        CustomerDAO customerDAO = new CustomerDAO();
        BookDAO bookDAO = new BookDAO();
        OrderDAO orderDAO = new OrderDAO();

        while (true) {
            System.out.println("\n===== Welcome to Online Bookshop =====");
            System.out.println("1. Register Customer");
            System.out.println("2. Login Customer");
            System.out.println("3. View Books");
            System.out.println("4. Order Book");
            System.out.println("5. View My Orders");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
            
                case 1:
                	
                           System.out.print("Enter name: ");
                           String name = sc.nextLine();
                           System.out.print("Enter email: ");
                           String email = sc.nextLine();
                           System.out.print("Enter password: ");
                           String password = sc.nextLine();

                           Customer newCustomer = new Customer(name, email, password);
                           boolean registered = customerDAO.registerCustomer(newCustomer);
                           if (registered) {
                           System.out.println("Customer registered successfully!");
                           } else {
                           System.out.println("Registration failed!");
                           }
                           break;

                case 2:
                           System.out.print("Enter email: ");
                           String loginEmail = sc.nextLine();
                           System.out.print("Enter password: ");
                           String loginPassword = sc.nextLine();

                           Customer customer = customerDAO.loginCustomer(loginEmail, loginPassword);
                           if (customer != null) {
                           loggedInCustomer = customer;
                           System.out.println("Login successful! Welcome, " + customer.getName());
                           } else {
                           System.out.println("Invalid login credentials.");
                           }
                           break;

                case 3:
                           bookDAO.viewBooks();
                           break;

               
                case 4:
                           if (loggedInCustomer == null) {
                           System.out.println("You need to login first to place an order.");
                           } else {
                           bookDAO.viewBooks();
                           System.out.print("Enter Book ID to order: ");
                           int bookId = sc.nextInt();
                           System.out.print("Enter quantity: ");
                           int qty = sc.nextInt();

                           Order order = new Order(loggedInCustomer.getCustomerId(), bookId, qty);
                           boolean placed = orderDAO.placeOrder(order);
                           if (placed) {
                            System.out.println("Order placed successfully!");
                           } else {
                            System.out.println("Order failed.");
                           }
                           }
                           break;

                case 5:
                    
                	       if (loggedInCustomer == null) {
                           System.out.println("You need to login first to view your orders.");
                           } else {
                           List<OrderDetails> orders = orderDAO.getOrderDetailsByCustomerId(loggedInCustomer.getCustomerId());
                           if (orders.isEmpty()) {
                            System.out.println("You have not placed any orders yet.");
                           } else {
                            System.out.println("===== Your Orders =====");
                            for (OrderDetails od : orders) {
                                System.out.println("Order ID: " + od.getOrderId() +
                                                   ", Title: " + od.getBookTitle() +
                                                   ", Price: ₹" + od.getBookPrice() +
                                                   ", Quantity: " + od.getQuantity() +
                                                   ", Date: " + od.getOrderDate());
                            }
                        }
                    }
                    break;
                    
                case 6:
                            System.out.println("Thank you for visiting the Online Bookshop!");
                            System.exit(0);
                            break;
                            default:
                            System.out.println("Invalid choice.");
                 }
               }
             }
           }

		
       	


