package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.CurrentWeatherDTO;
import io.github.xcvqqz.weather_app.dto.api.request.ApiWeatherRequestDTO;
import io.github.xcvqqz.weather_app.dto.api.response.ApiWeatherResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WeatherMapper {


    @Mapping(source = "longitude", target = "lon")
    @Mapping(source = "latitude", target = "lat")
    ApiWeatherRequestDTO toWeatherRequest(Location location);

    @Mapping(source = "apiWeatherResponse.coord.lon", target = "lon")
    @Mapping(source = "apiWeatherResponse.coord.lat", target = "lat")
    @Mapping(source = "apiWeatherResponse.name", target = "city")
    @Mapping(source = "apiWeatherResponse.main.temp", target = "temp")
    @Mapping(source = "apiWeatherResponse.main.feelsLike", target = "feelsLike")
    @Mapping(source = "apiWeatherResponse.main.humidity", target = "humidity")
    @Mapping(source = "apiWeatherResponse.sys.country", target = "country")
    @Mapping(source = "apiWeatherResponse.weather", target = "description", qualifiedByName = "toWeatherDescription")
    @Mapping(source = "location.id", target = "id")
    CurrentWeatherDTO toCurrentWeather(ApiWeatherResponseDTO apiWeatherResponse, Location location);


    @Named("toWeatherDescription")
    default String convertToWeatherDescription(List<ApiWeatherResponseDTO.Weather> weather) {
        return weather != null && !weather.isEmpty() ? weather.get(0).getDescription() : "Not found";
    }

}