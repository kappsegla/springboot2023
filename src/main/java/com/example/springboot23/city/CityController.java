package com.example.springboot23.city;

import com.example.springboot23.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
//        throw new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "entity not found"
//        );
//        return new ArrayList<>(service.getAllCities());
        return service.getAllCities();
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> getOne(@PathVariable int id) {
        var city = service.getOneCity(id);
        return city.map(cityDto -> ResponseEntity.ok().body(cityDto))
                .orElseThrow(() -> new ResourceNotFoundException("", Integer.toString(id)));
    }

    @PostMapping
    public ResponseEntity<City> create(@RequestBody CityDto city){
        City createdCity = service.create(city);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCity.getCityId())
                .toUri();

        return ResponseEntity.created(location).body(createdCity);
    }
}
