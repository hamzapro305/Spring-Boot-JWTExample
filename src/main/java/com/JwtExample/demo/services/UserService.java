package com.JwtExample.demo.services;

import com.JwtExample.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;



}
