package io.github.xcvqqz.weather_app.scheduler;


import io.github.xcvqqz.weather_app.config.HibernateConfigTest;

import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.SessionService;
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
public class SessionsCleanSchedulerTest {

//
//    private final SessionService sessionService;
//    private final SessionsCleanScheduler sessionsCleanScheduler;
//
//    @Autowired
//    public SessionsCleanSchedulerTest(SessionsCleanScheduler sessionsCleanScheduler, SessionService sessionService) {
//        this.sessionService = sessionService;
//        this.sessionsCleanScheduler = sessionsCleanScheduler;
//    }
//
//    @Test
//    void delete_shouldDeleteExpiredSessions(){

//        Session expiredSession1 = sessionService.create(new User());
//        Session expiredSession2 = sessionService.create(new User());
//
//        expiredSession1.
//
//        assert sessionsCleanScheduler.cleanExpiredSessions();
//



}
