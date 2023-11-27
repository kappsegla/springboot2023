package com.example.springboot23.city;

import com.example.springboot23.country.Country;
import com.example.springboot23.country.CountryDto;
import com.example.springboot23.country.CountryService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CityService {

    RestClient client;

    final CityRepository repository;
    final CountryService countryService;

    public CityService(CityRepository repository, CountryService countryService, RestClient client) {
        this.repository = repository;
        this.countryService = countryService;
        this.client = client;
    }

    public List<CityIdName> getAllCities() {
        return repository.findAll().stream()
                .map(CityIdName::new)
                .collect(Collectors.toList());
    }

    public Optional<CityDto> getOneCity(int id) {
        var result = client.get().uri("/api/countries").retrieve().body(new ParameterizedTypeReference<List<Country>>(){});
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

    public City create(CityDto city) {
        City cityEntity = new City();
        cityEntity.setCityName(city.name());
        cityEntity.setCountry(countryService.country(city.country().name()));
        cityEntity.setInhabitants(city.inhabitants());

        return repository.save(cityEntity);

    }
}
