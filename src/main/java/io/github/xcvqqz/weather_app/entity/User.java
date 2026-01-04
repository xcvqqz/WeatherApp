package io.github.xcvqqz.weather_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "users")
public class User {

    @NotNull(message = "should be not null")
    private String name;


}
