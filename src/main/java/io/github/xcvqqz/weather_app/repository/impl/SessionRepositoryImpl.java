package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.AbstractRepositoryImpl;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SessionRepositoryImpl extends AbstractRepositoryImpl<Session, UUID> implements SessionRepository {

    @Autowired
    public SessionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory,  Session.class);
    }


    @Override
    public Optional<User> findUserById(UUID sessionId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("SELECT s.user FROM Session AS s WHERE s.id = :sessionId", User.class)
                .setParameter("sessionId", sessionId)
                .getSingleResultOrNull());
    }

}
