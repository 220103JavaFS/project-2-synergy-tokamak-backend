package com.app.satpoint.services;

import com.app.satpoint.models.User;
import com.app.satpoint.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
