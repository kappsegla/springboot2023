package com.example.springboot23;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/cities")
public class CityController {

    private final CityService service;

    public CityController(CityService cityService) {
        this.service = cityService;
    }

    @GetMapping
//    @PostFilter("filterObject.id() != 1")
    public List<CityIdName> getAll() {
//        throw new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "entity not found"
//        );
//        return new ArrayList<>(service.getAllCities());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        authentication.getAuthorities().forEach(System.out::println);
//        var jwt = (org.springframework.security.oauth2.jwt.Jwt) authentication.getPrincipal();

        return service.getAllCities();
    }

    @GetMapping("{id}")
    public ResponseEntity<CityDto> getOne(@PathVariable int id) {
        var city = service.getOneCity(id);
        return city.map(cityDto -> ResponseEntity.ok().body(cityDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }
                                                                                                            }
