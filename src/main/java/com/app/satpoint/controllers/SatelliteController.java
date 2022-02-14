package com.app.satpoint.controllers;


import com.app.satpoint.models.Satellite;
import com.app.satpoint.services.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/satellites")
public class SatelliteController {

    private SatelliteService satService;

    public SatelliteController() {
    }

    @Autowired
    public SatelliteController(SatelliteService satService) {
        this.satService = satService;
    }

    @GetMapping
    public ResponseEntity<List<Satellite>> getAllSatellites(){
        return ResponseEntity.ok().body(satService.getAllSatellites());
    }

    @GetMapping("/norad")
    public ResponseEntity<Satellite> getSatelliteByNoradId(@RequestParam("noradId") int noradId){
        return ResponseEntity.ok().body(satService.getSatelliteByNoradId(noradId));
    }

    @PostMapping()
    public ResponseEntity addSatellite(@RequestBody Satellite satellite){
        if(satService.addSatellite(satellite)){
            return ResponseEntity.status(201).build();
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }

    @PatchMapping("/inc/{noradId}")
    public ResponseEntity incrementFavorites(@PathVariable("noradId") int noradId){
        if(satService.incrementFavorites(noradId)){
            return ResponseEntity.status(202).build();
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }


    @PatchMapping("/dec/{noradId}")
    public ResponseEntity decrementFavorites(@PathVariable("noradId") int noradId){
        if(satService.decrementFavorites(noradId)){
            return ResponseEntity.status(202).build();
        }
        else {
            return ResponseEntity.status(400).build();
        }
    }


}
