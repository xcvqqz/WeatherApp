package io.github.xcvqqz.weather_app.model.domain;

import lombok.Data;

@Data
public class WeatherData {

//    private String city;
    private double lon;
    private double lat;
    private String description;
    private double temp;
    private double feelsLike;
    private int humidity;
    private String country;

}