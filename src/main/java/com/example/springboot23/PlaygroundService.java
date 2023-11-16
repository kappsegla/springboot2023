package com.example.springboot23;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.crs.CrsRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class PlaygroundService {

    private final PlaygroundRepository repository;

    public PlaygroundService(PlaygroundRepository repository) {
        this.repository = repository;
    }

    public List<Playground> getAll() {
        return repository.findAll();
    }

    public Playground createNew(Location location) {
        var playground = new Playground();
//        String text = "POINT (" + location.lat() + " " + location.lon() + ")";
//        var geo = Wkt.fromWkt("POINT(56.74246232627352 15.90867783035189)", WGS84);
//        playground.setCoordinates((Point<G2D>) geo);
//        var geo = Geometries.mkPoint(new G2D(location.lat(),location.lon()), WGS84);
//https://medium.com/spartner/the-best-way-to-locate-in-mysql-8-e47a59892443
        var geo = DSL.point(WGS84, g(location.lat(), location.lon()));
        playground.setCoordinates((Point<G2D>) geo);
        return repository.save(playground);
    }

    public List<Playground> findAround(double lat, double lng, double distance) {
        Point<G2D> location = DSL.point(WGS84, g(lat, lng));
        return repository.filterOnDistance("POINT(" + lng +" " + lat +")", distance);
    }
}
