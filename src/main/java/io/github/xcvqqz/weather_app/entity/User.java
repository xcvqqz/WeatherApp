package io.github.xcvqqz.weather_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String login;

    @Column(length = 100, nullable = false)
    private String password;

    /*Хранить пароль в открытом виде небезопасно,
    лучше использовать шифрование, например BCrypt*/

}