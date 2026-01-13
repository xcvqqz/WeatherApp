package io.github.xcvqqz.weather_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String testPage(Model model) {
        model.addAttribute("test", "Hello World!"); // Здесь создаем переменную
        return "test-page"; // имя HTML файла без расширения
    }
}
