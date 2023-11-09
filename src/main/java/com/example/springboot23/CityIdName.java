package com.example.springboot23;

public record CityIdName(int id, String name) {

    public CityIdName(City city){
        this(city.getCityId(),city.getCityName());
    }

    public static CityIdName of(City city){
        return new CityIdName(city.getCityId(), city.getCityName());
    }
}
