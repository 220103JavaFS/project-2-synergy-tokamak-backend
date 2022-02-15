package com.app.satpoint.services;

import com.app.satpoint.models.User;
import com.app.satpoint.repos.LogonDao;
import com.app.satpoint.repos.ProfileDAO;
import com.app.satpoint.util.AppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private LogonDao logonDao;
    private ProfileDAO dao;
    public ProfileService() {}
    @Autowired
    public ProfileService(ProfileDAO dao, LogonDao logonDao){
        this.dao = dao;
        this.logonDao = logonDao;
    }

    public User editUser(User user){
        try{
            Optional<User> userz = logonDao.findByUsername(user.getUsername());

            if(userz.isPresent()){
                User u = userz.get();
            if(u != null) {
                if (AppValidator.isValidEmail(user.getEmail())) {
                    u.setEmail(user.getEmail());
                }
                if (AppValidator.isValidName(user.getFirstName(), user.getLastName())) {
                    if (!AppValidator.isEmpty(user.getFirstName()) && !AppValidator.isEmpty(user.getLastName())) {
                        u.setFirstName(user.getFirstName());
                        u.setLastName(user.getLastName());
                    } else if (!AppValidator.isEmpty(user.getFirstName())) {
                        u.setFirstName(user.getFirstName());
                    } else {
                        u.setLastName(user.getLastName());
                    }
                }
                dao.save(u);
                return logonDao.getById(u.getId());
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
