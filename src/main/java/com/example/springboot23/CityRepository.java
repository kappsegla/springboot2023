package com.example.springboot23;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CityRepository extends ListCrudRepository<City,Integer> {

    @Query("select c from City c where c.cityName = ?#{ principal?.username }")
    List<City> findCities();
}
