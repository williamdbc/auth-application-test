package br.com.application.service;

import br.com.application.dto.AuthDTO;
import br.com.application.dto.LoginDTO;
import br.com.application.dto.RegisterDTO;
import br.com.application.entity.User;
import br.com.application.exception.BusinessException;
import br.com.application.repository.UserRepository;
import br.com.application.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthDTO login(LoginDTO data) {
        User user = userRepository.findByEmail(data.getEmail());

        if(user == null || !passwordEncoder.matches(data.getPassword(), user.getPassword())){
            throw new BusinessException("Email or password incorrect.");
        }

        String token = tokenService.generateToken(user);

        AuthDTO auth = new AuthDTO();
        auth.setEmail(user.getEmail());
        auth.setToken(token);

        return auth;
    }

    public void register(RegisterDTO data) {
        if (userRepository.findByEmail(data.getEmail()) != null) {
            throw new BusinessException("Email already in use");
        }

        if(data.getPassword().isEmpty()){
            throw new BusinessException("Password cannot be empty");
        }

        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
    }

}
