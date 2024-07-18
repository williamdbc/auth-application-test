package br.com.application;

import br.com.application.dto.RegisterDTO;
import br.com.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements CommandLineRunner {

    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        RegisterDTO data = new RegisterDTO();
        data.setName("admin");
        data.setEmail("admin@example.com");
        data.setPassword("admin123");

        authService.register(data);
    }
}