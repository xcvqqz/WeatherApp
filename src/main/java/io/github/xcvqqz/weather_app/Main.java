package io.github.xcvqqz.weather_app;

import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import io.github.xcvqqz.weather_app.service.weather_api.GeocodingUriBuilder;
import io.github.xcvqqz.weather_app.service.weather_api.LocationSearchService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        LocationSearchService locationSearchService = new LocationSearchService(
                new RestTemplate(), new GeocodingUriBuilder());

        List<LocationsResponseDTO> locations = locationSearchService.getFoundLocations(new LocationsRequestDTO("Moscow"));

        System.out.println(locations);


    }


}
