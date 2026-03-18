package io.github.xcvqqz.weather_app.scheduler;


import io.github.xcvqqz.weather_app.config.HibernateConfigTest;

import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.SessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = HibernateConfigTest.class)
@Transactional
public class SessionsCleanSchedulerTest {


    @Mock
    private SessionService sessionService;


    @InjectMocks
    private SessionsCleanScheduler sessionsCleanScheduler;


    @Test
    void delete_shouldDeleteExpiredSessions() {

        sessionsCleanScheduler.cleanExpiredSessions();

        verify(sessionService, times(1)).deleteExpiredSessions();
    }


//        @Test
//        void delete_shouldDeleteExpiredSessions() {
//
//            User expectedUser =  User.builder()
//                    .id(1L)
//                    .login(TEST_NAME)
//                    .password(TEST_PASSWORD)
//                    .build();
//
//            Session expectedSession = Session.builder()
//                    .sessionId(UUID.randomUUID())
//                    .user(expectedUser)
//                    .build();
//
//
//        sessionsCleanScheduler.cleanExpiredSessions();
//
//        verify(sessionService, times(1)).deleteExpiredSessions();
//    }




}
