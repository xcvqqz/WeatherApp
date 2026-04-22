package io.github.xcvqqz.weather_app.dto.api.request;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ApiWeatherRequestDTO(

        @NotNull(message = "Latitude cannot be null")
        @DecimalMin(value = "-90.0",  message = "Latitude must be >= -90")
        @DecimalMax(value = "90.0",  message = "Latitude must be <= 90")
        BigDecimal lat,

        @NotNull(message = "Longitude cannot be null")
        @DecimalMin(value = "-180.0",  message = "Longitude must be >= -180")
        @DecimalMax(value = "180.0",  message = "Longitude must be <= 180")
        BigDecimal lon
) {}
