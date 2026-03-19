package io.github.xcvqqz.weather_app.dto.auth;


import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(
    HttpStatus status,
    String name
){}
