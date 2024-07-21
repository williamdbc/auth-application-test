package br.com.application.dto;

import br.com.application.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String email;
    private UserRole role;

}
