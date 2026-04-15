package io.github.xcvqqz.weather_app.controller;



import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import io.github.xcvqqz.weather_app.service.weather_api.LocationSearchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/locations")
public class LocationController {

    private final LocationSearchService locationSearchService;

    @GetMapping("/search-results")
    public String search(@Valid @ModelAttribute("location") LocationsRequestDTO locationsRequestDTO, Model model) {

        List<LocationsResponseDTO> locations = locationSearchService.getFoundLocations(locationsRequestDTO);
        model.addAttribute("locations", locations);

        return "locations/search-results";
    }

}
