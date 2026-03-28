package io.github.xcvqqz.weather_app.service.weather_api_service;

import io.github.xcvqqz.weather_app.dto.weather.LocationRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.LocationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("classpath:application-dev.properties")
public class WeatherClientImpl{

    private final RestTemplate restTemplate;

    @Value("${app.key}")
    private String appKey;

    @Value("${base.url}")
    private String baseURL;
//    https://api.openweathermap.org/data/2.5/weather


    @Autowired
    public WeatherClientImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public LocationResponseDTO getCurrentWeather(LocationRequestDTO locationRequestDTO) {

//        String url = UriComponentsBuilder
//                .fromUriString(baseURL)
//                .queryParam("q", locationRequestDTO.locationName())
//                .queryParam("appid", appKey)
//                .toUriString();

        String url = "https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=4f9e811e1990b95dd679058a3dfae99a";

        return restTemplate.getForObject(url, LocationResponseDTO.class);

    }

}
