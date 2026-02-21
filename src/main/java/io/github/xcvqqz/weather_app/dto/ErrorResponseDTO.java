package io.github.xcvqqz.weather_app.dto;


import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(
    HttpStatus status,
    String name
){}
