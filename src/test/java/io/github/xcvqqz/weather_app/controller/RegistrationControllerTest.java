package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.config.AppConfigTest;

import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringJUnitConfig(classes = AppConfigTest.class)
@Transactional
public class RegistrationControllerTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";


    private MockMvc mockMvc;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;




    @BeforeEach
    void setup() {

        RegistrationController registrationController = new RegistrationController(sessionService, userService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(registrationController)
                .alwaysDo(print())
                .build();

//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .alwaysDo(print())
//                .build();
    }

//    @Test
//    public void should3xxRedirectionWhenCreatedUser() throws Exception {
//
//        mockMvc.perform(post("/sign-up")
//                        .contentType(MediaType.valueOf("application/x-www-form-urlencoded"))
//                        .param("name", TEST_NAME)
//                        .param("password", TEST_PASSWORD)
//                        .param("confirmPassword", TEST_PASSWORD))
//
//                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("user"))
//                .andExpect(model().hasErrors())
//                .andExpect(redirectedUrl("/home"));
//
//
//    }
}


