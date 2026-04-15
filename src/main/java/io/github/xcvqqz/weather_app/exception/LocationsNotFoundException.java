package io.github.xcvqqz.weather_app.exception;

public class LocationsNotFoundException extends RuntimeException {
    public LocationsNotFoundException(String message) {
        super(message);
    }
}
