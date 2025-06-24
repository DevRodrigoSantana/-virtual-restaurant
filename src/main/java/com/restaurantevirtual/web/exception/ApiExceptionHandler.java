package com.restaurantevirtual.web.exception;

import com.restaurantevirtual.exception.EmailUniqueViolationException;
import com.restaurantevirtual.exception.EntityNotFoundException;
import com.restaurantevirtual.exception.PasswordInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //ouvinte de exceções
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){
        log.error("API Error  --  ",ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request,HttpStatus.UNPROCESSABLE_ENTITY,"Campos Inválidos",result));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex,
                                                                        HttpServletRequest request){
        log.error("API Error  --  ",ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request,HttpStatus.NOT_FOUND,ex.getMessage()));
    }
    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> passwordInvalidException(PasswordInvalidException ex,
                                                                 HttpServletRequest request){
        log.error("API Error  --  ",ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request,HttpStatus.BAD_REQUEST,ex.getMessage()));
    }

    @ExceptionHandler(EmailUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> emailUniqueViolationException(EmailUniqueViolationException ex,
                                                                         HttpServletRequest request){
        log.error("API Error  --  ",ex);
        return  ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request,HttpStatus.CONFLICT,ex.getMessage()));

    }



}
