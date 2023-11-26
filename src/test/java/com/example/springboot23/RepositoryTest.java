package com.example.springboot23;

import com.example.springboot23.city.City;
import com.example.springboot23.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=none"
})
public class RepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    void validTest() {
        var city = new City();
        city.setCityName("Test");
        city.setInhabitants(999);
        var sCity = cityRepository.save(city);
        assertThat(cityRepository.findById(sCity.getCityId()))
                .isNotEmpty().get().isEqualTo(sCity);
    }
}
