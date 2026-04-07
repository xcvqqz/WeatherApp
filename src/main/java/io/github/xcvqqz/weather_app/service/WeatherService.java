package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.dto.weather.WeatherRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.WeatherResponseDTO;
import io.github.xcvqqz.weather_app.exception.BadRequestException;
import io.github.xcvqqz.weather_app.exception.CityNotFoundException;
import io.github.xcvqqz.weather_app.exception.WeatherApiCommunicationException;
import io.github.xcvqqz.weather_app.mapper.WeatherMapper;
import io.github.xcvqqz.weather_app.model.WeatherData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Slf4j
@Service
@PropertySource("classpath:application-dev.properties")
public class WeatherService {

    @Value("${app.key}")
    private String appKey;

    @Value("${base.url}")
    private String baseURL;

    private final RestTemplate restTemplate;
    private final WeatherMapper weatherMapper;

    @Autowired
    public WeatherService(WeatherMapper weatherMapper, RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.weatherMapper = weatherMapper;
    }

    public WeatherData getCurrentWeather(WeatherRequestDTO weatherRequestDTO) {

       validate(weatherRequestDTO);

       String city = weatherRequestDTO.locationName();
       log.info("Fetching weather for city: {}", city);

       WeatherResponseDTO weatherResponseDTO = fetchWeatherFromApi(city);
       WeatherData weatherData = weatherMapper.toWeatherData(weatherResponseDTO);
        log.info("Successfully fetched weather for: {}, temp: {}°",
                city, weatherData.getTemp());

       return weatherData;

    }

    private WeatherResponseDTO fetchWeatherFromApi(String city){

        URI uri = buildUri(city);

        try {
            return restTemplate.getForObject(uri, WeatherResponseDTO.class);
        } catch (ResourceAccessException e){
            throw new WeatherApiCommunicationException(e.getMessage());
        } catch (HttpClientErrorException.NotFound e){
            throw new CityNotFoundException(e.getMessage());
        }
    }


    private URI buildUri(String city){
        return UriComponentsBuilder
                .fromUriString(baseURL)
                .queryParam("q", city)
                .queryParam("appid", appKey)
                .build()
                .toUri();
    }


    private void validate(WeatherRequestDTO weatherRequestDTO) {

        if (weatherRequestDTO == null){
            throw new BadRequestException("Weather Request cannot be null or empty");
        }
        String city = weatherRequestDTO.locationName();

        if((city == null) || city.isEmpty()){
            throw new BadRequestException("City name cannot be null or empty");
        }

        if(city.length() > 100){
            throw new BadRequestException("City name is too long");
        }
    }




}