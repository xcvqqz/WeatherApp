package io.github.xcvqqz.weather_app.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDTO {

    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Sys sys;

    @Data
    @NoArgsConstructor
    public static class Coord {
        private double lon;
        private double lat;
    }

    @Data
    @NoArgsConstructor
    public static class Weather {
        private String description;
    }

    @Data
    @NoArgsConstructor
    public static class Main {
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;

        private int humidity;
    }

    @Data
    @NoArgsConstructor
    public static class Sys {
        private String country;
    }

}