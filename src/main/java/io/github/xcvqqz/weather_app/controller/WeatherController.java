package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.dto.weather.WeatherRequestDTO;
import io.github.xcvqqz.weather_app.model.domain_model.WeatherData;
import io.github.xcvqqz.weather_app.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/locations")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/search-result")
    public String search(@RequestParam String location, Model model) {

        WeatherRequestDTO weatherRequestDTO = new WeatherRequestDTO(location);
        WeatherData weatherData = weatherService.getCurrentWeather(weatherRequestDTO);

        model.addAttribute("weatherLocation", weatherData);

        return "locations/search-results";
    }

    //Таймлиф нужно написать

}
