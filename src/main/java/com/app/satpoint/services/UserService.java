package com.app.satpoint.services;

import com.app.satpoint.models.User;
import com.app.satpoint.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        super();
        this.userDAO = userDAO;
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


}
