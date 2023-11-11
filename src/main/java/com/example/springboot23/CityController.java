package com.example.springboot23;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService cityService) {
        this.service = cityService;
    }

    @GetMapping
    public List<CityIdName> getAll() {
        return service.getAllCities();
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> getOne(@PathVariable int id) {
        var city = service.getOneCity(id);
        return city.map(cityDto -> ResponseEntity.ok().body(cityDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
