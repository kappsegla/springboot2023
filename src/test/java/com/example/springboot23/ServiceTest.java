package com.example.springboot23;

import com.example.springboot23.city.City;
import com.example.springboot23.city.CityIdName;
import com.example.springboot23.city.CityRepository;
import com.example.springboot23.city.CityService;
import com.example.springboot23.country.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    CityRepository repository;

    @InjectMocks
    CityService service;

    @Test
    void cityService() {
        City city1 = new City();
        city1.setCityId(1);
        city1.setCityName("Test");
        var country = new Country();
        country.setCountryName("Testistan");
        country.setId(1);
        city1.setCountry(country);
        City city2 = new City();
        city2.setCityId(2);
        city2.setCityName("Home");
        city2.setCountry(country);
        Mockito.when(repository.findAll()).thenReturn(List.of(city1, city2));

        var cities = service.getAllCities();

        assertThat(cities).containsSequence(new CityIdName(1, "Test"), new CityIdName(2, "Home"));
    }
}
