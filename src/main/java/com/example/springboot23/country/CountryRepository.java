package com.example.springboot23.country;

import org.springframework.data.repository.ListCrudRepository;

public interface CountryRepository extends ListCrudRepository<Country, Integer> {

    Country findByCountryName(String name);
}
