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
                .sessionId(UUID.fromString(CookieUtil.createCookie().getValue()))
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .user(user)
                .build();

        sessionRepository.save(session);

        return session;
    }

    public Session get(UUID sessionId){
        sessionRepository.findById(sessionId);
    }











}
