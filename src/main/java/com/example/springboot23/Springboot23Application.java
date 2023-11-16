package com.example.springboot23;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@SpringBootApplication
public class Springboot23Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot23Application.class, args);
    }

//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        CoordinateReferenceSystem<G2D> crs = WGS84;
//        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
//                .modules(new GeolatteGeomModule(crs));
//    }

    @Bean
    GeolatteGeomModule geolatteModule(){
        CoordinateReferenceSystem<G2D> crs = WGS84;
        return new GeolatteGeomModule(crs);
    }
}
