package com.example.springboot23;

import com.example.springboot23.city.City;
import com.example.springboot23.city.CityRepository;
import com.example.springboot23.country.Country;
import com.example.springboot23.country.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest//(showSql = false)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=none",
})
//Uncomment the following to run with mysql and testcontainer instead of h2 database
//@TestPropertySource(properties = {
//        "spring.test.database.replace=none",
//        "spring.sql.init.mode=always",
//        "spring.datasource.url=jdbc:tc:mysql:8.2.0:///test?TC_TMPFS=/var/lib/mysql:rw"
//})
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Test
    void validTest() {
        var city = new City();
        city.setCityName("Test");
        city.setInhabitants(999);
        Country country = countryRepository.findByCountryName("Sverige");
        city.setCountry(country);
        var sCity = cityRepository.save(city);
        assertThat(cityRepository.findById(sCity.getCityId()))
                .isNotEmpty().get().isEqualTo(sCity);
    }

    @Test
    void getAllCities() {
        var cities = cityRepository.findById(1);
        assertThat(cities).isNotEmpty();
    }
}
