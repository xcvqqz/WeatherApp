package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThrows;

@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
public class UserServiceTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";

    private final UserRegistrationDTO userRegistrationTest = new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD);

    @Autowired
    private UserService userService;

    @Test
    public void shouldSaveNewUser() {

        User actualUser = userService.save(userRegistrationTest);

        assertThat(actualUser.getLogin())
                .isNotNull()
                .isEqualTo(userRegistrationTest.login());

        assertThat(actualUser.getId())
                .isNotNull()
                .isPositive();
    }


    @Test
    public void shouldThrowExceptionWhenUserAlreadyExists(){

        userService.save(userRegistrationTest);

        assertThrows(UserAlreadyExistsException.class,
                () -> userService.save(userRegistrationTest));

    }
}