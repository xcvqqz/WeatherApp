package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.HibernateConfigTest;
import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.mapper.UserMapper;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith({SpringExtension.class, MockitoExtension.class})  //Подключаем Spring к JUnit 5
@ContextConfiguration(classes = HibernateConfigTest.class)  //указали тестовый конфиг
@Transactional
public class UserServiceTest {

    private static final String TEST_NAME = "testName";

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @InjectMocks
    private  UserService userService;


    @Test
    void save_shouldSaveUser() {

        UserRegistrationDTO userRegistration = new UserRegistrationDTO(
                "testName",
                "testPassword",
                "testPassword");

        User createdUser =  User.builder()
                .id(1L)
                .login(userRegistration.login())
                .password(userRegistration.password()).build();

        when(passwordEncoder.encode(anyString())).thenReturn(userRegistration.password());
        when(userMapper.registrationToEntity(userRegistration)).thenReturn(createdUser);
        when(userRepository.save(createdUser)).thenReturn(Optional.of(createdUser));

        User result = userService.save(userRegistration);

        assertThat(result.getLogin())
                .isNotNull()
                .isEqualTo(TEST_NAME);

        assertThat(result.getId())
                .isNotNull()
                .isPositive();

    }

}
