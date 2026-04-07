package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.weather.WeatherResponseDTO;
import io.github.xcvqqz.weather_app.model.domain_model.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {


    @Mapping(source = "coord.lon", target = "lon")
    @Mapping(source = "coord.lat", target = "lat")
    @Mapping(source = "weather", target = "description")
    @Mapping(source = "main.temp", target = "temp")
    @Mapping(source = "main.feelsLike", target = "feelsLike")
    @Mapping(source = "main.humidity", target = "humidity")
    @Mapping(source = "sys.country", target = "country")
    WeatherData toWeatherData(WeatherResponseDTO weatherResponseDTO);


    default String convertWeatherList(List<WeatherResponseDTO.Weather> weather) {
        return weather != null && !weather.isEmpty() ? weather.get(0).getDescription() : null;
    }

}
