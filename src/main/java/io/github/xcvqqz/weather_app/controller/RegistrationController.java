package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.exception.PasswordMismatchException;
import io.github.xcvqqz.weather_app.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private UserService userService;


    @GetMapping()
    public String showSignUp(UserRegistrationDTO userRegistration, Model model) {
        //переход на форму для создания юзера
        model.addAttribute("user", userRegistration);
        return "first/sign-up";
    }

    

    @PostMapping()
    public String processSignUp(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistration,
                         BindingResult result)  {

        if(!(userRegistration.password().equals(userRegistration.confirmPassword()))) {
            result.rejectValue("confirmPassword", "passwordMismatch", "Passwords don't match");
        }

        if (result.hasErrors()) {
            return "first/sign-up";
        }

        //создание сессии + куки

        userService.save(userRegistration);


        return "redirect:/home";
    }




}
