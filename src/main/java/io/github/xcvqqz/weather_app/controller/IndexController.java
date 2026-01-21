package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String index(@ModelAttribute("user") User user) {

//        @RequestParam UUID uuid, Model model
//            model.addAttribute("uuid", uuid);
            return "first/index";
    }



}
