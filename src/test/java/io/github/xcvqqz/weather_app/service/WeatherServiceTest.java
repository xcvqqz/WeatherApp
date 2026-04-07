package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.controller.RegistrationController;
import io.github.xcvqqz.weather_app.dto.weather.WeatherRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.WeatherResponseDTO;

import io.github.xcvqqz.weather_app.exception.BadRequestException;
import io.github.xcvqqz.weather_app.exception.CityNotFoundException;
import io.github.xcvqqz.weather_app.exception.DataBaseException;
import io.github.xcvqqz.weather_app.mapper.UserMapper;
import io.github.xcvqqz.weather_app.mapper.WeatherMapper;
import io.github.xcvqqz.weather_app.model.WeatherData;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherMapper weatherMapper;


    @Test
    void shouldReturnWeatherDataWhenCityExists() {

        WeatherRequestDTO locationRequest = new WeatherRequestDTO(CITY_PARAM);
        WeatherData weatherData= weatherService.getCurrentWeather(locationRequest);

        assertThat(weatherData).isNotNull();
        assertThat(weatherData.getCountry()).isEqualTo(RUSSIA_COUNTRY_CODE);
    }

    @Test
    void shouldThrowCityNotFoundExceptionWhenCityDoesNotExist(){

        WeatherRequestDTO locationRequest = new WeatherRequestDTO(TEST_PARAM);

        assertThatThrownBy(() -> weatherService.getCurrentWeather(locationRequest))
                .isInstanceOf(CityNotFoundException.class);
    }

    @Test
    void shouldThrowBadRequestExceptionWhenCityIsInvalid(){

        WeatherRequestDTO locationRequest = new WeatherRequestDTO(EMPTY_INVALID_PARAM);

        assertThatThrownBy(() -> weatherService.getCurrentWeather(locationRequest))
                .isInstanceOf(BadRequestException.class)
                .hasNoCause();
    }








}
