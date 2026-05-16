package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.model.CurrentWeatherDTO;
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

    @Mapping(source = "resp.coord.lon", target = "lon")
    @Mapping(source = "resp.coord.lat", target = "lat")
    @Mapping(source = "resp.name", target = "city")
    @Mapping(source = "resp.main.temp", target = "temp")
    @Mapping(source = "resp.main.feelsLike", target = "feelsLike")
    @Mapping(source = "resp.main.humidity", target = "humidity")
    @Mapping(source = "resp.sys.country", target = "country")
    @Mapping(source = "resp.weather", target = "description", qualifiedByName = "toWeatherDescription")
    @Mapping(source = "resp.weather", target = "icon", qualifiedByName = "toWeatherIcon")
    @Mapping(source = "location.id", target = "id")
    CurrentWeatherDTO toCurrentWeather(ApiWeatherResponseDTO resp, Location location);

    @Named("toWeatherDescription")
    default String toWeatherDescription(List<ApiWeatherResponseDTO.Weather> weather) {
        return weather != null && !weather.isEmpty() ? weather.get(0).getDescription() : "Not found";
    }

    @Named("toWeatherIcon")
    default String toWeatherIcon(List<ApiWeatherResponseDTO.Weather> weather) {
        return weather != null && !weather.isEmpty() ? weather.get(0).getIcon() : "Not found";
    }
}