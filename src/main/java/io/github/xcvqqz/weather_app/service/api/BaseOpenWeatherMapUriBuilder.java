package io.github.xcvqqz.weather_app.service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class BaseOpenWeatherMapUriBuilder {

    @Value("${app.key}")
    protected String appKey;

    protected static final String APP_ID_PARAM = "appid";
    protected static final String UNITS_PARAM = "units";
    protected static final String METRIC_VALUE_FOR_UNITS_PARAM = "metric";

    protected UriComponentsBuilder addCommonsParam(UriComponentsBuilder builder){
        return builder
                .queryParam(APP_ID_PARAM, appKey)
                .queryParam(UNITS_PARAM, METRIC_VALUE_FOR_UNITS_PARAM);
    }
}