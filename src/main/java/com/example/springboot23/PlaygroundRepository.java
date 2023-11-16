package com.example.springboot23;


import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaygroundRepository extends ListCrudRepository<Playground, Integer> {

//    @Query("""
//            select p from Playground p where within(p.coordinates, :geoArea) = true
//                """)
//    List<Playground> filterOnArea(@Param("geoArea") Geometry<G2D> geoArea);
//
//    @Query("""
//            select p from Playground p where distance(p.coordinate, :location) < :distance
//                """)
//    List<Playground> filterOnDistance(@Param("location") Point<G2D> location, @Param("distance") double distance);

@Query(value = """
        SELECT * FROM playground
        WHERE ST_Distance_Sphere(coordinate, :location) < :distance
            """, nativeQuery = true)
List<Playground> filterOnDistance(@Param("location") Point<G2D> location, @Param("distance") double distance);
}
