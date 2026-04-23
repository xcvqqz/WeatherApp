package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(source = "lon", target = "longitude")
    @Mapping(source = "lat", target = "latitude")
    Location ApiResponseToEntity(ApiLocationsResponseDTO locationsResponseDTO);

}
