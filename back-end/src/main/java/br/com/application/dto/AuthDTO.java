package br.com.application.dto;

import lombok.Data;

@Data
public class AuthDTO {

    private Long id;
    private String email;
    private String token;
}
