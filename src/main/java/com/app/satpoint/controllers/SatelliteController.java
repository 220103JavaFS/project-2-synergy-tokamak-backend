package com.app.satpoint.controllers;


import com.app.satpoint.models.Satellite;
import com.app.satpoint.services.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/satellites")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
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

    @GetMapping("/satName")
    public ResponseEntity<Satellite> getSatByName(@RequestParam("satName") String satName){
        Satellite satellite = satService.getSatByName(satName);
        if(satellite != null){
            return ResponseEntity.ok().body(satellite);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/check")
    public ResponseEntity<Satellite> checkByNoradId(@RequestParam("noradId") int noradId){
        Satellite sat = satService.checkByNoradId(noradId);
        if(sat != null) {
            return ResponseEntity.ok().body(sat);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<Set<Satellite>> getUserFavoriteSatellites(@PathVariable("userId") int userId){
        return ResponseEntity.ok().body(satService.getFavoriteSatellites(userId));
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

    @GetMapping("/favorites")
    public ResponseEntity<List<Satellite>> getTopFavorites(HttpServletRequest request){
        int userId = 0;
        try {
            userId = Integer.parseInt((String) request.getSession(false).getAttribute("userId"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().body(satService.findTopByOrderByNumFavoritesDesc(userId));
    }


}
