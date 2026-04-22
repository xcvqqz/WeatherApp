package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.CurrentWeatherDTO;
import io.github.xcvqqz.weather_app.dto.api.response.ApiWeatherResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(source = "coord.lon", target = "lon")
    @Mapping(source = "coord.lat", target = "lat")
    @Mapping(source = "name", target = "city")
    @Mapping(source = "main.temp", target = "temp")
    @Mapping(source = "main.feelsLike", target = "feelsLike")
    @Mapping(source = "main.humidity", target = "humidity")
    @Mapping(source = "sys.country", target = "country")
    @Mapping(source = "weather", target = "description", qualifiedByName = "toWeatherDescription")
    CurrentWeatherDTO toCurrentWeather(ApiWeatherResponseDTO apiWeatherResponseDTO);


    @Named("toWeatherDescription")
    default String convertToWeatherDescription(List<ApiWeatherResponseDTO.Weather> weather) {
        return weather != null && !weather.isEmpty() ? weather.get(0).getDescription() : "Not found";
    }

}