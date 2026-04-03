package io.github.xcvqqz.weather_app.mapper;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class WeatherMapperTest {




}
