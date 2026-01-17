package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.entity.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/locations")
public class LocationController {


    @GetMapping("/search-results")
    public String find(@RequestParam String name, Model model) {

        // Location location = dao.get(name) -> получение локации
        model.addAttribute("location", location);

        return "locations/search-results";
    }

}
