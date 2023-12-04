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
public class Publisher {
    String publisherID;
    String publisherName;
    String phone;

    public Publisher() {
    }
    
    public Publisher(String publisherID, String publisherName, String phone) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.phone = phone;
    }
    
    //contructor phục vụ chức năng tìm kiếm

    public Publisher(String publisherID) {
        this.publisherID = publisherID;
    }
    
    @Override
    public boolean equals(Object obj) {
        Publisher p = (Publisher) obj;
        return this.publisherID.equalsIgnoreCase(p.publisherID);
    }

    @Override
    public String toString() {
        return this.publisherID + ", " +
                this.publisherName + ", " +
                this.phone;
    }
    
    public String printAll() {
        return String.format("|%-12s|%-30s|%-12s",
                publisherID, publisherName, phone);
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPublisherName() {
        return publisherName;
    }
    
}
