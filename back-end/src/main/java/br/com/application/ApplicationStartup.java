package br.com.application;

import br.com.application.controller.UserController;
import br.com.application.dto.RegisterDTO;
import br.com.application.enums.UserRole;
import br.com.application.service.AuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class ApplicationStartup implements CommandLineRunner {

    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        RegisterDTO data1 = new RegisterDTO();
        data1.setName("Administrador");
        data1.setEmail("admin");
        data1.setPassword("admin");
        data1.setRole(UserRole.ADMIN);

        RegisterDTO data2 = new RegisterDTO();
        data2.setName("Usuario comum");
        data2.setEmail("user");
        data2.setPassword("user");
        data2.setRole(UserRole.USER);

        authService.register(data1);

    }

}