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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@AllArgsConstructor
@Controller
@RequestMapping("/sign-up")
public class RegistrationController {

    private final SessionService sessionService;
    private UserService userService;

    @GetMapping()
    public String showSignUp(@ModelAttribute("user") UserRegistrationDTO userRegistration) {
        return "first/sign-up";
    }


    @PostMapping()
    public String processSignUp(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistration,
                                BindingResult result, HttpServletResponse response)  {

//        if(!(userRegistration.password().equals(userRegistration.confirmPassword()))) {
//            result.rejectValue("confirmPassword", "passwordMismatch", "Passwords don't match");
//        }
//
//        if (result.hasErrors()) {
//            return "first/sign-up";
//        }


        User user = userService.save(userRegistration);   //СОЗДАНИЕ НОВОГО ЮЗЕРА

        Session session = sessionService.create(user);   //СОЗДАНИЕ СЕССИИ ДЛЯ ЮЗЕРА

        CookieUtil.setSessionCookie(response, session.getSessionId());   //СОЗДАНИЕ КУКИ И ОТПРАВЛЕНИЕ КУКИ


        return "redirect:/home";
    }




}
