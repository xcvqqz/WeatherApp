package io.github.xcvqqz.weather_app.model.domain_model;

import lombok.Data;

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