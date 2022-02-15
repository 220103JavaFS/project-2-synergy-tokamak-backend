package com.app.satpoint.controllers;

import com.app.satpoint.models.User;
import com.app.satpoint.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/edit")
public class ProfileController {
    private ProfileService service;

    public ProfileController(){}

    @Autowired
    public ProfileController(ProfileService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<User> editProfile(@RequestBody User user){
        User updatedUser = service.editUser(user);
        if(updatedUser != null){
            return ResponseEntity.status(201).body(updatedUser);
        }
        return ResponseEntity.status(400).build();
    }
}
