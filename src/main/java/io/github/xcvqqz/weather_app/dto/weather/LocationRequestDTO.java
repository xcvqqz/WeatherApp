package io.github.xcvqqz.weather_app.dto.weather;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record LocationRequestDTO(

        @NotBlank
        @Size(min = 3, max = 30, message = "location name should be min 3 and less 30 symbol")
        String locationName

//        @NotBlank
//        @DecimalMin(-90.00)
//        @DecimalMax(90.00)
//        BigDecimal lat,
//
//        @NotBlank
//        @DecimalMin(-90.00)
//        @DecimalMax(90.00)
//        BigDecimal lon
        )

{}
