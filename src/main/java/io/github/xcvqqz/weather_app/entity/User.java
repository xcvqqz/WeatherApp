package io.github.xcvqqz.weather_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(length = 30)
    private String login;

    @Column(length = 100)
    private String password;

    /*Хранить пароль в открытом виде небезопасно,
    лучше использовать шифрование, например BCrypt*/

}