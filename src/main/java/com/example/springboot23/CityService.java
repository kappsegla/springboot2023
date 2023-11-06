package com.example.springboot23;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CityService {

    List<String> cities = List.of("Kalmar", "Göteborg", "Sundsvall");

    List<String> getAllCities() {
        return cities;
    }

    Optional<String> getOneCity(int id) {
        if (id < 0 || id >= cities.size())
            return Optional.empty();
        return Optional.of(cities.get(id));
    }
}
