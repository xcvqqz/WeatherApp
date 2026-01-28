package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.UserAlreadyExistsException;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import io.github.xcvqqz.weather_app.repository.impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public User save(User user) {

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }
        return user;
    }


    @Transactional(readOnly = true)
    public User find(String login) {
        return userRepository.find(login).orElseThrow(() -> new RuntimeException("Отсутствует имя пользователя"));
    }


    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }


}
