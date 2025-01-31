package br.com.application.dto;

import br.com.application.enums.UserRole;
import lombok.Data;

@Data
public class RegisterDTO {

    private String name;
    private String email;
    private String password;
    private UserRole role;

}
