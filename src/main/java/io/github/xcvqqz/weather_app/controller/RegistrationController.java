package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import io.github.xcvqqz.weather_app.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping()
public class RegistrationController {

    private final SessionService sessionService;
    private UserService userService;

    @GetMapping("/sign-up")
    public String showSignUp(@ModelAttribute("user") UserRegistrationDTO userRegistration) {
        return "sign-up";
    }


    @PostMapping("/sign-up")
    public String processSignUp(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistration,
                                BindingResult result, HttpServletResponse response)  {

        if(!(userRegistration.password().equals(userRegistration.confirmPassword()))) {
            result.rejectValue("confirmPassword", "passwordMismatch", "Passwords don't match");
        }

        if (result.hasErrors()) {
            return "sign-up";
        }


        User user = userService.save(userRegistration);
        log.info("A new user has been created with login: {}", user.getLogin());

        Session session = sessionService.create(user);
        log.info("A new session has been created for user: {}", user.getLogin());

        CookieUtil.setSessionCookie(response, session.getSessionId());
        log.info("A cookie has been created, Cookie: {}", session.getSessionId());

        return "redirect:/home";
    }




}
