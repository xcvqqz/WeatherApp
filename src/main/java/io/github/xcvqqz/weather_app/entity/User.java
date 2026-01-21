package io.github.xcvqqz.weather_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "users_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "От 3 до 30 символов")   //Эти проверки поставим НА ДТО классы
    @Column(length = 30, nullable = false, unique = true)
    private String login;

    @NotBlank
    @Size(min = 7, max = 100)                       //Эти проверки поставим НА ДТО классы
    @Column(length = 100, nullable = false)
    private String password;

    /*Хранить пароль в открытом виде небезопасно,
    лучше использовать шифрование, например BCrypt*/

}