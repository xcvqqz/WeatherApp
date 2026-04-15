package io.github.xcvqqz.weather_app.service.weather_api;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Component
public class WeatherDataUriBuilder extends BaseOpenWeatherMapUriBuilder {

    @Value("${weather.url}")
    private String weatherDataUrl;

    private final String LAT_PARAM = "lat";
    private final String LON_PARAM = "lon";

    protected URI buildCurrentWeatherUri(double lat, double lon) {
         UriComponentsBuilder builder = fromUriString(weatherDataUrl)
                .queryParam(LAT_PARAM, lat)
                .queryParam(LON_PARAM, lon);
         return addCommonsParam(builder).build().toUri();
    }

}