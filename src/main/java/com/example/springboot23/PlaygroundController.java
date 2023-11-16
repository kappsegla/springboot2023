package com.example.springboot23;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/playgrounds")
public class PlaygroundController {

    private final PlaygroundService service;

    public PlaygroundController(PlaygroundService service) {
        this.service = service;
    }

//    @GetMapping
//    List<Playground> allPlaygrounds() {
//        return service.getAll();
//    }

    @PostMapping
    public ResponseEntity<Void> createNew(@RequestBody Location playgroundLocation) {
        var playg = service.createNew(playgroundLocation);
        //Todo: Add created with uri
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public List<Playground> getPlaygroundsInCircle(
            @RequestParam(required = false,
                    defaultValue = "0") double lat,
            @RequestParam(required = false,
                    defaultValue = "0") double lng,
            @RequestParam(required = false,
                    defaultValue = "0") double dist) {
        if (dist == 0.0)
            return service.getAll();
        return service.findAround(lat, lng, dist);
    }
}
