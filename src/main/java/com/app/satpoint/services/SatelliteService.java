package com.app.satpoint.services;


import com.app.satpoint.models.Satellite;
import com.app.satpoint.models.User;
import com.app.satpoint.repos.SatelliteDAO;
import com.app.satpoint.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.SimpleAttributeSet;
import java.util.*;

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

    public Satellite getSatByName(String satName){
        Optional<Satellite> satellite = satelliteDAO.findSatelliteBySatName(satName);
        if(satellite.isPresent()){
            return satellite.get();
        }
        return null;
    }

    public Satellite checkByNoradId(int noradId){
        if(noradId >= 0){
            Optional<Satellite> satelliteOptional = satelliteDAO.findSatelliteByNoradId(noradId);
            if(satelliteOptional.isPresent()) {
                return satelliteOptional.get();
            }
        }

        return null;
    }

    public boolean addSatellite(Satellite satellite){
        try{
            satellite.setNumFavorites(0);
            satellite.setSatId(0);
            satellite.setComments(Collections.emptySet());
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

    public Set<Satellite> getFavoriteSatellites(int userId) {
        Optional<User> userOptional = userDAO.findById((long)userId);
        if(userOptional.isPresent()){
            Set<Satellite> satSet = userOptional.get().getFavorites();
            satSet.forEach(satellite -> {satellite.setFavorite(true);});
            return satSet;
        }
        return Collections.emptySet();
    }

    public List<Satellite> findTopByOrderByNumFavoritesDesc(int userId) {
        Optional<List<Satellite>> satelliteOptional = satelliteDAO.findTop20ByOrderByNumFavoritesDesc();
        Optional<User> userOptional = userDAO.findById((long)userId);
        User user = userOptional.get();
        if(satelliteOptional.isPresent()){
            //set the isFavorite Var for each Sat in the list for the requesting user
            List<Satellite> satList = satelliteOptional.get();
            satList.forEach(satellite -> {
                if(user.getFavorites().contains(satellite)){
                    satellite.setFavorite(true);
                }
            });
            return satList;
        }
        return new ArrayList<>();
    }


}
