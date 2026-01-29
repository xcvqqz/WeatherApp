package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.exception.PasswordMismatchException;
import io.github.xcvqqz.weather_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-up")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String show(UserRegistrationDTO userRegistration, Model model) {
        //переход на форму для создания юзера
        model.addAttribute("user", userRegistration);
        return "first/sign-up";
    }

    

    @PostMapping()
    public String create(@Valid @ModelAttribute("user") UserRegistrationDTO userRegistration,
                         BindingResult result)  {

        if (result.hasErrors()) {
            return "first/sign-up";
        }

        //создание сессии + куки

        String password = userRegistration.password();
        String confirmPassword = userRegistration.confirmPassword();
//        if(!password.equals(confirmPassword)) {
//            model.addAttribute("message", "Passwords do not match");
//            throw new PasswordMismatchException();
//        }

        userService.save(userRegistration);


        return "redirect:/home";
    }




}
