package com.app.satpoint.services;

import com.app.satpoint.models.User;
import com.app.satpoint.models.UserDTO;
import com.app.satpoint.repos.LogonDao;
import com.app.satpoint.util.AppValidator;
import com.app.satpoint.util.Argon2Hasher;
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
                //need to check hashed pw to inputted pw
                //looking into Spring Security for now using Argon2Hasher
//                if (Argon2Hasher.verify(user.get().getPassword(), userDTO.password)) {
                    return user.get();
//                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
