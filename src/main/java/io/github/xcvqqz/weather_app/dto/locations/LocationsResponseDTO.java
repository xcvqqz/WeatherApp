package io.github.xcvqqz.weather_app.dto.locations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationsResponseDTO (

    String name,
    double lon,
    double lat,
    String country,
    String state)
{}



//    @Data
//    @NoArgsConstructor
//    public static class Coord {
//        private double lon;
//        private double lat;
//    }
//
//    @Data
//    @NoArgsConstructor
//    public static class Sys {
//        private String country;
//    }
//
//
//
//    private List<Weather> weather;
//    private Main main;
//
//
//
//
//    @Data
//    @NoArgsConstructor
//    public static class Weather {
//        private String description;
//    }
//
//    @Data
//    @NoArgsConstructor
//    public static class Main {
//        private double temp;
//
//        @JsonProperty("feels_like")
//        private double feelsLike;
//
//        private int humidity;
