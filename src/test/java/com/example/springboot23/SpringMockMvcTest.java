package com.example.springboot23;

import com.example.springboot23.city.City;
import com.example.springboot23.city.CityController;
import com.example.springboot23.city.CityDto;
import com.example.springboot23.city.CityService;
import com.example.springboot23.country.CountryDto;
import com.example.springboot23.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.springboot23.matchers.ResponseBodyMatchers.responseBody;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CityController.class)
@Import(SecurityConfig.class)
public class SpringMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private CityService service;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnCityDto() throws Exception {
        CityDto expected = new CityDto(1, "Test", 999, new CountryDto("Testistan", LocalDate.of(2000, 1, 1)));

        when(service.getOneCity(1)).thenReturn(Optional.of(new CityDto(1, "Test", 999, new CountryDto("Testistan", LocalDate.of(2000, 1, 1)))));

        mockMvc.perform(get("/api/cities/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(responseBody().containsObjectAsJson(expected, CityDto.class));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturn404NotFound() throws Exception {
        when(service.getOneCity(100)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cities/100"))
                .andExpect(status().isNotFound())
                .andExpect(responseBody().containsError("title", "City Not Found"))
                .andExpect(responseBody().containsError("detail", "City with id 100 not found."));
    }

    @Test
    @WithAnonymousUser
    void shouldReturn401ForAnonymous() throws Exception {
        mockMvc.perform(get("/api/cities/1"))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void shouldReturn201CreatedIfRoleAdminForPost() throws Exception {
        CityDto cityDto = new CityDto(1, "Test", 999, new CountryDto("Testistan", LocalDate.of(2000, 1, 1)));
        var city = new City();
        city.setCityId(1);
        city.setCityName("Test");
        city.setInhabitants(999);

        when(service.create(cityDto)).thenReturn(city);

        mockMvc.perform(post("/api/cities")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(cityDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/cities/1"));
    }

    @Test
    @WithMockUser
    void shouldReturn403IfNotInRoleAdminForPost() throws Exception {
        CityDto city = new CityDto(1, "Test", 999, new CountryDto("Testistan", LocalDate.of(2000, 1, 1)));

        mockMvc.perform(post("/api/cities")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(city)))
                .andExpect(status().isForbidden());
    }
}
