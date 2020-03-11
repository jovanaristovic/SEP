package com.ftn.backend.controller;

import com.ftn.backend.dto.RegisterUserDto;
import com.ftn.backend.model.User;
import com.ftn.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        User user = userService.register(registerUserDto);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

//        userService.sendMailForActivation(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
