package br.com.application.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private Long id;
    private String email;
    private String password;

}
