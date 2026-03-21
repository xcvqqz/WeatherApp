package io.github.xcvqqz.weather_app.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponseDTO {

    @NotBlank
    String name;

    @NotBlank
    String country;

    @NotBlank
    BigDecimal lon;

    @NotBlank
    BigDecimal lat;

    @NotBlank
    String description;

    @NotBlank
    BigDecimal humidity;

    @NotBlank
    BigDecimal temp;

    @JsonProperty("feels_like")
    BigDecimal feelsLike;


}
