/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Objects;

/**
 *
 * 
 */
public class Book {

    
    String bookID;
    String bookName;
    double bookPrice;
    int quantity;
    String publisherID;
    Publisher pL;
    String status = "Available";

    public Book(String bookID, String bookName, double bookPrice, int quantity, String publisherID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.publisherID = publisherID;
        status = (quantity > 0) ? "Available" : "Not availble";
    }

    public Book(String bookID) {
        this.bookID = bookID;
    }

    
    @Override
    public boolean equals(Object obj) {
        Book b = (Book) obj;
        return this.bookName.equalsIgnoreCase(b.bookName);
    }

    @Override
    public String toString() {
        return bookID + ", "
                + bookName + ", "
                + bookPrice + ", "
                + quantity + ", "
                + publisherID + ", "
                + status;
    }
    
    public String printAll() {
        return String.format("%-7s|%-30s|%-5.2f|%-9d|%-14s|%-14s",
                bookID, bookName, bookPrice, quantity, publisherID, status);
    }
    //Getters, setters

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public String getStatus() {
        return status;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String stringTotal() {
        return String.format("%-7s|%-30s|%-5.2f|%-9d|%-9.2f|%-14s|%-14s",
                bookID, bookName, bookPrice, quantity, getBookPrice()*getQuantity(), publisherID, status);
//        return (bookID + ", " + bookName + ", " + 
//                bookPrice + ", " + quantity + ", " + getBookPrice()*getQuantity() + ", " + 
//                publisherID + ", " + status);
    }

}
