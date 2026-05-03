package io.github.xcvqqz.weather_app.service.api;


import io.github.xcvqqz.weather_app.dto.CurrentWeatherDTO;
import io.github.xcvqqz.weather_app.dto.api.request.ApiWeatherRequestDTO;
import io.github.xcvqqz.weather_app.dto.api.response.ApiWeatherResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.mapper.WeatherMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CurrentWeatherService {

    private final RestTemplate restTemplate;
    private final WeatherDataUriBuilder weatherDataUriBuilder;
    private final WeatherMapper weatherMapper;


    public List<CurrentWeatherDTO> getLocationsWeather(List<Location> locations) {

        List<CurrentWeatherDTO> result = new ArrayList<>();

        for(Location location : locations) {
            ApiWeatherRequestDTO request = weatherMapper.toWeatherRequest(location);
            ApiWeatherResponseDTO response = getCurrentWeather(request);
            result.add(weatherMapper.toCurrentWeather(response, location));
        }

        return result;
    }


    private ApiWeatherResponseDTO getCurrentWeather(ApiWeatherRequestDTO weatherRequest) {
        URI uri = weatherDataUriBuilder.buildCurrentWeatherUri(weatherRequest);
        ApiWeatherResponseDTO result =  restTemplate.getForObject(uri, ApiWeatherResponseDTO.class);
        return result;
    }
}