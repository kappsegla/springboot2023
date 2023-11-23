package com.example.springboot23.country;

import org.springframework.stereotype.Service;

@Service
public class CountryService {

    final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Country country(String name) {
        return repository.findByCountryName(name);
    }
}
