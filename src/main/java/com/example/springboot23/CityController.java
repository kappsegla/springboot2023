package com.example.springboot23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CityController {

    private CityService service;

    public CityController(CityService cityService) {
        this.service = cityService;
    }

    @GetMapping
    public List<String> getAll(){
        return service.getAllCities();
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getOne(@PathVariable int id){
        var city = service.getOneCity(id);
        if( city.isPresent())
            return ResponseEntity.ok().body(city.get());
        return ResponseEntity.notFound().build();
    }
}
