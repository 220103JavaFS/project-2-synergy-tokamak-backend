package com.app.satpoint.controllers;

import com.app.satpoint.models.User;
import com.app.satpoint.models.UserDTO;
import com.app.satpoint.services.LogonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logon")
public class LogonController {
    private LogonService service;

    public LogonController() {
    }
    @Autowired
    public LogonController(LogonService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<User> logon(@RequestBody UserDTO userDTO){
        User user = service.logon(userDTO);
        if(user != null){
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(401).build();
    }
}
