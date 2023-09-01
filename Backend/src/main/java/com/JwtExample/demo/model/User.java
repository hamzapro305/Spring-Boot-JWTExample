package com.JwtExample.demo.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String userId;

    @Indexed(unique = true)
    private String userName;

    private String password;
}
