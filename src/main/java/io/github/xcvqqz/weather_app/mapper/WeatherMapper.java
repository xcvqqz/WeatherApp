package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.weather.WeatherResponseDTO;
import io.github.xcvqqz.weather_app.model.WeatherData;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherData toWeatherData(WeatherResponseDTO weatherResponseDTO);

}
