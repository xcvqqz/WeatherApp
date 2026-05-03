package io.github.xcvqqz.weather_app.service.api;


import io.github.xcvqqz.weather_app.dto.api.request.ApiWeatherRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Component
public class WeatherDataUriBuilder extends BaseOpenWeatherMapUriBuilder {

    @Value("${weather.url}")
    private String weatherDataUrl;

    private final String LAT_PARAM = "lat";
    private final String LON_PARAM = "lon";

    protected URI buildCurrentWeatherUri(ApiWeatherRequestDTO apiWeatherRequest) {
         UriComponentsBuilder builder = fromUriString(weatherDataUrl)
                .queryParam(LON_PARAM, apiWeatherRequest.lon())
                .queryParam(LAT_PARAM, apiWeatherRequest.lat());

         return addCommonsParam(builder).build().toUri();
    }
}