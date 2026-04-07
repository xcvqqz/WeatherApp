package io.github.xcvqqz.weather_app.dto.weather;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WeatherRequestDTO(
        @NotBlank
        @Size(min = 3, max = 30, message = "city name should be min 3 and less 30 symbol")
        String locationName)
{}
