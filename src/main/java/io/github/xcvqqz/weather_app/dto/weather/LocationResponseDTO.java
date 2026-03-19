package io.github.xcvqqz.weather_app.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponseDTO {

    String name;

    String country;

    @NotBlank
    BigDecimal lon;

    @NotBlank
    BigDecimal lat;

    String description;

    BigDecimal humidity;

    BigDecimal temp;

    BigDecimal feels_like;


}
