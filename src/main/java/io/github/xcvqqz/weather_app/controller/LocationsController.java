package io.github.xcvqqz.weather_app.controller;



import io.github.xcvqqz.weather_app.dto.api.request.ApiLocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.CookieService;
import io.github.xcvqqz.weather_app.service.LocationService;
import io.github.xcvqqz.weather_app.service.UserService;
import io.github.xcvqqz.weather_app.service.api.LocationsSearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/locations")
public class LocationsController {

    private final LocationsSearchService locationSearchService;
    private final LocationService locationService;
    private final UserService userService;
    private final CookieService cookieService;


    @GetMapping("/search-results")
    public String search(@Valid @ModelAttribute("location") ApiLocationsRequestDTO locationsRequestDTO,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/home";
        }

        List<ApiLocationsResponseDTO> locations = locationSearchService.findAll(locationsRequestDTO);
        model.addAttribute("locations", locations);

        return "locations/search-results";
    }


    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("location") ApiLocationsResponseDTO locationsResponseDTO,
                      HttpServletRequest request){

        UUID userSessionId = cookieService.getSessionId(request);

        User user = userService.getBySessionId(userSessionId);

        Location location = locationService.create(locationsResponseDTO, user);

        log.info("A location has been created: {}, {}, {}, {},",
                location.getName(), location.getLongitude(), location.getLatitude(), location.getUser());

        return "redirect:/home";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){

        locationService.delete(id);
        log.info("The location id {} has been successfully deleted", id);

        return "redirect:/home";
    }



}