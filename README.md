# 📚 Online Book Shop (Console-Based)

The **Online Book Shop** is a **console-based Java application** that allows customers to register, log in, view books, place orders, and see their order history. This system is built using **Core Java**, **JDBC**, and **PostgreSQL**, and follows a **Maven** project structure.

---

 Features

-  *Customer Registration*
-  **Customer Login**
-  **View Available Books**
- **Place Book Orders**
-  **View My Orders (with details)**
-  **Menu-Driven Console Interface**
-  **Uses Java Collections for listing books and orders**

---

##  Technologies Used

| Technology     | Purpose                         |
|----------------|----------------------------------|
| Java (Core)    | Application Logic & OOP          |
| JDBC           | Database Connectivity            |
| PostgreSQL     | Data Storage                     |
| Maven          | Dependency Management & Build    |
| Collections    | Store and retrieve book/order list in memory |

---


Prerequisites
Before running the project, ensure you have the following installed:

Java Development Kit (JDK) 17 or higher.
Apache Maven 3.8.6 or higher.
PostgreSQL 15 or higher.
create PostgreSQL database with the following table:
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    department VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);


Maven Dependencies

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>


 Database Schema (PostgreSQL)



CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(id),
    book_id INT REFERENCES books(id),
    quantity INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


Structure:

<img width="417" height="627" alt="image" src="https://github.com/user-attachments/assets/882d2d57-4c7e-4f87-b5df-26a7c1c154d6" />



Use the consol menue to:

![Screenshot 2025-07-10 103406](https://github.com/user-attachments/assets/dc99f08b-041f-440e-b1ba-373ed0708b21)


Group Members

Shreya Shankar Tonape

Sakshi Shivaji Khandagale

Sayali Sadanand More


Contact

1) Name:Shreya Tonape
2) Email:shreyatonape65@gmail.com
3) GitHub:Shreyatonape
