package io.github.xcvqqz.weather_app.service.weather_api_service;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.weather.LocationRequestDTO;
import io.github.xcvqqz.weather_app.dto.weather.LocationResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;


//@SpringJUnitConfig(classes = AppConfigTest.class)
//@PropertySource("classpath:application-test.properties")
//public class WeatherClientImplTest {


//    private final RestTemplate restTemplate;
//
//    private final WeatherClientImpl weatherClient;
//
//    @Autowired
//    public WeatherClientImplTest(RestTemplate restTemplate, WeatherClientImpl weatherClient) {
//        this.restTemplate = restTemplate;
//        this.weatherClient = weatherClient;
//    }
//
//    @Test
//    void shouldReturnWeatherDataWhenCityExists() {
//
//        LocationRequestDTO locationRequestDTO = new LocationRequestDTO("Moscow");
//
//        LocationResponseDTO result = weatherClient.getCurrentWeather(locationRequestDTO);
//
//        assertThat(result).isNotNull();
//
//    }
//}
















//@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = HibernateConfigTest.class)
//@Transactional
//@PropertySource("classpath:application-test.properties")
//public class WeatherClientImplTest {
//
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Value("${app.key}")
//    private String appKey;
//
//    @Value("${base.url}")
//    private String baseURL;
////    https://api.openweathermap.org/data/2.5/weather
//
//
//    @InjectMocks
//    private WeatherClientImpl weatherClient;
//
//
//    @Test
//    void shouldReturnWeatherDataWhenCityExists() {
//
//        LocationRequestDTO locationRequestDTO = new LocationRequestDTO("Moscow");
//
//        LocationResponseDTO expectedLocationResponseDTO = LocationResponseDTO.builder()
//                .name("Moscow")
//                .country("RU")
//                .lon(BigDecimal.valueOf(37.6156))
//                .lat(BigDecimal.valueOf(55.7522))
//                .description("scattered clouds")
//                .humidity(BigDecimal.valueOf(51))
//                .temp(BigDecimal.valueOf(277.23))
//                .feelsLike(BigDecimal.valueOf(277.23))
//                .build();
//
//
//        when(weatherClient.getCurrentWeather(locationRequestDTO)).thenReturn(expectedLocationResponseDTO);
//
//        LocationResponseDTO result = weatherClient.getCurrentWeather(locationRequestDTO);
//
//        assertThat(result).isEqualTo(expectedLocationResponseDTO);
//
//    }
//}