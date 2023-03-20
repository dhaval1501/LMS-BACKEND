package com.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "a_name")
    private String author;
    @Column(name = "a_quantity")
    private String availableQuantity;
    @Column(name = "t_quantity")
    private String totalQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }


    public Book(Long id, String name, String author, String availableQuantity, String totalQuantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.availableQuantity = availableQuantity;
        this.totalQuantity = totalQuantity;
    }

    public Book(){}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", availableQuantity='" + availableQuantity + '\'' +
                ", totalQuantity='" + totalQuantity + '\'' +
                '}';
    }
}
