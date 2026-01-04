package io.github.xcvqqz.weather_app;

import io.github.xcvqqz.weather_app.config.DatabaseConfig;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);

        // Flyway уже выполнился (initMethod = "migrate")

        Flyway flyway = context.getBean(Flyway.class);
        System.out.println("Миграций применено: " + flyway.info().applied().length);

        context.close();
    }



    }


