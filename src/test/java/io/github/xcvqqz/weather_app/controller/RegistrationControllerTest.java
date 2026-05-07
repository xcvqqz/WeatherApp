package io.github.xcvqqz.weather_app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xcvqqz.weather_app.config.AppConfigTest;

import io.github.xcvqqz.weather_app.dto.auth.UserAuthDTO;
import io.github.xcvqqz.weather_app.dto.auth.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.handler.GlobalExceptionHandler;
import io.github.xcvqqz.weather_app.service.CookieService;
import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @Autowired
    private SessionService sessionService;

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


//    @Test
//    public void shouldReturn409ConflictWhenUserAlreadyExists() throws Exception {
//
//        mockMvc.perform(post("/sign-up")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("login", TEST_NAME)
//                        .param("password", TEST_PASSWORD)
//                        .param("confirmPassword", CONFIRM_TEST_PASSWORD))
//                        .andExpect(status().is3xxRedirection());
//
//
//        mockMvc.perform(post("/sign-up")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("login", TEST_NAME)
//                .param("password", TEST_PASSWORD)
//                .param("confirmPassword", CONFIRM_TEST_PASSWORD))
//                .andExpect();
//
//    }




    @Test
    public void shouldFailValidationWhenPasswordsDoNotMatch() throws Exception {

        mockMvc.perform(post("/sign-up")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", TEST_NAME)
                        .param("password", TEST_PASSWORD)
                        .param("confirmPassword", MISMATCH_TEST_PASSWORD))

                .andExpect(model().hasErrors());
//                .andExpect(model().attribute("user", "confirmPassword"));

    }






//    @Test
//    public void shouldReturn2xxWhenUserSuccessfullyCreated() throws Exception {
//
//        mockMvc.perform(post("/sign-up")
//                        .contentType(MediaType.valueOf("application/x-www-form-urlencoded"))
//
//                        .param("login", TEST_NAME)
//                        .param("password", TEST_PASSWORD)
//                        .param("confirmPassword", CONFIRM_TEST_PASSWORD))
//
////                .andExpect(status().is3xxRedirection())
////                .andExpect(redirectedUrl("/home"));
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(model().attributeExists("user"))
//                .andExpect(view().name("sign-up"));
////                .andExpect(model().hasErrors());
//
//
//    }



}


