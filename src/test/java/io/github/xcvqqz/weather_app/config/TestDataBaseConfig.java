package io.github.xcvqqz.weather_app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-test.properties")
public class TestDataBaseConfig {

        @Value("${db.url}")
        private String url;

        @Value("${db.username}")
        private String username;

        @Value("${db.password}")
        private String password;

        @Value("${db.driver}")
        private String driverClassName;

        @Value("${flyway.locations}")
        private String location;


        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        }


        @Bean(initMethod = "migrate")
        public Flyway flyway(DataSource dataSource) {
            return Flyway.configure()
                    .dataSource(dataSource)
                    .locations(location)
                    .baselineOnMigrate(true)
                    .load();

        }




}
