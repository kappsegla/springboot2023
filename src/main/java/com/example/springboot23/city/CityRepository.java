package com.example.springboot23.city;

import org.springframework.data.repository.ListCrudRepository;

public interface CityRepository extends ListCrudRepository<City, Integer> {
}
