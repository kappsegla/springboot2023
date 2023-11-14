package com.example.springboot23;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    String city;

    String streetName;

    int houseNumber;
}
