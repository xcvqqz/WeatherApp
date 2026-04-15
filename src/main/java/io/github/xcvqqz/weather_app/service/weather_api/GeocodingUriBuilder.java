package io.github.xcvqqz.weather_app.service.weather_api;


import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class GeocodingUriBuilder {

    private final String APP_KEY = "4f9e811e1990b95dd679058a3dfae99a";
    private final String LOCATION_PARAM = "q";
    private final String APP_ID_PARAM = "appid";
    private final String LOCATION_LIMIT_PARAM = "limit";
    private final String UNITS_PARAM = "units";
    private final String METRIC_VALUE_FOR_UNITS_PARAM = "metric";
    private final Integer MAX_LIMIT = 5;
    private final String LOCATION_SEARCH_URL = "https://api.openweathermap.org/geo/1.0/direct";


    public URI buildLocationSearchUri(String locationName) {
        return UriComponentsBuilder
                .fromUriString(LOCATION_SEARCH_URL)
                .queryParam(LOCATION_PARAM, locationName)
                .queryParam(APP_ID_PARAM, APP_KEY)
                .queryParam(LOCATION_LIMIT_PARAM, MAX_LIMIT)
                .queryParam(UNITS_PARAM, METRIC_VALUE_FOR_UNITS_PARAM)
                .build()
                .toUri();
    }


}
