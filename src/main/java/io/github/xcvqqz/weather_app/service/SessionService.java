package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.exception.DataBaseException;
import io.github.xcvqqz.weather_app.exception.SessionNotFoundException;
import io.github.xcvqqz.weather_app.exception.UserNotFoundException;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static io.github.xcvqqz.weather_app.service.UserService.USER_NOT_FOUND_MESSAGE;

@Slf4j
@Service
public class SessionService {

    protected static final String DATABASE_ERROR_MESSAGE = "Database error: %s";
    private static final String SESSION_NOT_FOUND_MESSAGE = "Session Not Found";

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


    public List<Session> findAll(){
        return sessionRepository.findAll();
    }


    @Transactional
    public void deleteAll() {
        sessionRepository.deleteAll();
    }


    @Transactional
    public Session create(User user){

        Session session = Session
                .builder()
                .sessionId(UUID.randomUUID())
                .user(user)
                .build();

         return sessionRepository
                 .save(session)
                 .orElseThrow(() -> new DataBaseException(DATABASE_ERROR_MESSAGE));

    }


    @Transactional()
    public void deleteById(UUID sessionId){
        if(sessionId != null) {
            try{
                sessionRepository.deleteById(sessionId);
            } catch (DataAccessException e){
                throw new DataBaseException(String.format(DATABASE_ERROR_MESSAGE, e.getMessage()));
            }
        }
    }


    @Transactional()
    public int deleteExpiredSessions(){
        List<Session> sessions = sessionRepository.findAll();
        int expiredSessions = 0;
        for(Session session : sessions){
            if (isExpired(session)){
                try {
                    sessionRepository.delete(session);
                    expiredSessions++;
                } catch (DataAccessException e){
                    throw new DataBaseException(String.format(DATABASE_ERROR_MESSAGE, e.getMessage()));
                }
            }
        }
        return expiredSessions;
    }

    private boolean isExpired(Session session){
        if(session == null || session.getExpiresAt() == null) {
            return true;
        }
        return  LocalDateTime
                .now()
                .isAfter(session.getExpiresAt());
    }

}