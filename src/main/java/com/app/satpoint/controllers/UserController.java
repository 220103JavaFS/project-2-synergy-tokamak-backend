package com.app.satpoint.controllers;


import com.app.satpoint.models.FavDTO;
import com.app.satpoint.models.User;
import com.app.satpoint.services.UserService;
import com.app.satpoint.util.Encryption;
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
    public ResponseEntity<User> newUser(@RequestBody User user){
        user.password = Encryption.stringToMD5(user.password);
        if(userService.addUser(user)){

            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();

    }

    @PostMapping("/fav/add")
    public ResponseEntity addFavoriteSatellites(@RequestBody FavDTO favorite){
        if(userService.addFavoriteSatellite(favorite.getUserId(),favorite.getNoradId())){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/fav/remove")
    public ResponseEntity removeFavoriteSatellite(@RequestBody FavDTO favorite){
        if(userService.deleteFavoriteSatellite(favorite.getUserId(), favorite.getNoradId())){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }
}
