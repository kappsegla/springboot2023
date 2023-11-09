package com.example.springboot23;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private Integer cityId;

    @Size(max = 255)
    @Column(name = "city_name")
    private String cityName;

    @Column(name = "inhabitants")
    private Integer inhabitants;

    public Integer getCityId() {
        return cityId;
    }

    public Integer getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(Integer inhabitants) {
        this.inhabitants = inhabitants;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
