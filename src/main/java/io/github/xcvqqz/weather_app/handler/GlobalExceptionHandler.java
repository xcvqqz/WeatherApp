package io.github.xcvqqz.weather_app.handler;


import io.github.xcvqqz.weather_app.dto.auth.ErrorResponseDTO;
import io.github.xcvqqz.weather_app.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW = "error";
    private static final String HOME_REDIRECT = "redirect:/home";
    private static final String SIGN_UP_REDIRECT = "redirect:/sign-up";
    private static final String SIGN_IN_REDIRECT = "redirect:/sign-in";

    private static final String GLOBAL_ERROR_ATTR = "global_error";

    private static final Map<Class<?>, String> REDIRECT_ROUTES = Map.of(
            LocationAlreadyExistsException.class,   HOME_REDIRECT,
            LocationsNotFoundException.class,       HOME_REDIRECT,
            UserAlreadyExistsException.class,       SIGN_UP_REDIRECT,
            UserNotFoundException.class,            SIGN_IN_REDIRECT,
            SessionNotFoundException.class,         SIGN_IN_REDIRECT,
            PasswordMismatchException.class,        SIGN_IN_REDIRECT,
            BadRequestException.class,              HOME_REDIRECT
    );

    @ExceptionHandler({
            UserAlreadyExistsException.class,
            LocationAlreadyExistsException.class
    })
    public String handleConflict(
            Exception ex,
            RedirectAttributes ra,
            HttpServletRequest request) {

        log.warn("conflict: URI={}, type={}, msg={}",
                request.getRequestURI(), ex.getClass().getSimpleName(), ex.getMessage());

        addGlobalError(ra, ex);
        return doRedirect(ex,
               ex instanceof UserAlreadyExistsException ? SIGN_UP_REDIRECT : HOME_REDIRECT);
    }


    @ExceptionHandler({
            UserNotFoundException.class,
            SessionNotFoundException.class,
            LocationsNotFoundException.class
    })
    public String handleNotFound(
            Exception ex,
            RedirectAttributes ra,
            HttpServletRequest request) {

        log.warn("Entity not found: URI={}, type={}, msg={}",
                request.getRequestURI(), ex.getClass().getSimpleName(), ex.getMessage());

        addGlobalError(ra, ex);
        return doRedirect(ex, SIGN_IN_REDIRECT);
    }


    @ExceptionHandler({
            PasswordMismatchException.class,
            BadRequestException.class
    })
    public String handleClientInputError(
            Exception ex,
            RedirectAttributes ra,
            HttpServletRequest request) {

        log.warn("Client input error: URI={}, type={}, msg={}",
                request.getRequestURI(), ex.getClass().getSimpleName(), ex.getMessage());

        addGlobalError(ra, ex);
        return doRedirect(ex, HOME_REDIRECT);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request,
            Model model) {

        log.warn("Method not allowed: {} {}", ex.getMethod(), request.getRequestURI());

        model.addAttribute("error", new ErrorResponseDTO(
                HttpStatus.METHOD_NOT_ALLOWED,
                "HTTP метод '" + ex.getMethod() + "' не поддерживается"
        ));
        return ERROR_VIEW;
    }

    @ExceptionHandler(DataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataBaseException(
            DataBaseException ex,
            HttpServletRequest request,
            Model model) {

        log.error("Database error: URI={}, msg={}", request.getRequestURI(), ex.getMessage(), ex);

        model.addAttribute("error",
                new ErrorResponseDTO(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage()));
        return ERROR_VIEW;
    }

    @ExceptionHandler(WeatherApiCommunicationException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleWeatherApiException(
            WeatherApiCommunicationException ex,
            HttpServletRequest request,
            Model model) {

        log.warn("Weather API unavailable: URI={}, msg={}", request.getRequestURI(), ex.getMessage(), ex);

        model.addAttribute("error",
                new ErrorResponseDTO(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage()));
        return ERROR_VIEW;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpected(
            Exception ex,
            HttpServletRequest request,
            Model model) {

        log.error("Unexpected error: URI={}, type={}, msg={}",
                request.getRequestURI(), ex.getClass().getName(), ex.getMessage(), ex);

        model.addAttribute("error",
                new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Произошла непредвиденная ошибка"));
        return ERROR_VIEW;
    }

    private void addGlobalError(RedirectAttributes ra, Exception ex) {
        ra.addFlashAttribute(GLOBAL_ERROR_ATTR, ex.getMessage());
    }

    private String doRedirect(Exception ex, String fallback) {
        return REDIRECT_ROUTES.getOrDefault(ex.getClass(), fallback);
    }
}