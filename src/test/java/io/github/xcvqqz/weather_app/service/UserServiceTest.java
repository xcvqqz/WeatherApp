package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.mapper.UserMapper;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
@ActiveProfiles("test")
public class UserServiceTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";

    @MockitoBean
    private UserMapper userMapper;

    @MockitoBean
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;


    @Test
    void save_shouldSaveUser() {

        User expectedUser =  User.builder()
                .id(1L)
                .login(TEST_NAME)
                .password(TEST_PASSWORD)
                .build();

        when(passwordEncoder.encode(anyString())).thenReturn(expectedUser.getPassword());
        when(userMapper.registrationToEntity(any(UserRegistrationDTO.class))).thenReturn(expectedUser);
        when(userRepository.save(expectedUser)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.save(new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD));

        assertThat(actualUser.getLogin())
                .isNotNull()
                .isEqualTo(TEST_NAME);

        assertThat(actualUser.getId())
                .isNotNull()
                .isPositive();

        verify(passwordEncoder, times(1)).encode(TEST_PASSWORD);
        verify(userMapper, times(1)).registrationToEntity(any());
        verify(userRepository, times(1)).save(expectedUser);

    }

}



//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    private static final String TEST_NAME = "testName";
//    private static final String TEST_PASSWORD = "testPassword";
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @Mock
//    private  PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private  UserService userService;
//
//
//    @Test
//    void save_shouldSaveUser() {
//
//        User expectedUser =  User.builder()
//                .id(1L)
//                .login(TEST_NAME)
//                .password(TEST_PASSWORD)
//                .build();
//
//        when(passwordEncoder.encode(anyString())).thenReturn(expectedUser.getPassword());
//        when(userMapper.registrationToEntity(any(UserRegistrationDTO.class))).thenReturn(expectedUser);
//        when(userRepository.save(expectedUser)).thenReturn(Optional.of(expectedUser));
//
//        User actualUser = userService.save(new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD));
//
//        assertThat(actualUser.getLogin())
//                .isNotNull()
//                .isEqualTo(TEST_NAME);
//
//        assertThat(actualUser.getId())
//                .isNotNull()
//                .isPositive();
//
//        verify(passwordEncoder, times(1)).encode(TEST_PASSWORD);
//        verify(userMapper, times(1)).registrationToEntity(any());
//        verify(userRepository, times(1)).save(expectedUser);
//
//    }
//
//}
