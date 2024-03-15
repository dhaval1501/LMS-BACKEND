package com.lms.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;


@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "number")
    private BigInteger number;
    @Column(name = "email")
    private String email;

    public Student(Long id, String firstName, String lastName, String address, BigInteger number, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.number = number;
        this.email = email;
    }

    public Student(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}