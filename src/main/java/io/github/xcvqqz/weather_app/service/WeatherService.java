package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.dto.weather.WeatherRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.WeatherResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@PropertySource("classpath:application-dev.properties")
public class WeatherService {

    private final RestTemplate restTemplate;

//    @Value("${app.key}")
//    private String appKey;
//
//    @Value("${base.url}")
//    private String baseURL;

    private String appKey;

    private String baseURL;


//    https://api.openweathermap.org/data/2.5/weather


    @Autowired
    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public WeatherResponseDTO getCurrentWeather(WeatherRequestDTO locationRequestDTO) {

//        String url = UriComponentsBuilder
//                .fromUriString(baseURL)
//                .queryParam("q", locationRequestDTO.locationName())
//                .queryParam("appid", appKey)
//                .toUriString();

        String url = "https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=4f9e811e1990b95dd679058a3dfae99a";

        WeatherResponseDTO result = restTemplate.getForObject(url, WeatherResponseDTO.class);

        System.out.println("Успешно!!!");

        return result;

    }

}
