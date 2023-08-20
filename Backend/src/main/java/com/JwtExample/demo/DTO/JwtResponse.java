package com.JwtExample.demo.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class JwtResponse {
    private String token;
}
