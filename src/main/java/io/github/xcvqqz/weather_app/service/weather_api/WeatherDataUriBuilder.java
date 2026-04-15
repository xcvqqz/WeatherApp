package io.github.xcvqqz.weather_app.service.weather_api;


import io.github.xcvqqz.weather_app.service.weather_api.builder.BaseOpenWeatherMapUriBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Component
public class WeatherDataUriBuilder extends BaseOpenWeatherMapUriBuilder {

    private final String LAT_PARAM = "lat";
    private final String LON_PARAM = "lon";
    private final String WEATHER_DATA_URL = "https://api.openweathermap.org/data/2.5/weather";

    protected URI buildCurrentWeatherUri(double lat, double lon) {
         UriComponentsBuilder builder = fromUriString(WEATHER_DATA_URL)
                .queryParam(LAT_PARAM, lat)
                .queryParam(LON_PARAM, lon)
                .queryParam(APP_ID_PARAM, APP_KEY)
                .queryParam(UNITS_PARAM, METRIC_VALUE_FOR_UNITS_PARAM);

         return addCommonsParam(builder).build().toUri();
    }

}