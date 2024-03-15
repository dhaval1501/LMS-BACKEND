package com.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "pin_code")
    private int pinCode;
    @OneToOne(mappedBy ="address")
    private Student student;
}
