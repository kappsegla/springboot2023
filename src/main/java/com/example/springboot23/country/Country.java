package com.example.springboot23.country;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "country", schema = "test")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "country_name")
    private String countryName;

    @Size(max = 255)
    @Column(name = "language_code")
    private String languageCode;

    @Size(max = 255)
    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "national_day")
    private LocalDate nationalDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getNationalDay() {
        return nationalDay;
    }

    public void setNationalDay(LocalDate nationalDay) {
        this.nationalDay = nationalDay;
    }

}
