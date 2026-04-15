package io.github.xcvqqz.weather_app;

import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import io.github.xcvqqz.weather_app.service.WeatherService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        WeatherService weatherService = new WeatherService(new RestTemplate());

        List<LocationsResponseDTO> locations = weatherService.getGeoLocations(new LocationsRequestDTO("Moscow"));

        System.out.println(locations);


    }


}
