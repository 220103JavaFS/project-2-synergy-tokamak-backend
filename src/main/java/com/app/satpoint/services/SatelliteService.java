package com.app.satpoint.services;


import com.app.satpoint.models.Satellite;
import com.app.satpoint.models.User;
import com.app.satpoint.repos.SatelliteDAO;
import com.app.satpoint.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.SimpleAttributeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SatelliteService {


    private SatelliteDAO satelliteDAO;
    private UserDAO userDAO;

    public SatelliteService() {
    }

    @Autowired
    public SatelliteService(SatelliteDAO satelliteDAO, UserDAO userDAO) {
        this.satelliteDAO = satelliteDAO;
        this.userDAO = userDAO;
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
        Optional<User> userOptional = userDAO.findById(userId);
        if(!userOptional.isPresent()){
            return new ArrayList<>();
        }
        Optional<List<Satellite>> satelliteOptional = satelliteDAO.findSatellitesByFavedBy(userOptional.get());
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
