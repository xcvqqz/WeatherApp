package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.DataBaseException;
import io.github.xcvqqz.weather_app.exception.LocationAlreadyExistsException;
import io.github.xcvqqz.weather_app.mapper.LocationMapper;
import io.github.xcvqqz.weather_app.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.xcvqqz.weather_app.service.SessionService.DATABASE_ERROR_MESSAGE;


@Slf4j
@Service
@AllArgsConstructor
public class LocationService {

    private static final String LOCATION_ALREADY_EXIST
            = "The location %s has already been created. Please return to the home page and check your profile.";

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Transactional
    public Location create(ApiLocationsResponseDTO locationsResponseDTO, User user){

        try {
            Location entity = locationMapper.ApiResponseToEntity(locationsResponseDTO);
            entity.setUser(user);

            return locationRepository.save(entity).orElseThrow(
                    () -> new DataBaseException(DATABASE_ERROR_MESSAGE));
        } catch (DataAccessException e){
            throw new DataBaseException(String.format(DATABASE_ERROR_MESSAGE, e.getMessage()));
        }
        catch (ConstraintViolationException e){
            throw new LocationAlreadyExistsException(String.format(LOCATION_ALREADY_EXIST, locationsResponseDTO.name()));
        }
    }


    @Transactional(readOnly = true)
    public List<Location> getLocationsByUser(User user) {
        return locationRepository.findAllByUser(user);
    }

}