package io.github.xcvqqz.weather_app.handler;


import io.github.xcvqqz.weather_app.dto.ErrorResponseDTO;
import io.github.xcvqqz.weather_app.exception.DataBaseException;
import io.github.xcvqqz.weather_app.exception.PasswordMismatchException;
import io.github.xcvqqz.weather_app.exception.UserAlreadyExistsException;
import io.github.xcvqqz.weather_app.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler({UserAlreadyExistsException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExists(Model model, Exception ex) {

        String errorType = ex instanceof UserAlreadyExistsException
                ? "User already exist"
                : "Data integrity violation";

        log.warn("{} - {}", errorType, ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.CONFLICT, ex.getMessage()));

        return "error";
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(Model model, UserNotFoundException ex) {

        log.info("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage()));

        return "error";

    }


    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePassswordMismatch(Model model, PasswordMismatchException ex) {

        log.info("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage()));

        return "error";
    }

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataBaseError(Model model, DataBaseException ex) {

        log.error("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));

        return "error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValid(Model model, MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        log.warn("{}", errorMessage, ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.BAD_REQUEST, errorMessage));

        return "error";

    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedException(Model model, Exception ex) {                                       //НУЖНО ДОБАВИТЬ ЛОГИ ДЛЯ СЕБЯ, не показываем ЮЗЕРУ

        log.error("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));

        return "error";
    }

}