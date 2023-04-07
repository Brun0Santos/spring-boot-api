package com.bruno.api.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    @Column(name = "launch_date")
    @Temporal(TemporalType.DATE)
    private Date launchDate;
    private double price;
    private String title;

    public Books(Long id, String author, Date launch_date, double price, String title) {
        this.id = id;
        this.author = author;
        this.launchDate = launch_date;
        this.price = price;
        this.title = title;
    }

    public Books() {
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
