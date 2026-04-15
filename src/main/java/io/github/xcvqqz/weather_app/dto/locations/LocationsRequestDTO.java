package io.github.xcvqqz.weather_app.dto.locations;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocationsRequestDTO(
        @NotBlank
        @Size(min = 3, max = 30, message = "Location name should be min 3 and less 30 symbol")
        String locationName)
{}
