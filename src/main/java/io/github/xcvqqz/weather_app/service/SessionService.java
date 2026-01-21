package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {


    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }







}
