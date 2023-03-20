package com.leiton.ejercicioLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    
    private Long number;

    private Integer cityCode;

    private String countryCode;
}
