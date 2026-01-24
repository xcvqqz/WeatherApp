package io.github.xcvqqz.weather_app.util;

import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.PasswordMismatchException;

public class Validator {



    public static void validatePasswordConfirmation(String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new PasswordMismatchException("Passwords do not match");
        }
    }


    public static void validatePassword(String password) {

    }


}
