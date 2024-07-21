package br.com.application.service;

import br.com.application.dto.AuthDTO;
import br.com.application.dto.LoginDTO;
import br.com.application.dto.RegisterDTO;
import br.com.application.dto.UserDTO;
import br.com.application.entity.User;
import br.com.application.enums.UserRole;
import br.com.application.exception.BusinessException;
import br.com.application.infra.security.TokenService;
import br.com.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList());
    }

    private UserDTO toDto(User entity){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(entity.getName());
        userDTO.setEmail(entity.getEmail());
        userDTO.setRole(entity.getRole());
        return userDTO;
    }

}
