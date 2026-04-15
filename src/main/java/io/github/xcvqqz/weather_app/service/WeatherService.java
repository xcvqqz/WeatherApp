package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import io.github.xcvqqz.weather_app.exception.BadRequestException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
@PropertySource("classpath:application-dev.properties")
public class WeatherService {

//    @Value("${app.key}")
    private String appKey = "4f9e811e1990b95dd679058a3dfae99a";

    @Value("${base.url}")
    private String baseURL;


    private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";  //здесь получаем точную погоду по lon и lat




    @Autowired
    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    private URI buildUri(String city){
        return UriComponentsBuilder
                .fromUriString(GEO_URL)
                .queryParam("q", city)
                .queryParam("appid", appKey)
                .queryParam("limit", MAX_LIMIT)
                .queryParam("units", "metric")
                .build()
                .toUri();
    }





}