package io.github.xcvqqz.weather_app.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class WeatherData {

    private double lon;
    private double lat;
    private String description;
    private double temp;
    private double feelsLike;
    private int humidity;
    private String country;

}
