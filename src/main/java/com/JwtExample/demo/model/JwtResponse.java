package com.JwtExample.demo.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class JwtResponse {
    private String token;
}
