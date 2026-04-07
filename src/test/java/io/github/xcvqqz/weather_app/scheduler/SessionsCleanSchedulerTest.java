package io.github.xcvqqz.weather_app.scheduler;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.model.entity.Session;
import io.github.xcvqqz.weather_app.model.entity.User;
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



@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class SessionsCleanSchedulerTest {

    private static final String TEST_NAME = "testName";
    private final String TEST_PASSWORD = "testPassword";

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

        Session session = createTestSession();

        List<Session> beforeCleanUpSessions = sessionService.findAll();

        session.setExpiresAt(SESSION_EXPIRY_TIME);
        sessionsCleanScheduler.cleanExpiredSessions();

        List<Session> afterCleanUpSessions = sessionService.findAll();

        assertThat(beforeCleanUpSessions).hasSize(1);
        assertThat(afterCleanUpSessions).isEmpty();

    }

    @Test
    void shouldNotDeleteValidSessions(){

        Session session = createTestSession();

        List<Session> beforeCleanUpSessions = sessionService.findAll();
        sessionsCleanScheduler.cleanExpiredSessions();
        List<Session> afterCleanUpSessions = sessionService.findAll();

        assertThat(beforeCleanUpSessions).hasSize(1);
        assertThat(afterCleanUpSessions).hasSize(1);

    }


    private Session createTestSession(){
        UserRegistrationDTO userRegistrationTest = new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD);
        User testUser = userService.save(userRegistrationTest);
        return sessionService.create(testUser);
    }



}

