package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class HomeController {

    @GetMapping
    public String index(@ModelAttribute("user") User user) {

//        @RequestParam UUID uuid, Model model
//            model.addAttribute("uuid", uuid);
            return "first/index";
    }



}
