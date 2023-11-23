package com.example.springboot23;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CityRepository extends ListCrudRepository<City, Integer> {

    List<City> findCities();
}
