package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.HibernateConfigTest;
import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)  //Подключаем Spring к JUnit 5
@ContextConfiguration(classes = HibernateConfigTest.class)  //указали тестовый конфиг
@Transactional
public class UserServiceTest {

    private final UserService userService;
    private static final String TEST_NAME = "testName";

    @Autowired
    public UserServiceTest(UserService userService){
        this.userService = userService;
    }


    @Test
    void save_shouldSaveUser() {

        UserRegistrationDTO userRegistration = new UserRegistrationDTO("testName", "testPassword", "testPassword");

        User user =  userService.save(userRegistration);

        assertThat(user.getLogin())
                .isNotNull()
                .isEqualTo(TEST_NAME);
    }

}
