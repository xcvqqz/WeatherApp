package io.github.xcvqqz.weather_app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xcvqqz.weather_app.config.AppConfigTest;

import io.github.xcvqqz.weather_app.dto.auth.UserAuthDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = AppConfigTest.class)
@Transactional
public class RegistrationControllerTest {

    private static final String TEST_NAME = "testName";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String CONFIRM_TEST_PASSWORD = "testPassword";
    private static final String MISMATCH_TEST_PASSWORD = "mismatchPassword";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;


    private MockMvc mockMvc;

    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    public void should3xxRedirectionWhenCreatedUser() throws Exception {

        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("login", TEST_NAME)
                .param("password", TEST_PASSWORD)
                .param("confirmPassword", CONFIRM_TEST_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }


    @Test
    public void shouldPersistNewUserWhenValidRegistration() throws Exception {

        mockMvc.perform(post("/sign-up")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", TEST_NAME)
                        .param("password", TEST_PASSWORD)
                        .param("confirmPassword", CONFIRM_TEST_PASSWORD));

        UserAuthDTO userAuth = new UserAuthDTO(TEST_NAME, TEST_PASSWORD);

        User createdUser = userService.findByLogin(userAuth);
        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals(TEST_NAME, createdUser.getLogin());
    }
}