package com.leiton.ejercicioLogin.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SignUpResponse {

    private UUID id;

    private Instant created;

    private Instant lastLogin;

    private String token;

    private Boolean isActive;
}
