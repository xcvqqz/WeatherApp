package io.github.xcvqqz.weather_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"io.github.xcvqqz.weather_app.service",
                               "io.github.xcvqqz.weather_app.repository",
                               "io.github.xcvqqz.weather_app.mapper"})
public class TestAppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
