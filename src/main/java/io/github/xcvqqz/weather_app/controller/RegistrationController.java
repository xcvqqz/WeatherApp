package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import io.github.xcvqqz.weather_app.repository.impl.UserRepositoryImpl;
import io.github.xcvqqz.weather_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String signUp(@ModelAttribute("user") User user) {
        //переход на форму для создания юзера
        return "first/sign-up";
    }




    @PostMapping()
    public String create(@ModelAttribute("user") User user) {

        //создание сессии + куки
        //userRepository.save(user)
        //создание юзера и редирект на index контроллер (на его страницу)

        userService.register(user);


        return "redirect:/index";
    }




}
