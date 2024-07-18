package br.com.application.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

}
