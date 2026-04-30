package io.github.xcvqqz.weather_app.handler;


import io.github.xcvqqz.weather_app.dto.auth.ErrorResponseDTO;
import io.github.xcvqqz.weather_app.exception.*;
import jakarta.servlet.http.HttpServletRequest;
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

    @ExceptionHandler({UserAlreadyExistsException.class,
            LocationAlreadyExistsException.class,
            DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAlreadyExistsException(Model model, Exception ex) {


        return processError(model, ex, HttpStatus.CONFLICT, LOG_LEVER_WARN);

//        model.getAttribute()

//                log.warn(" ");



    }


    @ExceptionHandler({
            UserNotFoundException.class,
            SessionNotFoundException.class,
            LocationsNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Model model, Exception ex) {
        return processError(model, ex, HttpStatus.NOT_FOUND, LOG_LEVER_INFO);
    }



    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePasswordMismatchException(Model model, Exception ex) {

        log.warn("Произошла ошибка ввода пароля: {}, {}", ex, ex.getMessage());
        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage()));
        return "error";
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestExceptionException(HttpServletRequest request, Model model, Exception ex) {

        log.warn("Произошла ошибка ввода данных: URI - {}. Ошибка: {}, {}", request.getRequestURI(), ex, ex.getMessage());

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage()));

        return "/home";




    }




    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataBaseException(HttpServletRequest request, Model model, DataBaseException ex) {

        log.error("Произошла ошибка при работе с базой данных: URI - {}, Ошибка: {}, {}", request.getRequestURI(), ex, ex.getMessage());

        model.addAttribute("error", new ErrorResponseDTO(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage()));


        return "error";

    }



    @ExceptionHandler()
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleWeatherApiCommunicationException(HttpServletRequest request, Model model, WeatherApiCommunicationException ex){

        log.warn("Не был получен ответ от внешнего источника: URI - {},  Ошибка - {}", request.getRequestURI(), ex.getMessage());

        return "error";
    }




    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedException(HttpServletRequest request, Model model, Exception ex) {

        log.error("Произошла непредвиденная ошибка: {}, {}, {}", request.getRequestURI(), ex, ex.getMessage());

       model.addAttribute("error", new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));

       return "error";
    }





    private String processError(Model model, Exception ex, HttpStatus status, String level){

        switch (level){
            case LOG_LEVER_ERROR -> log.error(ex.getMessage(), ex);
            case LOG_LEVER_WARN  -> log.warn(ex.getMessage(), ex);
            case LOG_LEVER_INFO -> log.info(ex.getMessage(), ex);
        }

        model.addAttribute("error", new ErrorResponseDTO(status, ex.getMessage()));
        return "error";
    }

}