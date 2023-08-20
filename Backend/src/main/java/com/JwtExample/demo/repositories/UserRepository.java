package com.JwtExample.demo.repositories;

import com.JwtExample.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Hamza", "123"));
        users.add(new User("Maaz", "321"));
        users.add(new User("ali", "456"));
    }

    public User getUserByName(String userName) {
        Optional<User> usr = this
                .users
                .stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst();
        return usr.orElse(null);
    }
}