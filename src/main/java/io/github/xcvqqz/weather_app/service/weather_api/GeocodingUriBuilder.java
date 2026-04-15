package io.github.xcvqqz.weather_app.service.weather_api;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class GeocodingUriBuilder extends BaseOpenWeatherMapUriBuilder {

    @Value("${geo.url}")
    private String locationSearchUrl = "https://api.openweathermap.org/geo/1.0/direct";

//    private String locationSearchUrl = "https://api.openweathermap.org/geo/1.0/direct";

    private final String LOCATION_PARAM = "q";
    private final String LOCATION_LIMIT_PARAM = "limit";
    private final Integer MAX_LIMIT = 5;


    protected URI buildLocationSearchUri(String locationName) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(locationSearchUrl)
                .queryParam(LOCATION_PARAM, locationName)
                .queryParam(LOCATION_LIMIT_PARAM, MAX_LIMIT);

        return addCommonsParam(builder).build().toUri();
    }


}
