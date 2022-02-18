package com.app.satpoint.services;

import com.app.satpoint.models.Satellite;
import com.app.satpoint.models.User;
import com.app.satpoint.repos.SatelliteDAO;
import com.app.satpoint.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    private UserDAO userDAO;
    private SatelliteDAO satelliteDAO;

    @Autowired
    public UserService(UserDAO userDAO, SatelliteDAO satelliteDAO) {
        this.userDAO = userDAO;
        this.satelliteDAO = satelliteDAO;
    }


    public boolean addUser(User user){
        try{
            userDAO.save(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User getUserByUsername(String username){
        Optional<User> userOptional = userDAO.findUserByUsername(username);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            return new User();
        }
    }

    public User getUserByUserId(int userId){
        Optional<User> userOptional = userDAO.findById((long)userId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            return new User();
        }
    }

    public boolean addFavoriteSatellite(int userId, int noradId){
        Optional<User> userOptional = userDAO.findById((long)userId);
        if(!userOptional.isPresent()){
            return false;
        }
        Optional<Satellite> satelliteOptional = satelliteDAO.findSatelliteByNoradId(noradId);
        if(!satelliteOptional.isPresent()){
            return false;
        }
        User user = userOptional.get();
        Satellite sat = satelliteOptional.get();

        Set<Satellite> favSats = user.getFavorites();

        if(favSats.add(sat)){
            user.setFavorites(favSats);
            sat.getFavedBy().add(user);
            sat.setNumFavorites(sat.getNumFavorites()+1);
            userDAO.save(user);
            satelliteDAO.save(sat);
            return true;
        }
        return false;
    }

    public boolean deleteFavoriteSatellite(int userId, int noradId){
        Optional<User> userOptional = userDAO.findById((long)userId);
        if(!userOptional.isPresent()){
            return false;
        }
        Optional<Satellite> satelliteOptional = satelliteDAO.findSatelliteByNoradId(noradId);
        if(!satelliteOptional.isPresent()){
            return false;
        }
        User user = userOptional.get();
        Satellite sat = satelliteOptional.get();

        if(user.getFavorites().remove(sat)){
            sat.getFavedBy().remove(user);
            sat.setNumFavorites(sat.getNumFavorites()-1);
            userDAO.save(user);
            satelliteDAO.save(sat);
            return true;
        }
        return false;
    }


}
