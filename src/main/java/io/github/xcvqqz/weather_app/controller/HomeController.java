package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.UserAuthDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import io.github.xcvqqz.weather_app.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/home")
public class HomeController {

    private final SessionService sessionService;
    private final UserService userService;


    @GetMapping
    public String home(Model model, HttpServletRequest request) {

        UUID userSessionId = (UUID) request.getAttribute("userSessionId");

        User user = sessionService.getUserBySessionId(userSessionId);   //ПОЛУЧАЕМ ПОЛЬЗОВАТЕЛЯ ПО СЕССИИ

        model.addAttribute("user", user);   //ОТОБРАЖАЕМ ДОМАШНЮЮ СТРАНИЦУ

        return "first/home";
    }






}
