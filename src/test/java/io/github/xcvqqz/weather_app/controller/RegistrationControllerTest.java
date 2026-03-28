package io.github.xcvqqz.weather_app.controller;


import io.github.xcvqqz.weather_app.config.AppConfigTest;
import io.github.xcvqqz.weather_app.mapper.UserMapper;

import io.github.xcvqqz.weather_app.service.SessionService;
import io.github.xcvqqz.weather_app.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


@SpringJUnitConfig(classes = AppConfigTest.class)
@WebAppConfiguration
@Transactional
public class RegistrationControllerTest {

//    private static final String TEST_NAME = "testName";
//    private static final String TEST_PASSWORD = "testPassword";
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private  PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private SessionService sessionService;
//
//    @Autowired
//    private  UserMapper userMapper;
//
//    @Autowired
//    private UserService userService;



//    @BeforeEach
//    void setup() {
//        // Инициализируем MockMvc перед каждым тестом
//
//        RegistrationController controller = new RegistrationController(sessionService, userService);
//
//        this.mockMvc = MockMvcBuilders
//                .standaloneSetup(controller)
//                .alwaysDo(print())
//                .build();
//    }

//    @Test
//    public void shouldReturn200WhenCreatedUser() throws Exception {
//
//        UserRegistrationDTO userRegistrationTest = new UserRegistrationDTO(TEST_NAME,TEST_PASSWORD,TEST_PASSWORD);
//
//        User createdUser = userService.save(userRegistrationTest);
//
//        mockMvc.perform(post("/sign-up")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", TEST_NAME)
//                        .param("password", TEST_PASSWORD)
//                        .param("confirmPassword", TEST_PASSWORD))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("user"))
//                .andExpect(view().name("redirect:/home"));
//    }
}


