package com.JwtExample.demo.services;

import com.JwtExample.demo.model.User;
import com.JwtExample.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return this.userRepository.insert(user);
    }

}
