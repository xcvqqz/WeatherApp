package io.github.xcvqqz.weather_app.scheduler;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class SessionsCleanSchedulerTest {

    private static final String TEST_NAME_ONE = "testName1";
    private static final String TEST_NAME_TWO = "testName2";
    private static final String TEST_PASSWORD = "testPassword";
    private static final LocalDateTime SESSION_EXPIRY_TIME = LocalDateTime.now().minusMinutes(30).minusSeconds(5);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionsCleanScheduler sessionsCleanScheduler;


    @BeforeEach
    void setUp() {
        sessionService.deleteAll();
        userService.deleteAll();
    }

    @Test
    void shouldDeleteExpiredSessions() {

        UserRegistrationDTO userRegistrationTestOne = new UserRegistrationDTO(TEST_NAME_ONE,TEST_PASSWORD,TEST_PASSWORD);
        UserRegistrationDTO userRegistrationTestTwo = new UserRegistrationDTO(TEST_NAME_TWO,TEST_PASSWORD,TEST_PASSWORD);

        User testUserOne = userService.save(userRegistrationTestOne);
        Session testSessionOne = sessionService.create(testUserOne);

        User testUserTwo = userService.save(userRegistrationTestTwo);
        Session testSessionTwo = sessionService.create(testUserTwo);

        List<Session> sessions = sessionService.findAll();

        testSessionTwo.setExpiresAt(SESSION_EXPIRY_TIME);

        sessionsCleanScheduler.cleanExpiredSessions();

        List<Session> sessions2 = sessionService.findAll();

        assertThat(sessions2).hasSize(1);


    }
}

