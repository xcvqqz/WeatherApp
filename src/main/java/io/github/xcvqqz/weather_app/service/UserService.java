package io.github.xcvqqz.weather_app.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xcvqqz.weather_app.dto.UserAuthDTO;
import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.PasswordMismatchException;
import io.github.xcvqqz.weather_app.exception.UserAlreadyExistsException;
import io.github.xcvqqz.weather_app.mapper.UserMapper;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import io.github.xcvqqz.weather_app.repository.impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;          //не нужно создавать конструктор потому что есть аннотация @AllArgsConstructor
    private UserMapper userMapper;                  //Spring автоматически поставит аннотацию @Autowired на конструктор
    private PasswordEncoder passwordEncoder;


    @Transactional
    public User save(UserRegistrationDTO userRegistration) {

        String encodedPassword = passwordEncoder.encode(userRegistration.password());

        User entity = User.builder()
                .login(userRegistration.login())
                .password(encodedPassword)
                .build();

        try {
            userRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }
        return entity;
    }


    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Отсутствует имя пользователя"));
    }
    
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public User findByLogin(UserAuthDTO userAuthDTO) {

        String login = userAuthDTO.login();
        String rawPassword = userAuthDTO.password();

        User user =  userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Отсутствует имя пользователя"));

        if(passwordEncoder.matches(rawPassword, user.getPassword())){
            return user;
        } else {
            throw new PasswordMismatchException("пароль введён неверно");
        }

    }




}
