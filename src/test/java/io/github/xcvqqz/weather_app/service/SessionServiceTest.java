package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class SessionServiceTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Test
    void shouldReturnCreatedSession() {

        UserRegistrationDTO userRegistrationTest = new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD);

        User actualUser = userService.save(userRegistrationTest);

        Session actualSession = sessionService.create(actualUser);

        assertThat(actualSession)
                .satisfies(obj -> {

                    LocalDateTime expiresAt = obj.getExpiresAt();

                    assertThat(expiresAt).isNotNull();


                    assertThat(expiresAt)
                            .isBetween(
                                    LocalDateTime.now().plusMinutes(30).minusSeconds(5),
                                    LocalDateTime.now().plusMinutes(30).plusSeconds(5)
                            );

                    assertThat(obj.getSessionId())
                            .isNotNull();

                    assertThat(obj.getUser())
                            .isNotNull()
                            .isEqualTo(actualUser);
                });
    }

}