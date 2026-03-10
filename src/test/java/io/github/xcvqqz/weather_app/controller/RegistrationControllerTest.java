package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.config.HibernateConfigTest;
import io.github.xcvqqz.weather_app.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HibernateConfigTest.class)
@WebAppConfiguration
@Transactional
public class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final UserService userService;

    @Autowired
    public RegistrationControllerTest(UserService userService){
        this.userService = userService;
    }




    @BeforeEach
    void setup() {
        // Инициализируем MockMvc перед каждым тестом
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


//    @Test
//    void create_shouldGetNewSession(){
//        mockMvc.perform()
//    }






}
