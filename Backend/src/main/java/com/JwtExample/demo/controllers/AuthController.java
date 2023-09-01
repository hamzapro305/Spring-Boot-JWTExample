package com.JwtExample.demo.controllers;

import com.JwtExample.demo.DTO.JwtRequest;
import com.JwtExample.demo.DTO.UserDTO;
import com.JwtExample.demo.services.UserService;
import com.JwtExample.demo.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.JwtExample.demo.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtService helper;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());

        this.doAuthenticate(userDetails.getUsername(), request.getPassword());

        String token = this.helper.generateToken(userDetails);

        var response = new HashMap<String, Object>();
        response.put("token", token);
        response.put("userName", userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody JwtRequest jwtRequest) {
        ModelMapper m = new ModelMapper();
        User user = m.map(jwtRequest, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        user = userService.save(user);

        UserDTO userDTO = m.map(user, UserDTO.class);

        Map<String, Object> resp = new HashMap<>();
        resp.put("user", userDTO);

        return ResponseEntity.ok(resp);
    }

    private void doAuthenticate(String userName, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userName, password
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

}