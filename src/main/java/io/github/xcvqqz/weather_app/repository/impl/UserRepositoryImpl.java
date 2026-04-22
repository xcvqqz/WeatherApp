package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.entity.*;
import io.github.xcvqqz.weather_app.exception.UserNotFoundException;
import io.github.xcvqqz.weather_app.repository.AbstractRepositoryImpl;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.UUID;



@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User, Long> implements UserRepository {

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory,  User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM User WHERE login = :login")
                .setParameter("login", login)
                .uniqueResultOptional();
    }

    @Override
    public Optional<User> findBySessionId(UUID sessionId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("SELECT s.user FROM Session AS s WHERE s.id = :sessionId", User.class)
                .setParameter("sessionId", sessionId)
                .getSingleResultOrNull());
    }


}