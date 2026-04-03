package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.dto.auth.UserAuthDTO;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registrationToEntity(UserRegistrationDTO userRegistrationDTO);

    User authToEntity(UserAuthDTO userAuthDTO);

    UserRegistrationDTO toDTO(User user);

}