package com.example.springboot23.city;

import com.example.springboot23.country.CountryDto;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link City}
 */
public record CityDto(Integer id, @Size(max = 255) String name, Integer inhabitants,
                      CountryDto country) implements Serializable {
}
