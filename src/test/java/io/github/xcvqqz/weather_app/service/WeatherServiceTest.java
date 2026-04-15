package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;

import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import io.github.xcvqqz.weather_app.exception.BadRequestException;
import io.github.xcvqqz.weather_app.exception.LocationsNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class WeatherServiceTest {

    private static final String TEST_PARAM = "test city";
    private static final String CITY_PARAM = "Moscow";
    private static final String RUSSIA_COUNTRY_CODE = "RU";
    private static final String EMPTY_INVALID_PARAM = "";

//    @Autowired
//    private WeatherService weatherService;
//
////    @Autowired
////    private LocationMapper locationMapper;
//
//    @Test
//    void shouldReturnLocationsWhenCityExists() {
//
//        LocationsRequestDTO locationRequest = new LocationsRequestDTO(CITY_PARAM);
//
//        List<LocationsResponseDTO> locations = weatherService.getGeoLocations(locationRequest);
//
//        assertThat(locations).isNotEmpty();
//        assertThat(locations.get(0).getCountry()).isEqualTo(RUSSIA_COUNTRY_CODE);
//    }
//
//    @Test
//    void shouldThrowLocationsNotFoundExceptionWhenLocationsDoesNotExist(){
//
//        LocationsRequestDTO locationRequest = new LocationsRequestDTO(TEST_PARAM);
//
//        assertThatThrownBy(() -> weatherService.getGeoLocations(locationRequest))
//                .isInstanceOf(LocationsNotFoundException.class);
//    }
//
//    @Test
//    void shouldThrowBadRequestExceptionWhenLocationIsInvalid(){
//
//        LocationsRequestDTO locationRequest = new LocationsRequestDTO(EMPTY_INVALID_PARAM);
//
//        assertThatThrownBy(() -> weatherService.getGeoLocations(locationRequest))
//                .isInstanceOf(BadRequestException.class)
//                .hasNoCause();
//    }


}
