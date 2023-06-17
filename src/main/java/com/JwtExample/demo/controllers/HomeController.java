package com.JwtExample.demo.controllers;

import com.JwtExample.demo.model.User;
import com.JwtExample.demo.DTO.UserDTO;
import com.JwtExample.demo.repositories.UserRepository;
import com.JwtExample.demo.services.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HomeController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // Get User Data On the basis of token
    @GetMapping("/getUser")
    public UserDTO Home(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        String userName = jwtService.getUsernameFromToken(token);
        User usr = this.userRepository.getUserByName(userName);
        return modelMapper.map(usr, UserDTO.class);

    }
}
