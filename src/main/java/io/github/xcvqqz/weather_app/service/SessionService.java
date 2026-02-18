package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import io.github.xcvqqz.weather_app.util.CookieUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        if(sessionId != null) {
            sessionRepository.deleteById(sessionId);
        }
    }

    public void deleteExpiredSessions(){
        List<Session> sessions = sessionRepository.findAll();
        for(Session session : sessions){
            if (isExpired(session)){
                sessionRepository.delete(session);
            }
        }


    }

    public boolean isExpired(Session session){

        if(session == null || session.getExpiresAt() == null) {
            return true;
        }
        return  LocalDateTime
                .now()
                .isAfter(session.getExpiresAt());
    }


    private Session getBySessionId(UUID sessionId){
        return  sessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Сессия отсутствует"));
    }















}
