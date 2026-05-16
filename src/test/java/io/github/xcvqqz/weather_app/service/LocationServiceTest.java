package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.LocationAlreadyExistsException;
import io.github.xcvqqz.weather_app.mapper.LocationMapper;
import io.github.xcvqqz.weather_app.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    private static final String LOCATION_ALREADY_EXIST_TEST_EXCEPTION
            = "Location already exists for this user";

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationService locationService;

    @Test
    void shouldCreateLocationSuccessfully() {

        ApiLocationsResponseDTO apiLocationsResponseDTO = createTestApiLocationResponseDTO();
        User testUser = createTestUser();
        Location mappedLocation = createTestMappedLocation(apiLocationsResponseDTO);
        Location savedLocation = createTestSavedLocation(apiLocationsResponseDTO, testUser);

        when(locationMapper.apiResponseToEntity(apiLocationsResponseDTO))
                .thenReturn(mappedLocation);
        when(locationRepository.save(any(Location.class)))
                .thenReturn(Optional.ofNullable(savedLocation));

        Location result = locationService.create(apiLocationsResponseDTO, testUser);

        assertThat(result.getName())
                .isNotNull()
                .isEqualTo(apiLocationsResponseDTO.name());

        assertThat(result.getId())
                .isNotNull()
                .isPositive()
                .isEqualTo(222L);

        assertThat(result.getUser())
                .isNotNull()
                .isEqualTo(testUser);

        verify(locationMapper).apiResponseToEntity(apiLocationsResponseDTO);
        verify(locationRepository).save(any(Location.class));
    }

    @Test
    void shouldThrowLocationAlreadyExistsExceptionWhenLocationExists() {

        ApiLocationsResponseDTO apiLocationsResponseDTO = createTestApiLocationResponseDTO();
        User testUser = createTestUser();
        Location savedLocation = createTestSavedLocation(apiLocationsResponseDTO, testUser);

        when(locationMapper.apiResponseToEntity(apiLocationsResponseDTO))
                .thenReturn(savedLocation);
        when(locationRepository.save(any(Location.class)))
                .thenThrow(new LocationAlreadyExistsException(LOCATION_ALREADY_EXIST_TEST_EXCEPTION));

        assertThrows(LocationAlreadyExistsException.class,
                () -> locationService.create(apiLocationsResponseDTO, testUser));

        verify(locationMapper).apiResponseToEntity(apiLocationsResponseDTO);
        verify(locationRepository).save(any(Location.class));
    }

    private User createTestUser(){
        return User.builder()
                .id(111L)
                .login("TestName")
                .password("TestPassword")
                .build();
    }

    private ApiLocationsResponseDTO createTestApiLocationResponseDTO(){
        return new ApiLocationsResponseDTO(
                "Moscow",
                BigDecimal.valueOf(37.6174943),
                BigDecimal.valueOf(55.7504461),
                "RU",
                "Moscow"
        );
    }

    private Location createTestSavedLocation(ApiLocationsResponseDTO apiLocationsResponseDTO, User testUser){
        return   Location.builder()
                .id(222L)
                .name(apiLocationsResponseDTO.name())
                .longitude(apiLocationsResponseDTO.lon())
                .latitude(apiLocationsResponseDTO.lat())
                .user(testUser)
                .build();
    }

    private Location createTestMappedLocation(ApiLocationsResponseDTO apiLocationsResponseDTO){
        return   Location.builder()
                .name(apiLocationsResponseDTO.name())
                .longitude(apiLocationsResponseDTO.lon())
                .latitude(apiLocationsResponseDTO.lat())
                .build();
    }

}