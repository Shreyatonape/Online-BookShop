package com.bookshop.dao;

import com.bookshop.model.Book;
import com.bookshop.util.DBConnection;
import java.sql.*;
import java.util.*;

public class BookDAO {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM books";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setQuantity(rs.getInt("quantity"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

   
    public void viewBooks() {
        List<Book> books = getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("*** Available Books ***");
            for (Book book : books) {
                System.out.println("ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", Price: ₹" + book.getPrice() +
                        ", Quantity: " + book.getQuantity());
            }
        }
    }
}
