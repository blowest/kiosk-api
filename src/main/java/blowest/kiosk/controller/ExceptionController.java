package blowest.kiosk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NoResultException.class})
    public ResponseEntity<Object> BadRequestException(final NoResultException ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }
}
