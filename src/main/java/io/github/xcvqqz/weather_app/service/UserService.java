package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import io.github.xcvqqz.weather_app.repository.impl.UserRepositoryImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Отсутствует имя пользователя"));
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Неверный Id пользователя"));
    }


    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }


}
