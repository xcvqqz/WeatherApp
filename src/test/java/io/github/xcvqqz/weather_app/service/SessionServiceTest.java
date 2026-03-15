package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.config.HibernateConfigTest;

import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HibernateConfigTest.class)
@Transactional
public class SessionServiceTest {

    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public SessionServiceTest(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Test
    void create_shouldReturnCreatedSession() {

        UserRegistrationDTO userRegistrationTest = new UserRegistrationDTO(
                "testLogin",
                "testPassword",
                "testPassword");

        User createdUser =  userService.save(userRegistrationTest);

        Session createdSession = sessionService.create(createdUser);

        assertThat(createdSession)
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
                            .isEqualTo(createdUser);
                });

    }



}
