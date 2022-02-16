package com.app.satpoint.services;

import com.app.satpoint.models.User;
import com.app.satpoint.models.UserDTO;
import com.app.satpoint.repos.LogonDao;
import com.app.satpoint.util.AppValidator;
import com.app.satpoint.util.Argon2Hasher;
import com.app.satpoint.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LogonService {
    private LogonDao dao;

    public LogonService() {
    }

    @Autowired
    public LogonService(LogonDao dao) {
        this.dao = dao;
    }
    public User logon(UserDTO userDTO){
        try{
            AppValidator.isValidSecret(userDTO.password);
            AppValidator.isValidUserName(userDTO.username);
            Optional<User> user = dao.findByUsername(userDTO.username);
            if(user.isPresent()) {
                String hashedpw = Encryption.stringToMD5(userDTO.password);
                if (hashedpw.equals(user.get().password)) {
                    return user.get();
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String username){
        Optional<User> user = dao.findByUsername(username);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

}
