package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.dto.weather.WeatherRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.WeatherResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("classpath:application-dev.properties")
public class WeatherService {

    @Value("${app.key}")
    private String appKey;

    @Value("${base.url}")
    private String baseURL;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public WeatherResponseDTO getCurrentWeather(WeatherRequestDTO locationRequestDTO) {

        String url = UriComponentsBuilder
                .fromUriString(baseURL)
                .queryParam("q", locationRequestDTO.locationName())
                .queryParam("appid", appKey)
                .toUriString();

        return restTemplate.getForObject(url, WeatherResponseDTO.class);
    }

}