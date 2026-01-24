package io.github.xcvqqz.weather_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegistrationDTO(


        @NotBlank
        @Size(min = 3, max = 30, message = "От 3 до 30 символов")
        String login,

        @NotBlank
        @Size(min = 3, max = 30, message = "От 3 до 30 символов")
        String password,


        @NotBlank
        @Size(min = 3, max = 30, message = "От 3 до 30 символов")
        String confirmPassword
) {
}
