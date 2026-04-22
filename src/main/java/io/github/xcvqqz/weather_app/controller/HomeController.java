package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.dto.CurrentWeatherDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.LocationService;
import io.github.xcvqqz.weather_app.service.UserService;
import io.github.xcvqqz.weather_app.service.api.CurrentWeatherService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping()
public class HomeController {

    private final UserService userService;

    private final LocationService locationService;

    private final CurrentWeatherService currentWeatherService;


    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {

        UUID userSessionId = (UUID) request.getAttribute("userSessionId");

        User user = userService.getUserBySessionId(userSessionId);

        log.info("The user received is - {}", user.getLogin());

        List<Location> locations = locationService.getLocationsByUser(user);

        List<CurrentWeatherDTO> weathers = currentWeatherService.getLocationsWeather(locations);


        log.info("Retrieved weather for {} locations", weathers.size());


        model.addAttribute("user", user);
        model.addAttribute("weathers", weathers);

        return "home";
    }


}