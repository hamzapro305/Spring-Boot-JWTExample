package com.JwtExample.demo.services.jwt;

import com.JwtExample.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.JwtExample.demo.model.User user = this.userRepository.getUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found", null);
        }
        return new User(user.getUserName(), new BCryptPasswordEncoder().encode(user.getPassword()), new ArrayList<>());
    }
}
