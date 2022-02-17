package com.app.satpoint.controllers;


import com.app.satpoint.models.FavDTO;
import com.app.satpoint.models.User;
import com.app.satpoint.services.UserService;
import com.app.satpoint.util.Encryption;
import com.app.satpoint.util.Argon2Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "user")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }



    @PostMapping
    public ResponseEntity<User> newUser(@RequestBody User user){
        user.password = Encryption.stringToMD5(user.password);
        System.out.println(user);
        if(userService.addUser(user)){


            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();

    }

    @PostMapping("/fav/add")
    public ResponseEntity addFavoriteSatellites(@RequestBody FavDTO favorite, HttpServletRequest request){
        int userId = 0;
        try {
            userId = Integer.parseInt((String) request.getSession(false).getAttribute("userId"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

        if(userService.addFavoriteSatellite(userId,favorite.getNoradId())){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/fav/remove")
    public ResponseEntity removeFavoriteSatellite(@RequestBody FavDTO favorite, HttpServletRequest request){
        int userId = 0;
        try {
            userId = Integer.parseInt((String) request.getSession(false).getAttribute("userId"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


        if(userService.deleteFavoriteSatellite(userId, favorite.getNoradId())){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }


    //Test session endpoints

    //creates a session if one doesn't already exist and adds attributes to it,
    @PostMapping("/session")
    public ResponseEntity testSession(@RequestParam(value = "message", required = false) String message, HttpSession session){

        List<String> messages = (List<String>) session.getAttribute("messages");
        if(messages == null){
            messages = new ArrayList<>();
        }
        messages.add(message);
        session.setAttribute("messages",messages);
        return ResponseEntity.ok().build();
    }

    //Gets the session messages attribute
    @GetMapping("/session")
    public ResponseEntity<Object> testGetSession(HttpServletRequest request){
        //checking if the session is null
        if(request.getSession(false) == null){
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).eTag("SessionMIA").build();
        }

        //checking if the messages attribute is null
        Object value = request.getSession(false).getAttribute("messages");
        if(value != null){
            return ResponseEntity.ok().body(value);
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();

    }

    //invalidates the session
    @PostMapping("/sessionkill")
    public ResponseEntity killSession(HttpServletRequest request){
        request.getSession(false).invalidate();
        return ResponseEntity.ok().build();
    }
}
