package com.example.springboot23;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CityService {

    CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    List<CityIdName> getAllCities() {
        return repository.findCities().stream()
                .map(CityIdName::new)
                .collect(Collectors.toList());
    }

    Optional<CityDto> getOneCity(int id) {
        return map(repository.findById(id));
    }

    static Optional<CityDto> map(Optional<City> city) {
        if (city.isEmpty())
            return Optional.empty();
        var city1 = city.get();
        return Optional.of(
                new CityDto(city1.getCityId(), city1.getCityName(), city1.getInhabitants(),
                        new CountryDto(city1.getCountry().getCountryName(), city1.getCountry().getNationalDay()))
        );
    }
}
