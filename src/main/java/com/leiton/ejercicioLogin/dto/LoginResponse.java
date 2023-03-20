package com.leiton.ejercicioLogin.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    
    private String id;

    private Instant created;

    private Instant lastLogin;

    private String token;

    private Boolean isActive;

    private String name;

    private String email;

    private String password;

    private List<PhoneRequest> phones;
}
