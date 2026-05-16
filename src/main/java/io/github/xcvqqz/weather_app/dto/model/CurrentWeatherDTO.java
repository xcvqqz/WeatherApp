package io.github.xcvqqz.weather_app.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CurrentWeatherDTO(

    String icon,
    Long id,
    String city,
    BigDecimal lon,
    BigDecimal lat,
    BigDecimal temp,
    @JsonProperty("feels_like")
    BigDecimal feelsLike,
    int humidity,
    String country,
    String description)
{}