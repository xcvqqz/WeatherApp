package io.github.xcvqqz.weather_app.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xcvqqz.weather_app.dto.UserAuthDTO;
import io.github.xcvqqz.weather_app.dto.UserRegistrationDTO;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.DataBaseException;
import io.github.xcvqqz.weather_app.exception.PasswordMismatchException;
import io.github.xcvqqz.weather_app.exception.UserAlreadyExistsException;
import io.github.xcvqqz.weather_app.exception.UserNotFoundException;
import io.github.xcvqqz.weather_app.mapper.UserMapper;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import io.github.xcvqqz.weather_app.repository.impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static io.github.xcvqqz.weather_app.service.SessionService.DATABASE_ERROR_MESSAGE;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;          //не нужно создавать конструктор потому что есть аннотация @AllArgsConstructor
    private final UserMapper userMapper;                  //Spring автоматически поставит аннотацию @Autowired на конструктор
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ALREADY_EXIST_MESSAGE = "User Already Exist";
    private static final String PASSWORD_MISMATCH_MESSAGE = "The password confirmation does not match";
    protected static final String USER_NOT_FOUND_MESSAGE = "User Not Found";



    @Transactional
    public User save(UserRegistrationDTO userRegistration) {

        User entity = userMapper.registrationToEntity(userRegistration);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        try {
            userRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXIST_MESSAGE);
        }
        return entity;
    }



    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
    }


    
    @Transactional
    public void delete(User user) {
        try {
            userRepository.delete(user);
        } catch (DataAccessException e){
            throw new DataBaseException(String.format(DATABASE_ERROR_MESSAGE, e.getMessage()));
        }

    }


    @Transactional(readOnly = true)
    public User findByLogin(UserAuthDTO userAuthDTO) {

        String login = userAuthDTO.login();
        String rawPassword = userAuthDTO.password();

        User entity = userMapper.authToEntity(userAuthDTO);

        User user =  userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));

        if(passwordEncoder.matches(rawPassword, user.getPassword())){
            return user;
        } else {
            throw new PasswordMismatchException(PASSWORD_MISMATCH_MESSAGE);
        }

    }

}