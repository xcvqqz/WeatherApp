package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class UserServiceTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";

    @Autowired
    private UserService userService;

    @Test
    void shouldSaveNewUser() {

        UserRegistrationDTO userRegistrationTest = new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD);

        User actualUser = userService.save(userRegistrationTest);

        assertThat(actualUser.getLogin())
                .isNotNull()
                .isEqualTo(userRegistrationTest.login());

        assertThat(actualUser.getId())
                .isNotNull()
                .isPositive();

    }
}
