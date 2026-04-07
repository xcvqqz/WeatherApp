package io.github.xcvqqz.weather_app.handler;


import io.github.xcvqqz.weather_app.dto.auth.ErrorResponseDTO;
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

    private static final String LOG_LEVER_WARN = "warn";
    private static final String LOG_LEVER_ERROR = "error";
    private static final String LOG_LEVER_INFO = "info";

    @ExceptionHandler({UserAlreadyExistsException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExistsException(Model model, Exception ex) {
        return processError(model, ex, HttpStatus.CONFLICT, LOG_LEVER_WARN);
    }


    @ExceptionHandler({
            UserNotFoundException.class,
            SessionNotFoundException.class,
            CityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Model model, UserNotFoundException ex) {
        return processError(model, ex, HttpStatus.NOT_FOUND, LOG_LEVER_INFO);
    }


    @ExceptionHandler({PasswordMismatchException.class, BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePasswordMismatchException(Model model, PasswordMismatchException ex) {
        return processError(model, ex, HttpStatus.BAD_REQUEST, LOG_LEVER_INFO);
    }

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataBaseException(Model model, DataBaseException ex) {
        return processError(model, ex, HttpStatus.INTERNAL_SERVER_ERROR, LOG_LEVER_ERROR);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleWeatherApiCommunicationException(Model model, WeatherApiCommunicationException ex){
        return processError(model, ex, HttpStatus.SERVICE_UNAVAILABLE, LOG_LEVER_ERROR);
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedException(Model model, Exception ex) {
       return processError(model, ex, HttpStatus.INTERNAL_SERVER_ERROR, LOG_LEVER_ERROR);
    }


    private String processError(Model model, Exception ex, HttpStatus status, String level){

        switch (level){
            case "error" -> log.error(ex.getMessage(), ex);
            case "warn" -> log.warn(ex.getMessage(), ex);
            case "info" -> log.info(ex.getMessage(), ex);
        }

        model.addAttribute("error", new ErrorResponseDTO(status, ex.getMessage()));
        return "error";
    }

}