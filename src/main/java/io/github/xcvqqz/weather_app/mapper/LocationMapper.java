package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location ToEntity(ApiLocationsResponseDTO locationsResponseDTO);

}
