package com.app.satpoint.controllers;


import com.app.satpoint.models.User;
import com.app.satpoint.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
@CrossOrigin
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }



    @PostMapping
    public ResponseEntity<List<User>> newUser(@RequestBody User user){
        if(userService.addUser(user)){
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();

    }
}
