package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.UserAuthDTO;
import io.github.xcvqqz.weather_app.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String home(@ModelAttribute("user")UserAuthDTO userAuth, HttpServletRequest request, HttpServletResponse response) {


        request.



        //ТУТ ПОЛУЧАЕм ПОЛЬЗОВАТЕЛЯ ИЗ СЕССИИ


            return "first/home";
    }



}
