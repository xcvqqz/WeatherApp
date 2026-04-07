package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/search-result")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public String search(@RequestParam String name, Model model) {

        // Location location = dao.get(name) -> получение локации
        model.addAttribute("location", name);

        return "locations/search-results";
    }
}
