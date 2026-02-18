package io.github.xcvqqz.weather_app.handler;


import io.github.xcvqqz.weather_app.exception.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleAlreadyExistsException{

    }
}
