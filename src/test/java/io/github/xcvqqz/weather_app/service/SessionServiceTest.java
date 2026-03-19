package io.github.xcvqqz.weather_app.service;

import io.github.xcvqqz.weather_app.config.HibernateConfigTest;

import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = HibernateConfigTest.class)
@Transactional
public class SessionServiceTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";

    @Mock
    private UserService userService;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private  SessionService sessionService;


    @Test
    void create_shouldReturnCreatedSession() {

        User expectedUser =  User.builder()
                .id(1L)
                .login(TEST_NAME)
                .password(TEST_PASSWORD)
                .build();

        Session expectedSession = Session.builder()
                .sessionId(UUID.randomUUID())
                .user(expectedUser)
                .build();


        when(userService.save(any(UserRegistrationDTO.class))).thenReturn(expectedUser);

        when(sessionRepository.save(any(Session.class))).thenReturn(Optional.of(expectedSession));

        User actualUser = userService.save(new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD));

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

        verify(userService, times(1)).save(any());
        verify(sessionRepository, times(1)).save(any(Session.class));

    }



}
