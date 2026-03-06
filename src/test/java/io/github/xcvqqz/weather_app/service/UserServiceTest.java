package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.config.TestHibernateConfig;
import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.mapper.UserMapper;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)  //Подключаем Spring к JUnit 5
@ContextConfiguration(classes = TestHibernateConfig.class)  //указали тестовый конфиг
@Transactional
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService){
        this.userService = userService;
    }



    @Test
    void save_shouldPersistUser() {

        // Arrange (Подготовка)
        UserRegistrationDTO userRegistration = new UserRegistrationDTO("Иван", "password", "password");

        // Act (Действие)
        User user =  userService.save(userRegistration);

        // Assert (Проверка)
        assertNotNull(user.getId(), "ID должен быть назначен после save");
    }


    


}
