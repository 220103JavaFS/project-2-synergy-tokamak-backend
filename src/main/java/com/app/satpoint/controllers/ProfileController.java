package com.app.satpoint.controllers;

import com.app.satpoint.models.User;
import com.app.satpoint.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/edit")
public class ProfileController {
    private ProfileService service;

    public ProfileController(){}

    @Autowired
    public ProfileController(ProfileService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<User> editProfile(@RequestBody User user, HttpServletRequest request){
        int userId = 0;
        try {
            userId = Integer.parseInt((String) request.getSession(false).getAttribute("userId"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        user.setId(userId);

        User updatedUser = service.editUser(user);
        if(updatedUser != null){
            return ResponseEntity.status(201).body(updatedUser);
        }
        return ResponseEntity.status(400).build();
    }
}
