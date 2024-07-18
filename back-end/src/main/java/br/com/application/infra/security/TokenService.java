package br.com.application.infra.security;

import br.com.application.entity.User;
import br.com.application.exception.BusinessException;
import br.com.application.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new BusinessException("Error while autenticating.");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new BusinessException("Invalid or expired JWT token.");
        }
    }

    public String refreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String email = JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return generateToken(userRepository.findByEmail(email));
        } catch (JWTVerificationException exception) {
            //logger.error("Invalid or expired JWT token", exception);
            throw new BusinessException("Invalid or expired JWT token.");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(4).toInstant(ZoneOffset.of("-03:00"));
    }

}
