package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.CookieService;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping()
public class RegistrationController extends BasicController {

    private static final String FIELD_CONFIRM_PASSWORD = "confirmPassword";
    private static final String ERROR_PASSWORD_MISMATCH = "passwordMismatch";
    private static final String MESSAGE_PASSWORDS_DONT_MATCH = "Passwords do not match";

    private final SessionService sessionService;
    private final UserService userService;
    private final CookieService cookieService;

    @GetMapping("/sign-up")
    public String showSignUp(@ModelAttribute("user") UserRegistrationDTO userRegistration) {
        return SIGN_UP_VIEW;
    }


    @PostMapping("/sign-up")
    public String processSignUp(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistration,
                                BindingResult result, HttpServletResponse response)  {

        if(!(userRegistration.password().equals(userRegistration.confirmPassword()))) {
            result.rejectValue(FIELD_CONFIRM_PASSWORD, ERROR_PASSWORD_MISMATCH, MESSAGE_PASSWORDS_DONT_MATCH);
        }

        if (result.hasErrors()) {
            return SIGN_UP_VIEW;
        }


        User user = userService.save(userRegistration);
        log.info("A new user has been created with login: {}", user.getLogin());

        Session session = sessionService.create(user);
        log.info("A new session has been created for user: {}", user.getLogin());

        cookieService.setSessionCookie(response, session.getSessionId());
        log.info("A cookie has been created, Cookie: {}", session.getSessionId());

        return REDIRECT_HOME;
    }




}
