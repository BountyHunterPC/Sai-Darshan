package com.shirdi.darshan.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aarti_bookings")
public class AartiBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;   

    private String aartiType;

    private LocalDate bookingDate;
    
    private String aartiTime;
    
    private Integer numberOfPeople;

    private double price;

    private String status;

    // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAartiType() {
        return aartiType;
    }

    public void setAartiType(String aartiType) {
        this.aartiType = aartiType;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getAartiTime() {
        return aartiTime;
    }

    public void setAartiTime(String aartiTime) {
        this.aartiTime = aartiTime;
    }
    
    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
