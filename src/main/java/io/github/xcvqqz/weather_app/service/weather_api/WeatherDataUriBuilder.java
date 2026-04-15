package io.github.xcvqqz.weather_app.service.weather_api;


import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherDataUriBuilder {

    private final String APP_KEY = "4f9e811e1990b95dd679058a3dfae99a";
    private final String LAT_PARAM = "lat";
    private final String LON_PARAM = "lon";
    private final String APP_ID_PARAM = "appid";
    private final String UNITS_PARAM = "units";
    private final String METRIC_VALUE_FOR_UNITS_PARAM = "metric";
    private final String WEATHER_DATA_URL = "https://api.openweathermap.org/data/2.5/weather";


    public URI buildCurrentWeatherUri(double lat, double lon) {
        return UriComponentsBuilder
                .fromUriString(WEATHER_DATA_URL)
                .queryParam(LAT_PARAM, lat)
                .queryParam(LON_PARAM, lon)
                .queryParam(APP_ID_PARAM, APP_KEY)
                .queryParam(UNITS_PARAM, METRIC_VALUE_FOR_UNITS_PARAM)
                .build()
                .toUri();
    }

}