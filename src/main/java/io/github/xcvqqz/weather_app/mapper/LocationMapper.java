package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationsResponseDTO toResponseDTO(Object rawResponse);

}
