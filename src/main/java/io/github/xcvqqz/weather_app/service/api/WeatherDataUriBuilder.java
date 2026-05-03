package io.github.xcvqqz.weather_app.service.api;


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

    protected URI buildCurrentWeatherUri(BigDecimal lon, BigDecimal lat) {
         UriComponentsBuilder builder = fromUriString(weatherDataUrl)
                .queryParam(LON_PARAM, lon)
                .queryParam(LAT_PARAM, lat);

         return addCommonsParam(builder).build().toUri();
    }
}