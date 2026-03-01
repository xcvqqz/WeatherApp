package io.github.xcvqqz.weather_app.handler;


import io.github.xcvqqz.weather_app.dto.ErrorResponseDTO;
import io.github.xcvqqz.weather_app.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({UserAlreadyExistsException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExistsException(Model model, Exception ex) {

        String errorType = ex instanceof UserAlreadyExistsException
                ? "User already exist"
                : "Data integrity violation";

        log.warn("{} - {}", errorType, ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.CONFLICT, ex.getMessage()));

        return "error";
    }


    @ExceptionHandler({UserNotFoundException.class, SessionNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Model model, UserNotFoundException ex) {

        log.info("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage()));

        return "error";
    }


    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePasswordMismatchException(Model model, PasswordMismatchException ex) {

        log.info("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage()));

        return "error";
    }

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataBaseException(Model model, DataBaseException ex) {

        log.error("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));

        return "error";
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedException(Model model, Exception ex) {

        log.error("{}", ex.getMessage(), ex);

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));

        return "error";
    }

}