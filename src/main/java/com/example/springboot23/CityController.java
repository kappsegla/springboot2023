package com.example.springboot23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("api/cities")
public class CityController {

    private AtomicInteger counter = new AtomicInteger(0);

    private CityService service;

    public CityController(CityService cityService) {
        this.service = cityService;
    }

    @GetMapping
    public List<CityIdName> getAll(){
        return service.getAllCities();
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getOne(@PathVariable int id){
        var city = service.getOneCity(id);
        if( city.isPresent())
            return ResponseEntity.ok().body(city.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping("counter")
    public int counter(){
        return counter.incrementAndGet();
    }

}
