package io.github.xcvqqz.weather_app.service.weather_api_service;

import io.github.xcvqqz.weather_app.dto.weather.LocationRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.LocationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application-dev.properties")
public class WeatherClientImpl{

    private static final String WEATHER_MAP_URL_2 = "https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=4f9e811e1990b95dd679058a3dfae99a";

    private static final String WEATHER_MAP_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}}&appid={key}";

    private static final String MOSCOW = "Moscow";

    @Value("${app.key}")
    private String appKey;

    @Value("${base.url}")
    private String baseURL;


    @Autowired
    private RestTemplate restTemplate;


    public LocationResponseDTO findWeather() {

        LocationRequestDTO locationRequestDTO = new LocationRequestDTO("Moscow");

        LocationResponseDTO locationResponseDTO = restTemplate.getForObject(WEATHER_MAP_URL,)


        UriComponentsBuilder.fromHttpUrl
    }



}
