package com.app.satpoint.services;


import com.app.satpoint.models.Satellite;
import com.app.satpoint.repos.SatelliteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.SimpleAttributeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SatelliteService {


    private SatelliteDAO satelliteDAO;

    public SatelliteService() {
    }

    @Autowired
    public SatelliteService(SatelliteDAO satelliteDAO) {
        this.satelliteDAO = satelliteDAO;
    }

    public List<Satellite> getAllSatellites(){
        return satelliteDAO.findAll();
    }

    public Satellite getSatelliteByNoradId(int noradId){
        Optional<Satellite> satelliteOptional = satelliteDAO.findSatelliteByNoradId(noradId);
        if(satelliteOptional.isPresent()){
            return satelliteOptional.get();
        }
        return new Satellite();
    }

    public boolean addSatellite(Satellite satellite){
        try{
            satellite.setNumFavorites(0);
            satellite.setSatId(0);
            satellite.setComments(new ArrayList<>());
            satelliteDAO.save(satellite);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean incrementFavorites(int noradId){
        Optional<Satellite> satelliteOptional = satelliteDAO.findSatelliteByNoradId(noradId);
        if(satelliteOptional.isPresent()){
            Satellite s = satelliteOptional.get();
            s.incrementFavorites();
            satelliteDAO.save(s);
            return true;
        }
        return false;
    }

    public boolean decrementFavorites(int noradId){
        Optional<Satellite> satelliteOptional = satelliteDAO.findSatelliteByNoradId(noradId);
        if(satelliteOptional.isPresent()){
            Satellite s = satelliteOptional.get();
            s.decrementFavorites();
            satelliteDAO.save(s);
            return true;
        }
        return false;
    }

    public List<Satellite> getSatelliteByUserId(int userId) {
        Optional<List<Satellite>> satelliteOptional = satelliteDAO.findSatellitesByUserId(userId);
        if(satelliteOptional.isPresent()){
            return satelliteOptional.get();
        }
        return new ArrayList<>();
    }

    public List<Satellite> findTop5ByOrderByNumFavoritesDesc() {
        Optional<List<Satellite>> satelliteOptional = satelliteDAO.findTop5ByOrderByNumFavoritesDesc();
        if(satelliteOptional.isPresent()){
            return satelliteOptional.get();
        }
        return new ArrayList<>();
    }


}
