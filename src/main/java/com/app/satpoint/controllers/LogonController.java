package com.app.satpoint.controllers;

import com.app.satpoint.models.User;
import com.app.satpoint.models.UserDTO;
import com.app.satpoint.services.LogonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
public class LogonController {
    private LogonService service;
    private UserController sessionController;

    public LogonController() {
    }
    @Autowired
    public LogonController(LogonService service, UserController sessionController) {
        this.service = service;
        this.sessionController = sessionController;
    }

    @PostMapping
    public ResponseEntity<User> logon(@RequestBody UserDTO userDTO, HttpSession session){
        User user = service.logon(userDTO);
        if(user != null){
            sessionController.testSession("successful login",session);
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<User> logout(HttpServletRequest request){
        return sessionController.killSession(request);
    }
}
