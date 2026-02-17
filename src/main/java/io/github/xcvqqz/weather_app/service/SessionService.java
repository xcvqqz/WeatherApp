package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import io.github.xcvqqz.weather_app.util.CookieUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SessionService {


    private final SessionRepository sessionRepository;


    public Session create(User user){

        Session session = Session
                .builder()
                .sessionId(UUID.randomUUID())
                .user(user)
                .build();

        sessionRepository.save(session);

        return session;
    }


    public User getUserBySessionId(UUID sessionId){
        return sessionRepository
                .findUserById(getBySessionId(sessionId)
                .getSessionId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }


    public void deleteSessionById(UUID sessionId){
        sessionRepository.deleteById(sessionId);
    }


    private Session getBySessionId(UUID sessionId){
        return  sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Сессия отсутствует"));
    }















}
