package com.example.springboot23;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CityService {

    CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    List<CityIdName> getAllCities() {
        return repository.findAll().stream()
                .map(CityIdName::new)
                .toList();
    }

    Optional<City> getOneCity(int id) {
        return repository.findById(id);
    }
}
