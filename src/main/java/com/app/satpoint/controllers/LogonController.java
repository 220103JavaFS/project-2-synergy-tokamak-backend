package com.app.satpoint.controllers;

import com.app.satpoint.models.User;
import com.app.satpoint.models.UserDTO;
import com.app.satpoint.services.LogonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/getUser/{username}")
    public ResponseEntity<User> getUser(HttpSession session, @PathVariable("username") String username,
            HttpServletRequest request){
//        System.out.println(username);
//        if(request.getSession(false) == null) {
//            return ResponseEntity.status(401).build();
//        }
        return ResponseEntity.status(200).body(service.getUser(username));
    }

    @PostMapping
    public ResponseEntity<Object> logon(@RequestBody UserDTO userDTO, HttpSession session){

        ArrayList<String> result = new ArrayList<>();

        User user = service.logon(userDTO);
        if(user != null){
            session.setAttribute("userId", Long.toString(user.getId()));
            session.setAttribute("username", user.getUsername());
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            session.setAttribute("email", user.getEmail());
            result.add(Long.toString(user.getId()));
            result.add(user.getUsername());
            result.add(user.getFirstName());
            result.add(user.getLastName());
            result.add(user.getEmail());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(401).build();
    }


    @PostMapping("/logout")
    public ResponseEntity<User> logout(HttpServletRequest request){
        request.getSession(false).invalidate();
        return ResponseEntity.ok().build();
    }
}
