package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.UserAuthDTO;
import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registrationToEntity(UserRegistrationDTO userRegistrationDTO);

    User AuthtToEntity(UserAuthDTO userAuthDTO);

    UserRegistrationDTO toDTO(User user);


}