package io.github.xcvqqz.weather_app.service.weather_api.builder;


import org.springframework.web.util.UriComponentsBuilder;

public abstract class BaseOpenWeatherMapUriBuilder {

    protected static final String APP_KEY = "4f9e811e1990b95dd679058a3dfae99a";
    protected static final String APP_ID_PARAM = "appid";
    protected static final String UNITS_PARAM = "units";
    protected static final String METRIC_VALUE_FOR_UNITS_PARAM = "metric";

    protected UriComponentsBuilder addCommonsParam(UriComponentsBuilder builder){
        return builder
                .queryParam(APP_ID_PARAM, APP_KEY)
                .queryParam(UNITS_PARAM, METRIC_VALUE_FOR_UNITS_PARAM);
    }
}