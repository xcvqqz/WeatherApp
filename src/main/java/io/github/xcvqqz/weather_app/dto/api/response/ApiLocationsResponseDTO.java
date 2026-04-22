package io.github.xcvqqz.weather_app.dto.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiLocationsResponseDTO (

    String name,
    BigDecimal lon,
    BigDecimal lat,
    String country,
    String state)

{}


