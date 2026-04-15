package io.github.xcvqqz.weather_app.service.weather_api;


import io.github.xcvqqz.weather_app.service.weather_api.builder.BaseOpenWeatherMapUriBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@NoArgsConstructor
@Component
public class GeocodingUriBuilder extends BaseOpenWeatherMapUriBuilder {

    private final String LOCATION_PARAM = "q";
    private final String LOCATION_LIMIT_PARAM = "limit";
    private final Integer MAX_LIMIT = 5;
    private final String LOCATION_SEARCH_URL = "https://api.openweathermap.org/geo/1.0/direct";

    protected URI buildLocationSearchUri(String locationName) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(LOCATION_SEARCH_URL)
                .queryParam(LOCATION_PARAM, locationName)
                .queryParam(LOCATION_LIMIT_PARAM, MAX_LIMIT);

        return addCommonsParam(builder).build().toUri();
    }


}
