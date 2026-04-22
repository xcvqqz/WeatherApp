package io.github.xcvqqz.weather_app.dto.api.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiWeatherResponseDTO {

    private String name;
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Sys sys;

    @Data
    public static class Coord {
    private BigDecimal lon;
    private BigDecimal lat;
   }


   @Data
   public static class Weather {
   private String description;
   private String main;
   }


    @Data
    public static class Main {
    private BigDecimal temp;

    @JsonProperty("feels_like")
    private BigDecimal feelsLike;
    private int humidity;
    }

    @Data
    public static class Sys {
    private String country;
    }

}