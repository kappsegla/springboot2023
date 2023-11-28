package com.example.springboot23.geolocation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    GeoCodeService geoCodeService;

    public LocationController(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }

    @GetMapping("/api/geo")
    String lookup(@RequestParam float lat, @RequestParam float lon) {
        return geoCodeService.reverseGeoCode(lat,lon);
    }
}
