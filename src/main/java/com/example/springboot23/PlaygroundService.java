package com.example.springboot23;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
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
        if (location.lat() < -90 || location.lat() > 90 || location.lon() < -180 || location.lon() > 180) {
            throw new IllegalArgumentException("Invalid latitude or longitude");
        }
//        String text = "POINT (" + location.lon() + " " + location.lat() + ")";
//        Point<G2D> geo = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        var geo = Geometries.mkPoint(new G2D(location.lon(),location.lat()), WGS84);
//        var geo = DSL.point(WGS84, g(location.lat(), location.lon()));
        playground.setCoordinate(geo);
        return repository.save(playground);
    }
    public List<Playground> findAround(double lat, double lng, double distance) {
        Point<G2D> location = DSL.point(WGS84, g(lng, lat));
//        return repository.filterOnDistance("POINT(" + lat +" " + lng +")", distance);
        return repository.filterOnDistance(location, distance);
    }
}
