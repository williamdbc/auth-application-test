package br.com.application.handler;

import br.com.application.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<StandardError> businessRuleException(final BusinessException ex, final HttpServletRequest req) {

        StandardError error = new StandardError();

        error.setError("Bad request");
        error.setTimestamp(Instant.now());
        error.setStatus(BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setPath(req.getRequestURI());

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

}
